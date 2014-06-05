package io.github.luiseduardobrito.androidboilerplate.activity;

import io.github.luiseduardobrito.androidboilerplate.R;

import org.androidannotations.annotations.AfterViews;
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
import android.os.Build;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
@OptionsMenu(R.menu.login)
@EActivity(R.layout.activity_login)
public class LoginActivity extends Activity {

	/**
	 * Extra email from Intent
	 */
	@Extra("io.github.luiseduardobrito.androidboilerplate.EMAIL")
	String mExtraEmail;

	/**
	 * UI references
	 */
	@ViewById(R.id.email)
	EditText mEmailView;

	@ViewById(R.id.password)
	EditText mPasswordView;

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
	}

	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	@Click(R.id.sign_in_button)
	void attemptLogin() {

		// Get short time animation
		int animTime = getResources().getInteger(
				android.R.integer.config_mediumAnimTime);

		showProgress(true);

		// Prepare handler to call sign in
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				showProgress(false);
			}

		}, animTime * 3);
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
