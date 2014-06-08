package io.github.luiseduardobrito.angelhack.drawer;

import io.github.luiseduardobrito.angelhack.R;
import io.github.luiseduardobrito.angelhack.UserState;
import io.github.luiseduardobrito.angelhack.activity.ProfileActivity;
import io.github.luiseduardobrito.angelhack.anim.ResizeAnimation;
import io.github.luiseduardobrito.angelhack.model.User;

import java.util.Observable;
import java.util.Observer;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import com.parse.ParseException;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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

	@ViewById(R.id.profile_details_list)
	ListView profileDetailsList;

	Boolean detailState = false;

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

			try {
				name.setText(current.getName());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (userState.getCompany() != null) {
				group.setText(userState.getCompany().getName());
			} else {
				group.setText("");
			}
		}
	}

	@Click(R.id.avatar)
	void avatar() {
		Intent i = new Intent(getContext(), ProfileActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		getContext().startActivity(i);
	}

	@Click(R.id.avatar_layout)
	void avatarLayout() {
		avatar();
	}

	@Click(R.id.more)
	void more() {

		int duration = 600;

		less.setVisibility(View.VISIBLE);
		more.setVisibility(View.GONE);
		detailState = false;

		int width = details.getWidth();
		int height = details.getHeight();

		ResizeAnimation resizeAnimation = new ResizeAnimation(details, width,
				height, width, 264);
		resizeAnimation.setDuration(duration);
		details.startAnimation(resizeAnimation);
	}

	@Click(R.id.less)
	void less() {

		int duration = 600;

		less.setVisibility(View.GONE);
		more.setVisibility(View.VISIBLE);
		detailState = true;

		int width = details.getWidth();
		int height = details.getHeight();

		profileDetailsList.getMeasuredHeight();

		ResizeAnimation resizeAnimation = new ResizeAnimation(details, width,
				height, width, 0);
		resizeAnimation.setDuration(duration);
		details.startAnimation(resizeAnimation);
	}
}
