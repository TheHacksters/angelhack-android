package io.github.luiseduardobrito.angelhack;

import io.github.luiseduardobrito.angelhack.model.User;

import java.util.Observable;

public class UserState extends Observable {

	private static UserState instance;

	public static UserState getInstance() {

		if (instance == null) {
			instance = new UserState();
		}

		return instance;
	}

	private User current;

	private UserState() {
		update();
	}

	public User getCurrent() {
		return current;
	}

	public void update() {
		current = (User) User.getCurrentUser();
		setChanged();
		notifyObservers();
	}
}
