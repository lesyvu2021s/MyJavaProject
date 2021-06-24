package com.example.demo.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.dto.UploadFileResponseDTO;
import com.example.demo.utils.FileStorageService;


@RestController
public class FileController {

	private final Logger log = org.slf4j.LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	private FileStorageService fileService;
	
	@PostMapping("/uploadFile")
	public UploadFileResponseDTO uploadFile(@RequestParam("file") MultipartFile file) {
		String fileName = fileService.storeFile(file);
		
		String fileDownloadUri =ServletUriComponentsBuilder.fromCurrentContextPath()
									.path("/downloadFile/")
									.path(fileName)
									.toString();
		return new UploadFileResponseDTO(fileName,fileDownloadUri,file.getContentType(), file.getSize());
	}
	
	@PostMapping("/uploadMultipleFiles")
	public List<UploadFileResponseDTO> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files){
		return Arrays.asList(files)
					.stream()
					.map(file -> uploadFile(file))
					.collect(Collectors.toList());
	}
	
	@GetMapping("/downloadFile/{fileName:.+")
	public ResponseEntity<Resource> dowloadFile(@PathVariable String fileName ,HttpServletRequest request){
		//tải tệp dưới dạng tài nguyên 
		Resource resource =fileService.loadFileAsResource(fileName);
		//Cố gắng xác định loại nội dung của tệp
		String contentType = null ; 
		
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		}catch (IOException e) {
			log.error("Không thể xác định loại tệp.");
		}
		//Dự phòng về loại nội dung mặc định nếu không thể xác định được loại
		if(contentType==null) {
			contentType = "application/octet-stream";
		}
		
		return ResponseEntity.ok()
							.contentType(MediaType.parseMediaType(contentType))
							.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ resource.getFilename()+"\"")
							.body(resource);
		
	}
}
