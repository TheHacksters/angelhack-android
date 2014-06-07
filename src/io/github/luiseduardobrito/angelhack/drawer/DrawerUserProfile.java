package io.github.luiseduardobrito.angelhack.drawer;

import io.github.luiseduardobrito.angelhack.R;
import io.github.luiseduardobrito.angelhack.UserState;
import io.github.luiseduardobrito.angelhack.model.User;

import java.util.Observable;
import java.util.Observer;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

@EViewGroup(R.layout.drawer_user_profile)
public class DrawerUserProfile extends LinearLayout implements Observer {

	UserState userState = UserState.getInstance();

	/**
	 * UI References
	 */
	@ViewById(R.id.name)
	TextView name;

	@ViewById(R.id.group)
	TextView group;

	@ViewById(R.id.more)
	ImageView more;

	@ViewById(R.id.less)
	ImageView less;

	@ViewById(R.id.details)
	View details;

	/**
	 * Constructor
	 * 
	 * @param context
	 */
	public DrawerUserProfile(Context context) {
		super(context);
	}

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param attrs
	 */
	public DrawerUserProfile(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public DrawerUserProfile(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

	}

	/**
	 * Initialize after annotations injection
	 */
	@AfterInject
	void init() {
		userState.addObserver(this);
	}

	/**
	 * Initialize after annotations views
	 */
	@AfterViews
	void initViews() {
		update(null, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable observable, Object data) {

		User current = userState.getCurrent();

		if (current != null) {
			name.setText(current.getName());
		}
	}
}
