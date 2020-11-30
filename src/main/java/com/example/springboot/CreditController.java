package com.example.springboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.springboot.model.UserForm;
import com.example.springboot.service.SmsUtil;
import com.example.springboot.service.CreditUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CreditController {

	@RequestMapping(value="/credit", method = RequestMethod.GET)
	public String index() {
		return "Wellcome to Credit Controller!";
	}

	@RequestMapping(value = "/credit/check", method = RequestMethod.POST)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Object> check(@RequestBody UserForm form) // @ModelAttribute 
    {
        HashMap<String, String> respMap = CreditUtil.checkCredit(form);

        return new ResponseEntity<Object>(respMap, HttpStatus.OK);
    }

}
