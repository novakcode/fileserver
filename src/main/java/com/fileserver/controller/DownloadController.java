package com.fileserver.controller; 

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
import static org.springframework.http.HttpHeaders.IF_NONE_MATCH;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fileserver.model.FileResource;
import com.fileserver.service.IFileService;
import static com.fileserver.util.FileUtil.ATTACHMENT;


@RestController
@RequestMapping("/file/")
public class DownloadController {

	@Autowired
	protected IFileService fileService;

	@GetMapping("{fileName}/download")
	public ResponseEntity<Resource> download(@PathVariable("fileName") String fileName,
			@RequestHeader(IF_NONE_MATCH) Optional<String> requestEtagOpt) {
		
		return fileService.get(fileName).map(fileRes -> {
			
			if (requestEtagOpt.isPresent())
				return serve(fileRes,null,HttpStatus.NOT_MODIFIED);

			return serve(fileRes,new InputStreamResource(fileRes.open()),HttpStatus.OK);

		}).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

	}
	
	private ResponseEntity<Resource> serve(FileResource fileRes,Resource resource,HttpStatus status){
		return ResponseEntity.status(status)
				.header(CONTENT_DISPOSITION, ATTACHMENT  + fileRes.originalFileName())
				.contentType(MediaType.parseMediaType(fileRes.contentType()))
				.contentLength(fileRes.size())
				.eTag(fileRes.eTag())
				.body(resource);
	}

	

}
