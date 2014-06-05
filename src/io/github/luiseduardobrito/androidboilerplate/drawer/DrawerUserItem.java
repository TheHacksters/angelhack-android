package io.github.luiseduardobrito.androidboilerplate.drawer;

import io.github.luiseduardobrito.androidboilerplate.R;
import io.github.luiseduardobrito.androidboilerplate.model.User;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.widget.TextView;

@EViewGroup(R.layout.drawer_user_item)
public class DrawerUserItem extends DrawerItem {

	/**
	 * UI Reference
	 */
	@ViewById(R.id.label)
	TextView label;

	/**
	 * Constructor
	 * 
	 * @param context
	 */
	public DrawerUserItem(Context context) {
		super(context);
	}

	/**
	 * Bind user to drawer item
	 * 
	 * @param user
	 */
	public void bind(User user) {
		bind(user.getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * io.github.luiseduardobrito.androidboilerplate.drawer.DrawerItem#bind(
	 * java.lang.String)
	 */
	@Override
	public void bind(String label) {
		this.label.setText(label);
	}
}
