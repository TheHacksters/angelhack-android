package io.github.luiseduardobrito.angelhack.activity;

import io.github.luiseduardobrito.angelhack.R;
import io.github.luiseduardobrito.angelhack.UserState;
import io.github.luiseduardobrito.angelhack.adapter.EventAttendeeListAdapter;
import io.github.luiseduardobrito.angelhack.model.Event;
import io.github.luiseduardobrito.angelhack.model.User;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseQuery;

@EActivity(R.layout.activity_event_profile)
public class EventProfileActivity extends Activity {

	@Extra("io.github.luiseduardobrito.angelhack.EVENT_ID")
	String mExtraEventId;

	@Extra("io.github.luiseduardobrito.angelhack.EVENT_TYPE")
	String mExtraEventType;

	Event event;
	Event.Type type;

	UserState userState = UserState.getInstance();
	ProgressDialog dialog;

	@Bean
	EventAttendeeListAdapter adapter;

	@ViewById(R.id.event_type_label)
	TextView eventLabel;

	@ViewById(R.id.event_type_location)
	TextView eventLocation;

	@ViewById(R.id.event_icon)
	ImageView icon;

	@ViewById(R.id.event_layout)
	LinearLayout eventLayout;

	@ViewById(R.id.invitations_list)
	ListView list;

	@AfterInject
	void init() {

		try {
			ParseQuery<Event> query = new ParseQuery<Event>(Event.class);
			query.include("invited");
			query.include("confirmed");
			event = query.get(mExtraEventId);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@AfterViews
	void initViews() {

		list.setAdapter(adapter);
		setTitle("Event details");

		if (event != null) {

			type = event.getType();

			eventLabel.setText(event.getName());
			eventLocation.setText(event.getLocation());
			eventLayout.setBackgroundResource(type.getColorRes());

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

		for (Object u : event.getList("confirmed")) {
			adapter.add((User) u);
		}
	}

	@Click(R.id.send_invitations)
	void sendInvitations() {
		InviteEventActivity_.intent(this).mExtraEventId(event.getObjectId())
				.mExtraEventType(event.getType().getValue()).start();
	}
}
