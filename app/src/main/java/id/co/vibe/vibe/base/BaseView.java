package id.co.vibe.vibe.base;

/**
 * Created by alvinoktavianus (https://www.linkedin.com/in/alvinoktavianus)
 * on 6/17/2018
 */

public interface BaseView {
    void showToast(String message);

    void hideKeyboard();

    void showProgressDialog();

    void hideProgressDialog();

    void showAlertDialogWithOkButtonAndNullClickListener(String message);

    void goToLoginActivity();

    void goToMainActivity();
}
