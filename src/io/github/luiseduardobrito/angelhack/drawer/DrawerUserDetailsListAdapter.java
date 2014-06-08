package io.github.luiseduardobrito.angelhack.drawer;

import io.github.luiseduardobrito.angelhack.R;
import io.github.luiseduardobrito.angelhack.UserState;
import io.github.luiseduardobrito.angelhack.activity.ChooseCompanyActivity_;
import io.github.luiseduardobrito.angelhack.activity.CreateCompanyActivity_;
import io.github.luiseduardobrito.angelhack.activity.ProfileActivity;
import io.github.luiseduardobrito.angelhack.model.Company;
import io.github.luiseduardobrito.angelhack.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EBean.Scope;
import org.androidannotations.annotations.RootContext;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.parse.ParseException;

@EBean(scope = Scope.Singleton)
public class DrawerUserDetailsListAdapter extends BaseAdapter implements
		Observer {

	UserState userState = UserState.getInstance();
	List<String> labels;

	@RootContext
	Context context;

	@AfterInject
	void init() {
		userState.addObserver(this);
		update(null, null);
	}

	@Override
	public int getCount() {
		return labels.size();
	}

	@Override
	public Object getItem(int position) {
		return labels.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		DrawerUserDetailsItem item;

		if (convertView != null) {
			item = (DrawerUserDetailsItem) convertView;
		}

		else {
			item = DrawerUserDetailsItem_.build(context);
		}

		if (position == 0) {

			item.bind((String) getItem(position), new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent i = new Intent(context, ProfileActivity.class);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(i);
				}
			}, context.getResources().getDrawable(R.drawable.ic_profile));
		}

		else if (position == getCount() - 1) {
			item.bind((String) getItem(position), new OnClickListener() {

				@Override
				public void onClick(View v) {
					CreateCompanyActivity_.intent(context)
							.flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
				}
			}, context.getResources().getDrawable(R.drawable.ic_add_company));
		}

		else {
			item.bind((String) getItem(position), new OnClickListener() {

				@Override
				public void onClick(View v) {
					ChooseCompanyActivity_.intent(context)
							.flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
				}

			}, context.getResources().getDrawable(R.drawable.ic_company));
		}

		return item;
	}

	@Override
	public void update(Observable observable, Object data) {

		labels = new ArrayList<String>();
		labels.add("Profile");

		User me = userState.getCurrent();

		try {
			if (me != null && me.getCompanies() != null) {

				for (Company company : userState.getCurrent().getCompanies()) {
					company.fetchIfNeeded();
					labels.add(company.getName());

				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		labels.add("Create a new company");

	}
}
