package io.github.luiseduardobrito.angelhack.activity;

import io.github.luiseduardobrito.angelhack.Prefs_;
import io.github.luiseduardobrito.angelhack.R;
import io.github.luiseduardobrito.angelhack.UserState;
import io.github.luiseduardobrito.angelhack.fragment.CoworkerListFragment;
import io.github.luiseduardobrito.angelhack.fragment.CoworkerListFragment_;
import io.github.luiseduardobrito.angelhack.fragment.DrawerFragment;
import io.github.luiseduardobrito.angelhack.fragment.FeedFragment;
import io.github.luiseduardobrito.angelhack.fragment.FeedFragment_;
import io.github.luiseduardobrito.angelhack.model.User;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.sharedpreferences.Pref;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

@OptionsMenu(R.menu.main)
@EActivity(R.layout.activity_main)
public class MainActivity extends Activity implements
		DrawerFragment.NavigationDrawerCallbacks {

	UserState userState = UserState.getInstance();

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private DrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@Pref
	Prefs_ prefs;

	/**
	 * Initializes activity.
	 * 
	 * IMPORTANT: Called before views
	 */
	@AfterInject
	void init() {

		setTitle("");

		User me = userState.getCurrent();

		if (me == null) {

			String email = prefs.email().get();

			if (email != null) {
				LoginActivity_.intent(getApplicationContext())
						.mExtraEmail(email)
						.flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
			}

			else {
				LoginActivity_.intent(getApplicationContext())
						.flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
			}
		}
	}

	/**
	 * Initializes activity.
	 * 
	 * IMPORTANT: Called after views
	 */
	@AfterViews
	void initViews() {

		mNavigationDrawerFragment = (DrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * io.github.luiseduardobrito.androidboilerplate.fragment.DrawerFragment
	 * .NavigationDrawerCallbacks#onNavigationDrawerItemSelected(int)
	 */
	@Override
	public void onNavigationDrawerItemSelected(int position) {

		if (position == 0) {
			FeedFragment fragment = FeedFragment_.builder().build();
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.container, fragment).commit();
		}

		else if (position == 1) {
			CoworkerListFragment fragment = CoworkerListFragment_.builder()
					.build();
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.container, fragment).commit();
		}
	}

	/**
	 * @param number
	 */
	public void onSectionAttached(int number) {
		mTitle = getString(R.string.app_name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		switch (id) {

		case android.R.id.home:
			mNavigationDrawerFragment.toggle();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	/**
	 * Restore actionBar status
	 */
	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle("");
	}

	@OptionsItem(R.id.action_logout)
	void settings() {
		User.logOut();
		this.finish();
	}
}
