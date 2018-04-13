package com.balinasoft.balinasoftapp.utils;

public class Validation {

    public static boolean checkLogin(String login){
        final String emailPattern = "^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@([a-z0-9]" +
                "([-a-z0-9]{0,61}[a-z0-9])?\\.)*" +
                "(aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$";
        return true;
       // return (login.matches(emailPattern));
    }

    public static boolean checkPassword(String password){
        return (password.length() >= 8);
    }

    public static boolean checkForRepeat(String pass1, String pass2){
       return (pass1.equals(pass2));
    }

}
