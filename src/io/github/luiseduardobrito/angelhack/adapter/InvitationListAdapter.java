package io.github.luiseduardobrito.angelhack.adapter;

import io.github.luiseduardobrito.angelhack.view.InvitationItemView;
import io.github.luiseduardobrito.angelhack.view.InvitationItemView_;

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
public class InvitationListAdapter extends BaseAdapter {

	List<String> list;

	@RootContext
	Context context;

	@AfterInject
	void init() {
		list = new ArrayList<String>();
	}

	public void add(String email) {
		list.add(email);
		notifyDataSetChanged();
	}

	public void remove(String email) {
		list.remove(email);
		notifyDataSetChanged();
	}

	public List<String> getList() {
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

		InvitationItemView view;

		if (convertView != null) {
			view = (InvitationItemView) convertView;
		} else {
			view = InvitationItemView_.build(context);
		}

		view.bind((String) getItem(position));
		return view;
	}

}
