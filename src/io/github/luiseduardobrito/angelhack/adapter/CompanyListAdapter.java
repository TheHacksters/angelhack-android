package io.github.luiseduardobrito.angelhack.adapter;

import io.github.luiseduardobrito.angelhack.UserState;
import io.github.luiseduardobrito.angelhack.model.Company;
import io.github.luiseduardobrito.angelhack.model.User;
import io.github.luiseduardobrito.angelhack.view.CompanyItemView;
import io.github.luiseduardobrito.angelhack.view.CompanyItemView_;

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

import com.parse.ParseException;

@EBean(scope = Scope.Singleton)
public class CompanyListAdapter extends BaseAdapter implements Observer {

	@RootContext
	Context context;

	User current;
	List<Company> list = new ArrayList<Company>();

	@AfterInject
	void init() {
		UserState.getInstance().addObserver(this);
		update(null, null);
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

		CompanyItemView view;

		if (convertView != null) {
			view = (CompanyItemView) convertView;
		} else {
			view = CompanyItemView_.build(context);
		}

		view.bind((Company) getItem(position));

		return view;
	}

	@Override
	public void update(Observable observable, Object data) {
		current = UserState.getInstance().getCurrent();
		try {
			list = current.getCompanies();
		} catch (ParseException e) {
			list = new ArrayList<Company>();
		}
	}
}
