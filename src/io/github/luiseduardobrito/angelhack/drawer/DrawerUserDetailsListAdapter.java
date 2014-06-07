package io.github.luiseduardobrito.angelhack.drawer;

import io.github.luiseduardobrito.angelhack.UserState;
import io.github.luiseduardobrito.angelhack.activity.ProfileActivity;

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

@EBean(scope = Scope.Singleton)
public class DrawerUserDetailsListAdapter extends BaseAdapter implements
		Observer {

	UserState userState = UserState.getInstance();

	String[] labels = { "Perfil", "Company 1", "Company 2" };

	@RootContext
	Context context;

	@AfterInject
	void init() {
		userState.addObserver(this);
	}

	@Override
	public int getCount() {
		return labels.length;
	}

	@Override
	public Object getItem(int position) {
		return labels[position];
	}

	@Override
	public long getItemId(int position) {
		return 0;
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

		switch (position) {
		case 0:
			item.bind((String) getItem(position), new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent i = new Intent(context, ProfileActivity.class);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(i);
				}
			});

			break;
		default:
			item.bind((String) getItem(position));
			break;
		}

		return item;
	}

	@Override
	public void update(Observable observable, Object data) {
		// TODO
	}
}
