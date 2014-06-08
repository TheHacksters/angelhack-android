package io.github.luiseduardobrito.angelhack.adapter;

import io.github.luiseduardobrito.angelhack.model.Event;
import io.github.luiseduardobrito.angelhack.view.EventItemView;
import io.github.luiseduardobrito.angelhack.view.EventItemView_;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EBean.Scope;
import org.androidannotations.annotations.RootContext;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

@EBean(scope = Scope.Singleton)
public class EventListAdapter extends BaseAdapter implements Observer {

	List<Event> list;

	@RootContext
	Context context;

	@AfterInject
	void init() {
		list = new ArrayList<Event>();
	}

	public void add(Event event) {
		list.add(event);
		notifyDataSetChanged();
	}

	public void remove(Event event) {
		list.remove(event);
		notifyDataSetChanged();
	}

	public void clear() {
		list.clear();
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

		EventItemView view;

		if (convertView != null) {
			view = (EventItemView) convertView;
		} else {
			view = EventItemView_.build(context);
		}

		view.bind((Event) getItem(position));

		return view;
	}

	@Override
	public void update(Observable observable, Object data) {
		notifyDataSetChanged();
	}
}
