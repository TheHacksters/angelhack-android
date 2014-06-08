package io.github.luiseduardobrito.angelhack.fragment;

import io.github.luiseduardobrito.angelhack.R;
import io.github.luiseduardobrito.angelhack.UserState;
import io.github.luiseduardobrito.angelhack.adapter.CoworkerListAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.app.Fragment;
import android.widget.ListView;

@EFragment(R.layout.fragment_coworkers)
public class CoworkerListFragment extends Fragment {

	UserState userState = UserState.getInstance();

	@Bean
	CoworkerListAdapter adapter;

	@ViewById(R.id.coworkers_list)
	ListView list;

	@AfterViews
	void init() {
		list.setAdapter(adapter);
	}
}
