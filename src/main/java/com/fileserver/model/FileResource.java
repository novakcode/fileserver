package com.fileserver.model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.fileserver.domain.FileInfo;

public class FileResource{

	private FileInfo info;

	public FileResource(FileInfo info) {
			this.info = info;
	}

	public InputStream open() {
		try {	
			return new BufferedInputStream(new FileInputStream(info.getPath()));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e);
		}

	}
	
	public String originalFileName(){
		return info.getOriginalFileName();
	}
	
	public Long size() {
		return info.getSize();
	}
	
	
	public String contentType() {
		return info.getContentType();
	}
	

	public String eTag(){
		return info.getHash();
	}

	

}
