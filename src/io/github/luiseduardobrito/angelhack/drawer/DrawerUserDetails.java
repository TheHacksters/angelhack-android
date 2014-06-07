package io.github.luiseduardobrito.angelhack.drawer;

import io.github.luiseduardobrito.angelhack.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
import android.widget.RelativeLayout;

@EViewGroup(R.layout.drawer_user_details)
public class DrawerUserDetails extends RelativeLayout {

	@Bean
	DrawerUserDetailsListAdapter adapter;

	@ViewById(R.id.profile_details_list)
	ListView list;

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

	@AfterViews
	void initViews() {
		list.setAdapter(adapter);
	}
}
