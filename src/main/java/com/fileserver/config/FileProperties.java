package com.fileserver.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("file")
public class FileProperties {

	private String storagePath;

	public void setStoragePath(String storagePath) {
		this.storagePath = storagePath;
	}

	public Path folder(int hash) throws IOException {
		return Paths.get(generateHashFolder(hash));

	}

	private String generateHashFolder(int hash) throws IOException {

		int firstDir = hash & 255;
		int secondDir = (hash >> 8) & 255;

		String path = new StringBuffer(File.separator).append(String.format("%02x", firstDir)).append(File.separator)
				.append(String.format("%02x", secondDir)).toString();

		Path filePath = Paths.get(this.storagePath, path);

		if (!Files.exists(filePath))
			Files.createDirectories(filePath);

		return filePath.toString();

	}

}
