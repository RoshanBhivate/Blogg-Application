package com.blog.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.service.FileService;

@Service
public class FileserviceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {

		// for filename
		String name = file.getOriginalFilename();
		// sample.png

		// for random name gnerate file
		String randomId = UUID.randomUUID().toString();
		String fileName1 = randomId.concat(name.substring(name.lastIndexOf(".")));

//for full path
		String filePath = path + File.separator + fileName1;

//for creating folder if folder is not created
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}

//for copying file
		Files.copy(file.getInputStream(), Paths.get(filePath));

		return fileName1;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath = path + File.separator + fileName;
		InputStream is = new FileInputStream(fullPath);
		return is;
	}

}
