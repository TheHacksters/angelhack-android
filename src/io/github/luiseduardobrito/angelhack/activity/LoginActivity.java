package io.github.luiseduardobrito.angelhack.activity;

import io.github.luiseduardobrito.angelhack.Prefs_;
import io.github.luiseduardobrito.angelhack.R;
import io.github.luiseduardobrito.angelhack.UserState;
import io.github.luiseduardobrito.angelhack.drawer.DrawerItemAdapter;
import io.github.luiseduardobrito.angelhack.exception.AppException;
import io.github.luiseduardobrito.angelhack.model.User;

import java.util.Calendar;
import java.util.Date;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
@OptionsMenu(R.menu.login)
@EActivity(R.layout.activity_login)
public class LoginActivity extends Activity {

	private static Date birthDateFromPicker;

	public static class DatePickerFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {

			// Use the current time as the default values for the picker

			final Calendar c = Calendar.getInstance();

			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);

			// Create a new instance of TimePickerDialog and return it
			DatePickerDialog dialog = new DatePickerDialog(getActivity(),
					new OnDateSetListener() {

						@Override
						public void onDateSet(DatePicker view, int year,
								int monthOfYear, int dayOfMonth) {
							Calendar cal = Calendar.getInstance();
							cal.set(Calendar.YEAR, year);
							cal.set(Calendar.MONTH, monthOfYear);
							cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
							birthDateFromPicker = cal.getTime();
						}

					}, year, month, day);

			dialog.getDatePicker().setMaxDate(new Date().getTime());
			return dialog;
		}

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {

			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, monthOfYear);
			cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			birthDateFromPicker = cal.getTime();
		}
	}

	/**
	 * Extra email from Intent
	 */
	@Extra("io.github.luiseduardobrito.angelhack.EMAIL")
	String mExtraEmail;

	@Bean
	DrawerItemAdapter drawerAdapter;

	@Pref
	Prefs_ prefs;

	/**
	 * UI references
	 */
	@ViewById(R.id.email)
	EditText mEmailView;

	@ViewById(R.id.password)
	EditText mPasswordView;

	@ViewById(R.id.signup_name)
	EditText mSignupNameView;

	@ViewById(R.id.signup_email)
	EditText mSignupEmailView;

	@ViewById(R.id.signup_password)
	EditText mSignupPasswordView;

	@ViewById(R.id.signup_confirm_password)
	EditText mSignupConfirmPasswordView;

	@ViewById(R.id.login_form)
	View mLoginFormView;

	@ViewById(R.id.login_status)
	View mLoginStatusView;

	@ViewById(R.id.login_status_message)
	TextView mLoginStatusMessageView;

	/**
	 * Initialize views
	 */
	@AfterViews
	void initViews() {

		// Set extra email
		mEmailView.setText(mExtraEmail);
		mSignupEmailView.setText(mExtraEmail);

		// Set password editor action listener
		mPasswordView
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView v, int actionId,
							KeyEvent event) {
						if (actionId == R.id.login
								|| actionId == EditorInfo.IME_NULL) {

							attemptLogin();
							return true;
						}
						return false;
					}
				});

		// Set password editor action listener
		mSignupConfirmPasswordView
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView v, int actionId,
							KeyEvent event) {
						if (actionId == R.id.login
								|| actionId == EditorInfo.IME_NULL) {
							attemptSignup();
							return true;
						}
						return false;
					}
				});
	}

	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	@UiThread
	@Click(R.id.signin_button)
	void attemptLogin() {

		// Get short time animation
		final int animTime = getResources().getInteger(
				android.R.integer.config_mediumAnimTime) * 5;

		// Show login progress
		showProgress(true);

		// Prepare handler to call sign in
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {

				String email = mEmailView.getText().toString();
				String password = mPasswordView.getText().toString();

				performLogin(email, password);
			}

		}, animTime);
	}

	/**
	 * Perform login in background
	 */
	@Background
	void performLogin(String email, String password) {

		try {
			showResult((User) User.logIn(email, password));
		} catch (ParseException e) {
			showError(e);
		}
	}

	/**
	 * Attempts to register the account specified by the login form. If there
	 * are form errors (invalid email, missing fields, etc.), the errors are
	 * presented and no actual login attempt is made.
	 */
	@UiThread
	@Click(R.id.signup_button)
	void attemptSignup() {

		// Get short time animation
		final int animTime = getResources().getInteger(
				android.R.integer.config_mediumAnimTime) * 5;

		// Show login progress
		showProgress(true);

		// Prepare handler to call sign in
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {

				String name = mSignupNameView.getText().toString();
				String email = mSignupEmailView.getText().toString();
				String password = mSignupPasswordView.getText().toString();
				String confirmPassword = mSignupConfirmPasswordView.getText()
						.toString();

				if (password.equals(confirmPassword)) {
					performSignup(name, email, password, birthDateFromPicker);
				} else {
					showError("Password and its confirmation must match");
				}
			}

		}, animTime);
	}

	@Click(R.id.birthday_button)
	void showBirthdayPicker() {
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getFragmentManager(), "timePicker");
	}

	/**
	 * Perform login in background
	 */
	@Background
	void performSignup(String name, String email, String password, Date birthDay) {

		if (birthDateFromPicker == null) {
			showError("Birth date not specified");
			return;
		}

		try {
			showResult(User.signUp(name, email, password, birthDateFromPicker));
		} catch (ParseException e) {
			showError(e);
		} catch (AppException e) {
			showError(e);
		}
	}

	/**
	 * Show auth result based on User
	 * 
	 * @param e
	 */
	@UiThread
	void showResult(User user) {

		prefs.email().put(user.getEmail());
		UserState.getInstance().update();

		drawerAdapter.update();
		showProgress(false);

		ChooseCompanyActivity_.intent(this).start();
		this.finish();
	}

	/**
	 * Show auth error based on ParseException
	 * 
	 * @param e
	 */
	@UiThread
	void showError(ParseException e) {
		showError(e.getMessage());
	}

	/**
	 * Show auth error based on AppException
	 * 
	 * @param e
	 */
	@UiThread
	void showError(AppException e) {
		showError(e.getMessage());
	}

	/**
	 * Show auth error based on string
	 * 
	 * @param e
	 */
	@UiThread
	void showError(String msg) {

		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

		drawerAdapter.update();
		showProgress(false);
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@UiThread
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	void showProgress(final boolean show) {

		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			mLoginStatusView.setVisibility(View.VISIBLE);
			mLoginStatusView.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginStatusView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});

			mLoginFormView.setVisibility(View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginFormView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});
		}

		else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}
}
