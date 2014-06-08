package io.github.luiseduardobrito.angelhack.activity;

import io.github.luiseduardobrito.angelhack.R;
import io.github.luiseduardobrito.angelhack.UserState;
import io.github.luiseduardobrito.angelhack.adapter.EventInvitationListAdapter;
import io.github.luiseduardobrito.angelhack.model.User;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.widget.ListView;

import com.parse.ParseException;

@EActivity(R.layout.activity_invite_event)
public class InviteEventActivity extends Activity {

	@Extra("io.github.luiseduardobrito.angelhack.EVENT_ID")
	String eventId;

	UserState userState = UserState.getInstance();

	@Bean
	EventInvitationListAdapter adapter;

	@ViewById(R.id.invitations_list)
	ListView list;

	@AfterViews
	void initViews() {

		setTitle("Invite coworkers");

//		try {
//			for (User coworker : userState.getCompany().getCoworkers()) {
//				adapter.add(coworker.getName());
//			}
//		} catch (ParseException e) {
//			e.printStackTrace();
//			this.finish();
//		}

		list.setAdapter(adapter);
	}
}
