package com.fileserver.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class FileUtil {

	public static final String ATTACHMENT = "attachment;filename=";
	
	
	public static String refactorFileName(String fullFileName){
		if(fullFileName.length() < 10)
			return fullFileName;
		
		return extractName(fullFileName).substring(0,10) + 
				extractExtension(fullFileName); // extension
		
	}
	
	
	private static  String extractName(String fullName){
		
		return fullName.substring(0, fullName.indexOf("."));
	}
	
	private static String extractExtension(String fullName){
		return fullName.substring(fullName.indexOf("."));
	}
	

	public static String inputToHash(InputStream input) throws IOException{
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final byte[] data = new byte[1024];
	
		for(int read = 0;(read = input.read(data)) != -1;){
			digest.update(data, 0, read);
		}
		
		return  DatatypeConverter.printHexBinary(digest.digest());

}

	
	
	
}
