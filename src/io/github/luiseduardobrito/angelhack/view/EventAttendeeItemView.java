package io.github.luiseduardobrito.angelhack.view;

import io.github.luiseduardobrito.angelhack.R;
import io.github.luiseduardobrito.angelhack.adapter.EventInvitationListAdapter;
import io.github.luiseduardobrito.angelhack.model.User;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.ParseException;

@EViewGroup(R.layout.event_attendee_list_item)
public class EventAttendeeItemView extends LinearLayout {

	@Bean
	EventInvitationListAdapter adapter;

	@ViewById(R.id.email)
	TextView emailView;

	public EventAttendeeItemView(Context context) {
		super(context);
	}

	public void bind(User user) {

		try {
			emailView.setText(user.getName());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
