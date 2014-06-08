package io.github.luiseduardobrito.angelhack.fragment;

import io.github.luiseduardobrito.angelhack.R;
import io.github.luiseduardobrito.angelhack.adapter.EventListAdapter;
import io.github.luiseduardobrito.angelhack.model.Event;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.app.Fragment;
import android.widget.ListView;

@EFragment(R.layout.fragment_feed)
public class FeedFragment extends Fragment {

	@Bean
	EventListAdapter adapter;

	@ViewById(R.id.event_list)
	ListView list;

	@AfterViews
	void initViews() {
		adapter.clear();
		adapter.add(new Event());
		list.setAdapter(adapter);
	}
}
