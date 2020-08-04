package bo.com.mondongo.harbor.payload.response;

public class OptionResponse {
    private String text;
    private int value;

    public OptionResponse(String text, int value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public int getValue() {
        return value;
    }
}
