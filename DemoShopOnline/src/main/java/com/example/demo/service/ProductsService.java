package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.dto.ProductDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Products;
import com.example.demo.repository.ContainersRepository;
import com.example.demo.repository.ProductsRepository;
import com.example.demo.utils.FileStorageService;

@Service
public class ProductsService {

	@Autowired
	private ProductsRepository productRepo;

	@Autowired
	private FileStorageService fileService ;
	
	@Autowired
	private ModelMapper modelMapper ;
	

	public List<ProductDto> getAllProducts() {
		return productRepo.findAll().stream().map(product -> modelMapper.map(product, ProductDto.class))
										.collect(Collectors.toList());
	}

	public void CreateOrUpdateProduct(ProductDto productDto) {	
		productDto.setPhotoImage(provideFileDownloadUrlFrmMultipart(productDto.getFile()));
		Products product = modelMapper.map(productDto, Products.class);
		productRepo.save(product);
		
	}

//	public Products edit(Products productRequest , Integer id) {
//		Products product =productRepo.findById(id).orElseThrow(
//				()-> new NotFoundException("sản phẩm không tồn tại ")
//				);
//		product.setName(productRequest.getName());
//		product.setContent(productRequest.getContent());
//		product.setPrice(productRequest.getPrice());
//		product.setUnit(productRequest.getUnit());
//		product.setContainer(productRequest.getContainer());
//		return productRepo.save(product);
//	}

	public void deleteProduct(Integer id) {
		Products product =productRepo.findById(id).orElseThrow(
				()-> new NotFoundException("Id không tồn tại !"));
			productRepo.delete(product);
	}

	public ProductDto GetByIdProducts(Integer id) {
		Optional<Products> product = productRepo.findById(id);
		ProductDto productDto = modelMapper.map(product, ProductDto.class);
		return productDto;
		
	}
	
	public String provideFileDownloadUrlFrmMultipart(MultipartFile file) {
		String fileName =fileService.storeFile(file);
		return ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/").path(fileName).toUriString();
	}

}
