package io.github.luiseduardobrito.angelhack.activity;

import io.github.luiseduardobrito.angelhack.R;
import io.github.luiseduardobrito.angelhack.UserState;
import io.github.luiseduardobrito.angelhack.adapter.EventInvitationListAdapter;
import io.github.luiseduardobrito.angelhack.model.Event;
import io.github.luiseduardobrito.angelhack.model.User;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseQuery;

@EActivity(R.layout.activity_invite_event)
public class InviteEventActivity extends Activity {

	@Extra("io.github.luiseduardobrito.angelhack.EVENT_ID")
	String mExtraEventId;

	@Extra("io.github.luiseduardobrito.angelhack.EVENT_TYPE")
	String mExtraEventType;

	Event event;
	Event.Type type;

	UserState userState = UserState.getInstance();

	ProgressDialog dialog;

	@Bean
	EventInvitationListAdapter adapter;

	@ViewById(R.id.invite_event_layout)
	LinearLayout inviteEventLayout;

	@ViewById(R.id.event_type_label)
	TextView eventLabel;

	@ViewById(R.id.event_icon)
	ImageView icon;

	@ViewById(R.id.invitations_list)
	ListView list;

	@AfterInject
	void init() {

		try {
			ParseQuery<Event> query = new ParseQuery<Event>(Event.class);
			event = query.get(mExtraEventId);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@AfterViews
	void initViews() {

		setTitle("Invite coworkers");

		if (mExtraEventType != null && !mExtraEventType.isEmpty()) {

			type = Event.Type.fromString(mExtraEventType);

			eventLabel.setText(type.getTitleRes());
			inviteEventLayout.setBackgroundResource(type.getColorRes());

			switch (type) {
			case BIRTHDAY:
				icon.setImageResource(R.drawable.ic_event_birthday);
				break;

			case COFFEE:
				icon.setImageResource(R.drawable.ic_event_coffee);
				break;

			case HAPPYHOUR:
				icon.setImageResource(R.drawable.ic_event_happy_hour);
				break;

			case MEAL:
				icon.setImageResource(R.drawable.ic_event_meal);
				break;

			case MEETING:
				icon.setImageResource(R.drawable.ic_event_meeting);
				break;

			case SPORTS:
				icon.setImageResource(R.drawable.ic_event_sport);
				break;

			default:
				icon.setVisibility(View.INVISIBLE);
				break;
			}
		}

		if (event != null) {
			eventLabel.setText(event.getName());
		}

		adapter.clear();

		try {
			adapter.setList(userState.getCompany().getCoworkers());
		} catch (Exception e) {
			e.printStackTrace();
			this.finish();
		}

		list.setAdapter(adapter);
	}

	@Click(R.id.send_invitations)
	void sendInvitations() {
		dialog = ProgressDialog.show(this, null, "Sending invitations...");
		sendInvitationsInBg();
	}

	@Background
	void sendInvitationsInBg() {

		try {

			for (User u : adapter.getList()) {
				event.add("invited", u);
			}

			event.save();
			showResult();

		} catch (ParseException e1) {
			e1.printStackTrace();
			showResult(e1);
		}
	}

	@UiThread
	void showResult() {
		dialog.dismiss();
		Toast.makeText(this, "Invitations sent successfully",
				Toast.LENGTH_SHORT).show();
		this.finish();
	}

	@UiThread
	void showResult(Exception e) {
		showResult(e.getMessage());
	}

	@UiThread
	void showResult(String message) {
		dialog.dismiss();
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
}
