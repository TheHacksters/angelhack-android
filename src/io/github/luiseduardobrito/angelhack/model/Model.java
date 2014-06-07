package io.github.luiseduardobrito.angelhack.model;

import java.util.Observable;

public class Model extends Observable {

	/**
	 * Set chnaged and notify observers
	 */
	public void setChangeAndNotify() {
		this.setChanged();
		this.notifyObservers();
	}
}
