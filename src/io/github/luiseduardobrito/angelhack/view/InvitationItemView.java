package io.github.luiseduardobrito.angelhack.view;

import io.github.luiseduardobrito.angelhack.R;
import io.github.luiseduardobrito.angelhack.adapter.InvitationListAdapter;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

@EViewGroup(R.layout.invitation_list_item)
public class InvitationItemView extends LinearLayout {

	@Bean
	InvitationListAdapter adapter;

	@ViewById(R.id.email)
	TextView emailView;

	@ViewById(R.id.delete)
	ImageView delete;

	public InvitationItemView(Context context) {
		super(context);
	}

	public void bind(String email) {

		emailView.setText(email);
		
		final String fEmail = email;

		delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				adapter.remove(fEmail);
			}
		});
	}
}
