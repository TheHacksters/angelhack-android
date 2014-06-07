package io.github.luiseduardobrito.androidboilerplate.drawer;

import io.github.luiseduardobrito.androidboilerplate.R;
import io.github.luiseduardobrito.androidboilerplate.model.User;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

@EViewGroup(R.layout.drawer_user_profile)
public class DrawerUserProfile extends LinearLayout {

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
	 * Bind user to drawer item
	 * 
	 * @param user
	 */
	public void bind(User user) {
		bind(user.getName(), "Example Inc.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * io.github.luiseduardobrito.androidboilerplate.drawer.DrawerItem#bind(
	 * java.lang.String)
	 */
	public void bind(String label) {
		this.name.setText(label);
	}

	/**
	 * Bind user name and group
	 * 
	 * @param label
	 * @param group
	 */
	public void bind(String label, String group) {
		this.name.setText(label);
		this.group.setText(group);
	}
}
