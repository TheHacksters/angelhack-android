package io.github.luiseduardobrito.androidboilerplate.drawer;

import io.github.luiseduardobrito.androidboilerplate.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EBean.Scope;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.res.StringArrayRes;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

@EBean(scope = Scope.Singleton)
public class DrawerItemAdapter extends BaseAdapter implements Observer {

	@RootContext
	Context context;

	/**
	 * Drawer items labels
	 */
	@StringArrayRes(R.array.drawer_items)
	String[] drawerItemsLabels;

	/**
	 * Drawer items list
	 */
	List<DrawerItem> items;

	/**
	 * Constructor
	 */
	@AfterInject
	void init() {
		this.update();
	}

	/**
	 * Set selected item
	 * 
	 * @param position
	 */
	public void setSelected(Integer position) {

		// Iterate selecting stuff
		for (int i = 0; i < items.size(); i++) {
			items.get(i).setLabelSelected(position.equals(i));
		}
	}

	/**
	 * Update state
	 */
	public void update() {
		update(null, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable observable, Object data) {

		// Create items list
		items = new ArrayList<DrawerItem>();

		// Populate drawer items
		for (String label : drawerItemsLabels) {
			DrawerItem item = DrawerItem_.build(context);
			item.bind(label);
			items.add(item);
		}

		// Notify lsitview
		this.notifyDataSetChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return items.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		DrawerItem item = items.get(position);
		return item;
	}
}
