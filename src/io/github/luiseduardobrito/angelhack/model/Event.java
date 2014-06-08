package io.github.luiseduardobrito.angelhack.model;

import java.util.Date;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;

@ParseClassName("Event")
public class Event extends ParseObject {

	public static enum Type {
		SPORTS("sports"), HAPPY_HOUR("happyhour"), COFFEE("coffee"), MEETING(
				"meeting"), MEAL("meal"), BIRTHDAY("birthday");

		public static Type fromString(String text) {
			if (text != null) {
				for (Type b : Type.values()) {
					if (text.equalsIgnoreCase(b.value)) {
						return b;
					}
				}
			}
			return null;
		}

		String value;

		private Type(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return getValue();
		}

		public String getValue() {
			return value;
		}
	}

	public Event() {
		// Auto-generated constructor stub
	}

	public Event(String name, Type type, Date date, String location,
			Company company) throws ParseException {
		setName(name);
		setType(type);
		setDate(date);
		setLocation(location);
		setCreator((User) User.getCurrentUser());
		setCompany(company);
		save();
	}

	public void setCompany(Company company) {
		put("company", company);
	}

	public void setName(String name) {
		put("name", name);
	}

	public void setCreator(User creator) {
		put("creator", creator);
	}

	public void setType(Type type) {
		put("type", type.getValue());
	}

	public void setDate(Date date) {
		put("date", date);
	}

	public void setLocation(String location) {
		put("location", location);
	}

	public String getName() {
		return getString("name");
	}

	public Type getType() {
		return Type.fromString(getString("type"));
	}

	public String getLocation() {
		return getString("location");
	}
}
