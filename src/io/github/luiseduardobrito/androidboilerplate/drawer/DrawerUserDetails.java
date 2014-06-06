package io.github.luiseduardobrito.androidboilerplate.drawer;

import io.github.luiseduardobrito.androidboilerplate.R;

import org.androidannotations.annotations.EViewGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

@EViewGroup(R.layout.drawer_user_details)
public class DrawerUserDetails extends RelativeLayout {

	/**
	 * Constructor
	 * 
	 * @param context
	 */
	public DrawerUserDetails(Context context) {
		super(context);
	}

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param attrs
	 */
	public DrawerUserDetails(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public DrawerUserDetails(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

}
