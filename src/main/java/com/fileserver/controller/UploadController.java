package com.fileserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fileserver.response.UploadResponse;
import com.fileserver.response.UploadStatus;
import com.fileserver.service.IFileService;


@RestController
@RequestMapping("/file/")
public class UploadController {

	@Autowired
	protected IFileService fileService;

	@PostMapping("upload")
	public UploadResponse upload(@PathVariable("fileToUpload") MultipartFile fileToUpload) {
		return fileService
				.upload(fileToUpload).map(refactoredName -> new UploadResponse(fileToUpload.getOriginalFilename(),
						fileToUpload.getSize(), "localhost:8080/file/" + refactoredName + "/download"))
				.orElse(new UploadResponse(UploadStatus.FILE_NOT_UPLOADED));
	}
	
	
}

