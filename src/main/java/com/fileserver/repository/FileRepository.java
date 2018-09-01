package com.fileserver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fileserver.domain.FileInfo;

public interface FileRepository extends MongoRepository<FileInfo, String>{

	FileInfo findByName(String name);
	
}
