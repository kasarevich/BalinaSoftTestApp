package com.balinasoft.balinasoftapp.utils;

public class Validation {

    public static boolean checkLogin(String login){
        final String emailPattern = "^(?=.{4,32})[a-z][a-z0-9]*[._-]?[a-z0-9]+$";
        return (login.matches(emailPattern));
    }

    public static boolean checkPassword(String password){
        return (password.length() >= 8);
    }

    public static boolean checkForRepeat(String pass1, String pass2){
       return (pass1.equals(pass2));
    }

}
