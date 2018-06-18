package id.co.vibe.vibe.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import id.co.vibe.vibe.R;
import id.co.vibe.vibe.VibeApplication;

public abstract class BaseFragment extends Fragment implements BaseView {

    protected ProgressDialog progressDialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        injectDependencies(VibeApplication.get(context));
    }

    protected abstract void injectDependencies(VibeApplication application);

    protected abstract void releaseSubComponents(VibeApplication application);

    @Override
    public void showToast(String message) {

    }

    @Override
    public void hideKeyboard() {

    }

    @Override
    public void showProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage(getString(R.string.string_loading));
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
        progressDialog = null;
    }

    @Override
    public void showAlertDialogWithOkButtonAndNullClickListener(String message) {
        if (getContext() != null) {
            new AlertDialog.Builder(getContext())
                    .setCancelable(false)
                    .setMessage(message)
                    .setPositiveButton(getString(R.string.string_ok), null)
                    .create()
                    .show();
        }
    }

    @Override
    public void goToLoginActivity() {

    }

    @Override
    public void goToMainActivity() {

    }

}
