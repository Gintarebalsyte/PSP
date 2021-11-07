import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PasswordValidator {
    private String specialChars = "";

    public PasswordValidator() {
        try {
            readSpecialCharacters();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean isCorrectLength(int length, String password) throws NullPointerException{
        if(password == null)
            throw new NullPointerException("Cannot invoke this method because passed parameter is null.");
        return password.length() >= length;
    }

    public boolean hasUppercase(String password) throws NullPointerException{
        if (password == null)
            throw new NullPointerException("Cannot invoke this method because passed parameter is null.");
        return !password.equals(password.toLowerCase());
    }

    public boolean hasSpecialSymbols(String password, String specialChars) throws NullPointerException{
        if (password == null || specialChars == null)
            throw new NullPointerException("Cannot invoke this method because passed parameter(s) is null.");

        for (int i = 0; i < specialChars.length() ; i++) {
            if(password.contains(Character.toString(specialChars.charAt(i))))
                return true;
        }
        return false;
    }

//    overloaded method that does not take specialChars as a parameter
    public boolean hasSpecialSymbols(String password) throws NullPointerException{
        if (password == null)
            throw new NullPointerException("Cannot invoke this method because passed parameter is null.");

        for (int i = 0; i < specialChars.length() ; i++) {
            if(password.contains(Character.toString(specialChars.charAt(i))))
                return true;
        }
        return false;
    }

//    helper methods
    private void readSpecialCharacters() throws FileNotFoundException {
        String fileName = "second_task/src/resources/SpecialCharacters.txt";

        try (var scanner = new Scanner(new File(fileName))) {

            while (scanner.hasNext()) {
                specialChars += scanner.nextLine();
            }
        }
    }
}
