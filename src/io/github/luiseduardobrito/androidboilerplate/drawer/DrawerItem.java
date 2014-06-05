package io.github.luiseduardobrito.androidboilerplate.drawer;

import io.github.luiseduardobrito.androidboilerplate.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.RelativeLayout;
import android.widget.TextView;

@EViewGroup(R.layout.drawer_item)
public class DrawerItem extends RelativeLayout {

	/**
	 * UI References
	 */
	@ViewById(R.id.label)
	TextView label;

	/**
	 * Constructor
	 * 
	 * @param context
	 */
	public DrawerItem(Context context) {
		super(context);
	}

	/**
	 * Bind label to item
	 * 
	 * @param label
	 */
	public void bind(String label) {
		bind(label, false);
	}

	/**
	 * Bind label to item
	 * 
	 * @param label
	 * @param selected
	 */
	public void bind(String label, Boolean selected) {
		this.label.setText(label);
		setSelected(selected);
	}

	/**
	 * Set item selection state
	 * 
	 * @param selected
	 */
	public void setLabelSelected(Boolean selected) {

		if (selected) {
			this.label.setTypeface(null, Typeface.BOLD);
		}

		else {
			this.label.setTypeface(null, Typeface.NORMAL);
		}
	}
}
