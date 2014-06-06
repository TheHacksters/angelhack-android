package io.github.luiseduardobrito.androidboilerplate.card;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import android.content.Context;

public class SimpleCard extends Card {

	/**
	 * Card header
	 */
	CardHeader header;

	/**
	 * Constructor
	 * 
	 * @param context
	 */
	public SimpleCard(Context context) {
		super(context);
		header = new CardHeader(getContext());
		addCardHeader(header);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.gmariotti.cardslib.library.internal.base.BaseCard#setTitle(java.lang
	 * .String)
	 */
	@Override
	public void setTitle(String title) {
		header.setTitle(title);
	}

	/**
	 * Set card content
	 * 
	 * @param description
	 */
	public void setContent(String description) {
		super.setTitle(description);
	}
}
