package com.fileserver.domain;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Document(collection = "File_Info")
public class FileInfo {

	
	@Id
	private String _id;
	
	private String name;
	private String originalFileName;
	private String contentType;
	private  Long size;
	private  String path;
	private String hash;

	
	public FileInfo(String name,String originalFileName,String content_type, Long size,String path,String hash)  {
		this.name = name;
		this.originalFileName = originalFileName;
		this.contentType = content_type;
		this.size = size;
		this.path = path;
		this.hash = hash;
		
	}
	
	
	


}
