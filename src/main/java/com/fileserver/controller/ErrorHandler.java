package com.fileserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fileserver.response.UploadResponse;
import com.fileserver.response.UploadStatus;

@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(value={RuntimeException.class})
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public UploadResponse fileSizeLimitExceeded(){
		return new UploadResponse(UploadStatus.FILE_SIZE_LIMIT_EXCEEDED);
	}


}
