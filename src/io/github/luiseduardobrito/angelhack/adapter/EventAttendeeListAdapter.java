package io.github.luiseduardobrito.angelhack.adapter;

import io.github.luiseduardobrito.angelhack.model.User;
import io.github.luiseduardobrito.angelhack.view.EventAttendeeItemView;
import io.github.luiseduardobrito.angelhack.view.EventAttendeeItemView_;

import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EBean.Scope;
import org.androidannotations.annotations.RootContext;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

@EBean(scope = Scope.Singleton)
public class EventAttendeeListAdapter extends BaseAdapter {

	List<User> list;

	@RootContext
	Context context;

	@AfterInject
	void init() {
		list = new ArrayList<User>();
	}

	public void clear() {
		list.clear();
	}

	public List<User> getList() {
		return list;
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

		EventAttendeeItemView view;

		if (convertView != null) {
			view = (EventAttendeeItemView) convertView;
		} else {
			view = EventAttendeeItemView_.build(context);
		}

		view.bind((User) getItem(position));
		return view;
	}

	public void setList(List<User> coworkers) {
		list = coworkers;
	}

	public void remove(User fUser) {
		list.remove(fUser);
	}

	public void add(User u) {
		list.add(u);
		notifyDataSetChanged();
	}
}
