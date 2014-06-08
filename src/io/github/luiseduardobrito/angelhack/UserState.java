package io.github.luiseduardobrito.angelhack;

import io.github.luiseduardobrito.angelhack.model.Company;
import io.github.luiseduardobrito.angelhack.model.Event;
import io.github.luiseduardobrito.angelhack.model.User;

import java.util.List;
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
	private List<Company> companyList;
	private List<Event> eventList;

	private UserState() {
		update();
	}

	public User getCurrent() {
		return current;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
		setChanged();
		notifyObservers();
	}

	public List<Company> getCompanyList() {
		return companyList;
	}

	public List<Event> getEventList() {
		return eventList;
	}

	public void update() {

		current = (User) User.getCurrentUser();

		try {

			current.refresh();
			companyList = current.getCompanies();

			if (current != null && getCompanyList() != null
					&& current.getCompanies().size() > 0) {
				company = current.getCompanies().get(0);
			}

			updateEventList();

		} catch (ParseException e) {
			e.printStackTrace();
		}

		setChanged();
		notifyObservers();
	}

	public void updateEventList() {
		try {
			eventList = current.getAvailableEvents();
			setChanged();
			notifyObservers();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
