package io.github.luiseduardobrito.angelhack.drawer;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

@EViewGroup(android.R.layout.simple_list_item_1)
public class DrawerUserDetailsItem extends RelativeLayout {

	@ViewById(android.R.id.text1)
	TextView label;

	public DrawerUserDetailsItem(Context context) {
		super(context);
	}

	public void bind(String value) {
		this.label.setText(value);
		this.label.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {	}
		});
	}

	public void bind(String value, OnClickListener l) {
		this.label.setText(value);
		this.label.setOnClickListener(l);
	}
}
