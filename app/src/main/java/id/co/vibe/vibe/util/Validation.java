package id.co.vibe.vibe.util;

import android.text.TextUtils;
import android.util.Patterns;

public class Validation {

    public boolean isEmailValid(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public boolean isStringEmpty(CharSequence target) {
        return TextUtils.isEmpty(target);
    }

    public String getEmailIsNotValidMessage() {
        return "Email is not valid";
    }

    public String getFieldIsRequiredMessage() {
        return "This field is required";
    }

    public String getFieldIsRequiredMessage(String fieldName) {
        return TextUtils.concat(fieldName, " ", "is required").toString();
    }

}
