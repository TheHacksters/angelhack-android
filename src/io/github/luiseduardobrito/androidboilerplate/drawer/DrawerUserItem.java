package io.github.luiseduardobrito.androidboilerplate.drawer;

import io.github.luiseduardobrito.androidboilerplate.model.User;

import org.androidannotations.annotations.EViewGroup;

import android.content.Context;

@EViewGroup(android.R.layout.simple_list_item_1)
public class DrawerUserItem extends DrawerItem {

	public DrawerUserItem(Context context) {
		super(context);
	}

	public void bind(User user) {
		bind(user.getName());
	}
}
