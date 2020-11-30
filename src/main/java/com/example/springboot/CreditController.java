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

import java.util.HashMap;
import java.util.Map;

@RestController
public class CreditController {

    public static int CREDIT_LIMIT_MULTIPLIER = 4;

	@RequestMapping(value="/credit", method = RequestMethod.GET)
	public String index() {
		return "Wellcome to Credit Controller!";
	}

	@RequestMapping(value = "/credit/check", method = RequestMethod.POST)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Object> check(@RequestBody UserForm form) // @ModelAttribute 
    {
        HashMap<String, String> respMap = getResponse(form);

        return new ResponseEntity<Object>(respMap, HttpStatus.OK);
    }

    public HashMap<String, String> getResponse(UserForm form) {
            int amount = 0;
            String status = "";
        
            String tckn = form.tckn;
            int creditScore = getCreditScore(tckn);
            int income = form.income;
            int totalScore = creditScore * income;

            if(creditScore < 500) {
                status = "reject";
            } else{
                status = "accept";

                if (creditScore < 1000) {
                    if(income < 5000) {
                        amount = 10000;
                    } else {
                        amount = 10000 + income * CREDIT_LIMIT_MULTIPLIER;
                    }
                } else {
                    amount = income * CREDIT_LIMIT_MULTIPLIER;
                }
            }

            boolean smsSent = false;
            if(SmsUtil.isPhoneValid(form.phone) && SmsUtil.sendSMS(form.phone, status, amount)) {
                smsSent = true;
            }
            
            HashMap<String, String> map = new HashMap<>();
            map.put("status", status);
            map.put("smsSent", String.valueOf(smsSent));
            map.put("amount", String.valueOf(amount));
            map.put("phone", String.valueOf(form.phone));

        return map;
    }

    public int getCreditScore(String tckn) {

        if(tckn.equals("1")) {
            return 300;
        } else {
            if(tckn.equals( "2")) {
                return 600;
            } else if(tckn.equals("3")){
                return 1600;
            }
        }

        return -1;
    }



}
