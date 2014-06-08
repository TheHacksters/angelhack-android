package io.github.luiseduardobrito.angelhack.activity;

import io.github.luiseduardobrito.angelhack.R;
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
		Event.Type type = Event.Type.COFFEE;
		setEventTitle(type.getTitleRes());
		setBackgroundColor(type.getColorRes());
	}

	@Click(R.id.meal)
	void meal() {
		Event.Type type = Event.Type.MEAL;
		setEventTitle(type.getTitleRes());
		setBackgroundColor(type.getColorRes());
	}

	@Click(R.id.meeting)
	void meeting() {
		Event.Type type = Event.Type.MEETING;
		setEventTitle(type.getTitleRes());
		setBackgroundColor(type.getColorRes());
	}

	@Click(R.id.happyhour)
	void happyhour() {
		Event.Type type = Event.Type.HAPPYHOUR;
		setEventTitle(type.getTitleRes());
		setBackgroundColor(type.getColorRes());
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
