package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@RestController
public class HelloController {

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!!";
	}


	@RequestMapping(value = "/get")
	public String get(String param) {
		return "You entered: " + param;
	}

    public class UploadForm 
    {
        public String path = "";

        public UploadForm() {
            // TODO Auto-generated constructor stub
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }


	@RequestMapping(value = "/post")
    public String post(@ModelAttribute UploadForm form) 
    {
        return "hello " + form.path;
    }




}
