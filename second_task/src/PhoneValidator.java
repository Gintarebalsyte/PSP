import java.util.HashMap;

public class PhoneValidator {
    private HashMap<String, ValidationRules> validationRules = new HashMap<>(){{
        put("LT", new ValidationRules(12, "+370"));
    }};

    public void addValidationRule(String country, int length, String prefix) throws NullPointerException {
        if(country == null || prefix == null)
            throw new NullPointerException("Cannot invoke this method because passed parameter(s) is null.");
        validationRules.put(country, new ValidationRules(length, prefix));
    }

    public boolean hasOnlyNumbers(String phone) throws NullPointerException{
            if(phone == null)
                throw new NullPointerException("Cannot invoke this method because passed parameter is null.");
            if (phone.isEmpty())
                return false;

            phone = phone.trim();
            if(phone.startsWith("+"))
                phone = phone.substring(1);

            for (int i = 0; i < phone.length(); i++) {
                if (!Character.isDigit(phone.charAt(i)))
                    return false;
            }
        return true;
    }

    public String changePrefix(String phone) throws NullPointerException{
        if(phone == null)
            throw new NullPointerException("Cannot invoke this method because passed parameter is null.");

        if(phone.startsWith("8")){
            return "+370" + phone.substring(1);
        }
        return phone;
    }

    public boolean isCorrectLength(String country, String phone)  throws NullPointerException{
        if(country == null || phone == null)
            throw new NullPointerException("Cannot invoke this method because passed parameter(s) is null.");

        if(!validationRules.containsKey(country)){
            throw new NullPointerException("Country does not exist in the list");
        }

        int length = validationRules.get(country).getLength();
        return phone.length() == length;
    }

    public boolean hasCorrectPrefix(String country, String phone) throws NullPointerException{
        if(country == null || phone == null)
            throw new NullPointerException("Cannot invoke this method because passed parameter(s) is null.");

        if(!validationRules.containsKey(country)){
            throw new NullPointerException("Country does not exist in the list");
        }

        String prefix = validationRules.get(country).getPrefix();
        return phone.startsWith(prefix);
    }
}