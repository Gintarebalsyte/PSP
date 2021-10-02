import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class EmailValidator {
    private String specialChars = "";

    public EmailValidator() {
        try {
            readSpecialCharacters();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean hasAtSymbol(String email) throws NullPointerException{
        if(email == null)
            throw new NullPointerException("Cannot invoke this method because passed parameter is null.");
        else return email.contains("@");
    }

//    Checks the username part only - domain is checked in 'hasValidDomain' method
    public boolean hasForbiddenSymbols(String email) throws NullPointerException {
        if(email == null)
            throw new NullPointerException("Cannot invoke this method because passed parameter is null.");

        String emailUsername;
        int index = email.indexOf('@');

        if(index > -1)
            emailUsername = email.substring(0, index);
        else return false; //the @ sign does not exist and the username part is considered to be non-existent

        return containsSymbols(emailUsername);
    }

    /*
    Conditions for valid domain:
        The domain name should be between 1 and 63 characters long.
        The TLD must be between 2 and 6 characters long.
        The domain name should not start or end with a hyphen(-).
        The domain name should be a-z or A-Z or 0-9 and hyphen (-) (no other special characters)
     */

    public boolean hasValidDomain(String email) throws NullPointerException {
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

//    Helper methods:
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

    private void readSpecialCharacters() throws FileNotFoundException {
        String fileName = "second_task/src/resources/SpecialCharacters.txt";

        try (var scanner = new Scanner(new File(fileName))) {

            while (scanner.hasNext()) {
                specialChars += scanner.nextLine();
            }
        }
    }
}