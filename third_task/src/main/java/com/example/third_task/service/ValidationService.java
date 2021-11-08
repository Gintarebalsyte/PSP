package com.example.third_task.service;

import com.company.Validator;
import com.example.third_task.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ValidationService {
    Validator validator = new Validator();

    public void validate(User user){
        if(!validator.passwordChecker(user.getPassword()))
            throw new IllegalArgumentException("Invalid password");

        if(!validator.phoneNumberChecker(user.getPhoneNumber(), "+370"))
            throw new IllegalArgumentException("Invalid phone number");

        if(!validator.emailChecker(user.getEmail()) && !hasValidDomain(user.getEmail()))
            throw new IllegalArgumentException("Invalid email address");
    }
    /*
    *   my own methods to fix email validator, since it's not accepting any valid email addresses
    */
    String specialChars = "!@#$%^&*-(),.";

    private boolean hasValidDomain(String email) throws NullPointerException {
        if(email == null)
            throw new NullPointerException("Cannot invoke this method because passed parameter is null.");

//        separate the email address at @ sign, keep the second half
        int index = email.indexOf('@');
        String temp = email.substring(index+1);

        ArrayList<String> domains = new ArrayList<>();
//        create an array of all domain parts
        index = temp.indexOf('.');
        while(index > -1){
            domains.add(temp.substring(0, index));
            temp = temp.substring(index+1);
            index = temp.indexOf('.');
        }

//      save the last part, which is TLD. If "temp" variable is empty, it already means invalid tld (it's non-existent, there was a "." at the end of last domain etc.)
        String tld;
        if (!temp.isBlank()) tld = temp;
        else return false;

//        check if 'domains' array is not empty - otherwise it's already invalid
        if(domains.isEmpty())
            return false;

        for (String domain : domains) {
            if(domain.isBlank() || domain.length() > 63)
                return false;
            if(invalidContent(domain))
                return false;
        }

        if(tld.length() < 2 || tld.length() > 6)
            return false;

        if(invalidContent(tld)) return false;

        return true;
    }

    private boolean invalidContent(String text){
        return text.startsWith("-") || text.endsWith("-") || containsSymbols(text);
    }

    private boolean containsSymbols(String text) {
        for (int i = 0; i < specialChars.length() ; i++) {
            String temp = Character.toString(specialChars.charAt(i));
            if(text.contains(temp) && !temp.equals("-")) //for email, hyphens (-) are allowed
                return true;
        }
        return false;
    }
}
