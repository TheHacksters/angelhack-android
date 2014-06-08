package io.github.luiseduardobrito.angelhack;

import io.github.luiseduardobrito.angelhack.model.Company;
import io.github.luiseduardobrito.angelhack.model.User;

import java.util.Observable;

import com.parse.ParseException;

public class UserState extends Observable {

	private static UserState instance;

	public static UserState getInstance() {

		if (instance == null) {
			instance = new UserState();
		}

		return instance;
	}

	private User current;
	private Company company;

	private UserState() {
		update();
	}

	public User getCurrent() {
		return current;
	}

	public Company getCompany() {
		return company;
	}

	public void update() {

		current = (User) User.getCurrentUser();

		try {

			current.refresh();

			if (current != null && current.getCompanies() != null
					&& current.getCompanies().size() > 0) {
				company = current.getCompanies().get(0);
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		setChanged();
		notifyObservers();
	}
}
