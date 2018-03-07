package Utility;

import javafx.scene.control.TextField;

public class LimitedTextField extends TextField {
    private int maxlength;

    public LimitedTextField() {
        this.maxlength = 15;
    }

    public void setMaxlength(int maxlength) {
        this.maxlength = maxlength;
    }

    @Override
    public void replaceText(int start, int end, String text) {
        if (text.equals("")) {
            super.replaceText(start, end, text);
        } else if (!text.matches("[\\h\\s\\p{Punct}]") && getText().length() < maxlength) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text) {
        if (text.equals("")) {
            super.replaceSelection(text);
        } else if (!text.matches("[\\h\\s\\p{Punct}]") && getText().length() < maxlength) {
            OnlyValid(text);
            if (text.length() > maxlength - getText().length()) {
                text = text.substring(0, maxlength - getText().length());
            }
            super.replaceSelection(text);
        }
    }

    @Override
    public void paste() {
    }

    private String OnlyValid(String text) {
        String[] Text = text.split("[\\h\\s\\p{Punct}]");
        text = "";
        for (String onlynumbers : Text) if (!onlynumbers.isEmpty()) text += onlynumbers;
        return text;
    }
}