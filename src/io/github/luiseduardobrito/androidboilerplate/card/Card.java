package io.github.luiseduardobrito.androidboilerplate.card;

import it.gmariotti.cardslib.library.view.CardView;

import org.androidannotations.annotations.EViewGroup;

import android.content.Context;
import android.util.AttributeSet;

@EViewGroup
public class Card extends CardView {

	public Card(Context context) {
		super(context);
	}

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param attrs
	 */
	public Card(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public Card(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

}
