package com.fileserver.service;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.fileserver.model.FileResource;

public interface IFileService {


	
	Optional<FileResource> get(String fileName);
	Optional<String> upload(MultipartFile file) ;



}
