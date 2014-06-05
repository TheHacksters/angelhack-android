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
	 * UI References
	 */
	@ViewById(R.id.name)
	TextView name;

	@ViewById(R.id.group)
	TextView group;

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
		bind(user.getName(), "Example Inc.");
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

	@Override
	public void setLabelSelected(Boolean selected) {
		return;
	}
}
