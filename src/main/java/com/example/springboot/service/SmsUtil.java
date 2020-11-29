package com.example.springboot.service;

public class SmsUtil {
    public static boolean sendSMS(String phone, String status, int amount) {

        if(phone.equals("") || status.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isPhoneValid(String phone) {
        if(phone.length() == 10 || (phone.length() == 11 && phone.charAt(0) == 0)) {
            return true;
        } else {
            return false;
        }
    }

} 
