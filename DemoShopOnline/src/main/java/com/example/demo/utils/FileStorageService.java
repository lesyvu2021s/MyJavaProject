package com.example.demo.utils;


import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exception.FileNotFoundException;
import com.example.demo.exception.FileStorageException;

@Service
public class FileStorageService {

	private final Path fileStorageLocation ;
	
	public FileStorageService(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation=Paths.get(fileStorageProperties.getUploadLocation()).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.fileStorageLocation);
		}catch (Exception e) {
			throw new FileStorageException("Không thể tạo thư mục lưu trữ các tệp đã tải lên. " , e);
		}
	}
	public String storeFile(MultipartFile file) {
	
		//normalize file name 
		String fileName =StringUtils.cleanPath(file.getOriginalFilename());
		String fileExtent =StringUtils.getFilenameExtension(file.getOriginalFilename());
		String fileNameWithOutEx = FilenameUtils.removeExtension(fileName);
		Path path =Paths.get(fileNameWithOutEx + "_" + 1 +"."+fileExtent);
		try {
			if(fileName.contains("..")) {
				throw new FileStorageException("Tên tệp chứa chuỗi đường dẫn không hợp lệ" + fileName);
			}
			//Sao chép tệp vào vị trí đích (Thay thế tệp hiện có có cùng tên)
			Path targetLocation = this.fileStorageLocation.resolve(path);
			Files.copy(file.getInputStream(), targetLocation ,StandardCopyOption.REPLACE_EXISTING);
			return path.getFileName().toString();
		}catch (IOException e) {
			
		throw new FileStorageException("Không thể lưu trữ tệp " + fileName + ". hãy thử lại! " , e);
	}
}
	
	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath =this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource =new UrlResource(filePath.toUri());
			if(resource.exists()) {
				return resource;
			}else {
				throw new FileNotFoundException("Không tìm thấy tệp"  + fileName);
			}
		}catch (MalformedURLException e) {
			throw new FileNotFoundException("Không tìm thấy tệp" + fileName, e);
		}
		
	
		
	}
}
