package com.fileserver.response;

public class UploadResponse {


	
	private String originalFileName;
	private Long size;
	private String downloadPath;
	private UploadStatus status;
	
	public UploadResponse(String fileName, Long size, String downloadPath) {
		this.originalFileName = fileName;
		this.size = size;
		this.downloadPath = downloadPath;
		this.status = UploadStatus.FILE_UPLOADED;
	}
	
	public UploadResponse(UploadStatus uploadStatus){
		this.status = uploadStatus;
	}
	
	

	public String getOriginalFileName() {
		return originalFileName;
	}

	public Long getSize() {
		return size;
	}

	public String getDownloadPath() {
		return downloadPath;
	}

	public UploadStatus getStatus() {
		return status;
	}
	
	
	
	
	

}
