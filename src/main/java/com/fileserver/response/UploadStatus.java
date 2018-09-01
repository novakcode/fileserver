package com.fileserver.response;

public enum UploadStatus
{

	FILE_UPLOADED("File has been uploaded."),
	FILE_NOT_UPLOADED("Error when uploading..."),
	FILE_SIZE_LIMIT_EXCEEDED("File size limit is 10MB");
	
	private String message;
	
	private UploadStatus(final String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
	
}
