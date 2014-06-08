package io.github.luiseduardobrito.angelhack.view;

import io.github.luiseduardobrito.angelhack.R;
import io.github.luiseduardobrito.angelhack.model.Event;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.ParseException;

@EViewGroup(R.layout.event_list_item)
public class EventItemView extends LinearLayout {

	@ViewById(R.id.icon)
	ImageView icon;

	@ViewById(R.id.name)
	TextView name;

	@ViewById(R.id.members)
	TextView members;

	public EventItemView(Context context) {
		super(context);
	}

	public void bind(Event event) {

		name.setText(event.getName());

		try {
			members.setText(event.getConfirmed().toString());
			members.setVisibility(View.VISIBLE);
		} catch (ParseException e) {
			members.setVisibility(View.GONE);
		}

		switch (event.getType()) {

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
			icon.setImageResource(R.drawable.ic_event_meeting);
			break;

		}
	}
}