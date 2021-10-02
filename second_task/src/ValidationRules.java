public class ValidationRules {
    private int length;
    private String prefix;

    public ValidationRules(int length, String prefix) {
        this.length = length;
        this.prefix = prefix;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
