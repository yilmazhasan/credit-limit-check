package com.example.springboot.service;
import com.example.springboot.model.UserForm;
import java.util.HashMap;

public class DbUtil {
    public static boolean saveToDb(UserForm uf, HashMap<String, String> map) {
        String amount = map.get("amount");
        String smsSent = map.get("smsSent");
        return true;
    }
}