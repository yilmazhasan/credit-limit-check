package com.example.springboot.service;

import com.example.springboot.model.UserForm;
import java.util.HashMap;


public class CreditUtil {

        public static int CREDIT_LIMIT_MULTIPLIER = 4;

        public static HashMap<String, String> checkCredit(UserForm form) {
            int amount = 0;
            String status = "";
        
            int creditScore = getCreditScore(form.tckn);
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


    public static int getCreditScore(String tckn) {

        if(tckn.equals("11111111111")) {
            return 300;
        } else {
            if(tckn.equals( "22222222222")) {
                return 600;
            } else if(tckn.equals("33333333333")){
                return 1600;
            }
        }

        return 0;
    }


}