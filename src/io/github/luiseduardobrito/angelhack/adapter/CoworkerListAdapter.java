package io.github.luiseduardobrito.angelhack.adapter;

import io.github.luiseduardobrito.angelhack.UserState;
import io.github.luiseduardobrito.angelhack.model.Company;
import io.github.luiseduardobrito.angelhack.model.User;
import io.github.luiseduardobrito.angelhack.view.CoworkerItemView;
import io.github.luiseduardobrito.angelhack.view.CoworkerItemView_;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EBean.Scope;
import org.androidannotations.annotations.RootContext;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.parse.ParseException;

@EBean(scope = Scope.Singleton)
public class CoworkerListAdapter extends BaseAdapter implements Observer {

	UserState userState = UserState.getInstance();

	@RootContext
	Context context;

	User current;
	OnClickListener listener;
	List<User> list;

	@AfterInject
	void init() {
		UserState.getInstance().addObserver(this);
		update(null, null);
	}

	public void setOnClickListener(OnClickListener l) {
		listener = l;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		CoworkerItemView view;

		if (convertView != null) {
			view = (CoworkerItemView) convertView;
		} else {
			view = CoworkerItemView_.build(context);
		}

		final int fPosition = position;
		view.bind((User) getItem(fPosition), new OnClickListener() {

			@Override
			public void onClick(View v) {

				userState.setCompany((Company) getItem(fPosition));

				if (listener != null) {
					listener.onClick(null);
				}
			}
		});

		return view;
	}

	@Override
	public void update(Observable observable, Object data) {
		try {
			list = userState.getCompany().getCoworkers();
			notifyDataSetChanged();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
