package com.fileserver.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fileserver.config.FileProperties;
import com.fileserver.domain.FileInfo;
import com.fileserver.model.FileResource;
import com.fileserver.repository.FileRepository;
import static com.fileserver.util.FileUtil.inputToHash;
import static com.fileserver.util.FileUtil.refactorFileName;

@Service
public class FileService implements IFileService {

	private final FileRepository repository;
	private final FileProperties properties;

	public FileService(FileRepository repository, FileProperties properties) {
		this.repository = repository;
		this.properties = properties;
	}

	/**
	 * @param file 	to upload
	 * @return Name of file
	 */

	@Override
	public Optional<String> upload(MultipartFile file)  {
			
		String fileUUID = UUID.nameUUIDFromBytes(file.getOriginalFilename().getBytes()).toString();
		String uuidName = fileUUID.substring(0,7);
			
			try {
				
				Path filePath = properties.folder(fileUUID.hashCode()).resolve(fileUUID);			
				
				Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
	
				
				repository.save(new FileInfo(uuidName,refactorFileName(file.getOriginalFilename())
						,file.getContentType(), file.getSize(),
						filePath.toString(),inputToHash(file.getInputStream())));
				
				return Optional.of(uuidName);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			return Optional.empty();
	}
	
	/*
	 * @param Name of file
	 * @return FileResource
	 */
	@Override
	public Optional<FileResource> get(String fileName) {
		FileInfo fileInfo = repository.findByName(fileName);
		
		if(fileInfo == null)
			return Optional.empty();
		
		return Optional.of(new FileResource(fileInfo));
	}


}
