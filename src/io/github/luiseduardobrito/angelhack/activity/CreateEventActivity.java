package io.github.luiseduardobrito.angelhack.activity;

import io.github.luiseduardobrito.angelhack.R;
import io.github.luiseduardobrito.angelhack.UserState;
import io.github.luiseduardobrito.angelhack.model.Company;
import io.github.luiseduardobrito.angelhack.model.Event;
import io.github.luiseduardobrito.angelhack.model.Event.Type;

import java.util.Date;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;

@EActivity(R.layout.activity_create_event)
public class CreateEventActivity extends Activity {

	Event.Type currentEventType;

	@ViewById(R.id.create_event_layout)
	LinearLayout createEventLayout;

	@ViewById(R.id.event_type_label)
	TextView label;

	@ViewById(R.id.name)
	EditText eventName;

	@ViewById(R.id.date)
	EditText eventDate;

	@ViewById(R.id.location)
	EditText eventLocation;

	@AfterViews
	void initViews() {
		coffee();
	}

	@Click(R.id.coffee)
	void coffee() {
		currentEventType = Event.Type.COFFEE;
		setEventTitle(currentEventType.getTitleRes());
		setBackgroundColor(currentEventType.getColorRes());
	}

	@Click(R.id.meal)
	void meal() {
		currentEventType = Event.Type.MEAL;
		setEventTitle(currentEventType.getTitleRes());
		setBackgroundColor(currentEventType.getColorRes());
	}

	@Click(R.id.meeting)
	void meeting() {
		currentEventType = Event.Type.MEETING;
		setEventTitle(currentEventType.getTitleRes());
		setBackgroundColor(currentEventType.getColorRes());
	}

	@Click(R.id.happyhour)
	void happyhour() {
		currentEventType = Event.Type.HAPPYHOUR;
		setEventTitle(currentEventType.getTitleRes());
		setBackgroundColor(currentEventType.getColorRes());
	}

	@Click(R.id.sports)
	void sports() {
		Event.Type type = Event.Type.SPORTS;
		setEventTitle(type.getTitleRes());
		setBackgroundColor(type.getColorRes());
	}

	void setBackgroundColor(int colorRes) {
		createEventLayout.setBackgroundResource(colorRes);
	}

	void setEventTitle(int titleRes) {
		label.setText(getString(titleRes));
	}

	@Click(R.id.create)
	void createEvent() {
		String name = eventName.getText().toString();
		Date date = new Date();
		String location = eventLocation.getText().toString();

		performEventInBg(name, currentEventType, date, location, UserState
				.getInstance().getCompany());
	}

	@Background
	void performEventInBg(String name, Type type, Date date, String location,
			Company company) {
		try {
			showResult(new Event(name, type, date, location, company));
		} catch (ParseException e) {
			showError(e);
		}
	}

	@UiThread
	void showResult(Event event) {
		Toast.makeText(this, "Event created successfully", Toast.LENGTH_SHORT)
				.show();
		UserState.getInstance().updateEventList();
		this.finish();
	}

	@UiThread
	void showError(ParseException e) {
		showError(e.getMessage());
	}

	@UiThread
	void showError(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
}
