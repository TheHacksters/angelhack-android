package io.github.luiseduardobrito.angelhack.drawer;

import io.github.luiseduardobrito.angelhack.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

@EViewGroup(R.layout.drawer_profile_item)
public class DrawerUserDetailsItem extends RelativeLayout {

	@ViewById(R.id.label)
	TextView label;

	public DrawerUserDetailsItem(Context context) {
		super(context);
	}

	public void bind(String value) {
		this.label.setText(value);
		this.label.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
		this.label.setCompoundDrawablesWithIntrinsicBounds(null, null, null,
				null);
	}

	public void bind(String value, Drawable left) {
		this.label.setText(value);
		this.label.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
		this.label.setCompoundDrawablesWithIntrinsicBounds(left, null, null,
				null);
	}

	public void bind(String value, OnClickListener l) {
		this.label.setText(value);
		this.label.setOnClickListener(l);
		this.label.setCompoundDrawablesWithIntrinsicBounds(null, null, null,
				null);
	}

	public void bind(String value, OnClickListener l, Drawable left) {
		this.label.setText(value);
		this.label.setOnClickListener(l);
		this.label.setCompoundDrawablesWithIntrinsicBounds(left, null, null,
				null);
	}
}
