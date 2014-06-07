package io.github.luiseduardobrito.angelhack.activity;

import io.github.luiseduardobrito.angelhack.R;
import io.github.luiseduardobrito.angelhack.drawer.DrawerItemAdapter;

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

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
@OptionsMenu(R.menu.login)
@EActivity(R.layout.activity_login)
public class LoginActivity extends Activity {

	public static class TimePickerFragment extends DialogFragment implements
			TimePickerDialog.OnTimeSetListener {

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

						}

					}, year, month, day);

			dialog.getDatePicker().setMaxDate(new Date().getTime());
			return dialog;
		}

		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// Do something with the time chosen by the user
		}
	}

	/**
	 * Extra email from Intent
	 */
	@Extra("io.github.luiseduardobrito.angelhack.EMAIL")
	String mExtraEmail;

	@Bean
	DrawerItemAdapter drawerAdapter;

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
		mSignupPasswordView
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
				drawerAdapter.update();
				showProgress(false);
			}

		}, animTime);
	}

	/**
	 * Perform login in background
	 */
	@Background
	void performLogin(String email, String password) {

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
				drawerAdapter.update();
				showProgress(false);
			}

		}, animTime);
	}

	@Click(R.id.birthday_button)
	void showBirthdayPicker() {
		DialogFragment newFragment = new TimePickerFragment();
		newFragment.show(getFragmentManager(), "timePicker");
	}

	/**
	 * Perform login in background
	 */
	@Background
	void performSignup(String name, String email, String password, Date birthDay) {

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
