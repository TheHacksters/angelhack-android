package io.github.luiseduardobrito.angelhack.view;

import io.github.luiseduardobrito.angelhack.R;
import io.github.luiseduardobrito.angelhack.adapter.EventInvitationListAdapter;
import io.github.luiseduardobrito.angelhack.model.User;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import com.parse.ParseException;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

@EViewGroup(R.layout.company_invitation_list_item)
public class EventInvitationItemView extends LinearLayout {

	@Bean
	EventInvitationListAdapter adapter;

	@ViewById(R.id.email)
	TextView emailView;

	@ViewById(R.id.delete)
	ImageView delete;

	public EventInvitationItemView(Context context) {
		super(context);
	}

	public void bind(User user) {

		try {
			emailView.setText(user.getName());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final User fUser = user;

		delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				adapter.remove(fUser);
			}
		});
	}
}
