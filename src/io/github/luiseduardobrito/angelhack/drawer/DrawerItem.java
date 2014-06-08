package io.github.luiseduardobrito.angelhack.drawer;

import io.github.luiseduardobrito.angelhack.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
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
		this.label.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
	}

	/**
	 * Bind label to item
	 * 
	 * @param label
	 */
	public void bind(String label, Drawable left) {
		bind(label, false);
		this.label.setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
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
