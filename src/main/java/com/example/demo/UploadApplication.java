package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Random;

@SpringBootApplication
@Controller
public class UploadApplication {

	@GetMapping(value = {"/","/Home"})
	public String home(){
		return "index";
	}

	@PostMapping(value = "/upload")
	public String upload(@RequestParam("file") MultipartFile file) throws IOException {

		String type[] = {"jpg","jpeg","png"};

		boolean match = false;

		for (String d:type) {
			if (file.getContentType().contains(d)){
				match = true;
			}
		}

		if (match) {

			String dir = "C:\\Users\\Simphiwe\\Documents\\Upload\\src\\main\\resources\\static\\upload\\";
			file.transferTo(new File(dir, getSaltString() + ".png"));
			System.out.println("file match type");

		}
		else{
			System.out.println("no match file type");
		}

		return "index";
	}


	public static void main(String[] args) {
		SpringApplication.run(UploadApplication.class, args);
	}

        public 	String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 18) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

}
