package io.github.luiseduardobrito.androidboilerplate.drawer;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

@EViewGroup(android.R.layout.simple_list_item_1)
public class DrawerItem extends RelativeLayout {

	@ViewById(android.R.id.text1)
	TextView label;

	public DrawerItem(Context context) {
		super(context);
	}

	public void bind(String label) {
		this.label.setText(label);
	}
}
