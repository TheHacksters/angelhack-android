package io.github.luiseduardobrito.androidboilerplate.card;

import it.gmariotti.cardslib.library.internal.CardThumbnail;
import android.content.Context;

public class SimpleThumbnailCard extends SimpleCard {

	CardThumbnail thumb;

	public SimpleThumbnailCard(Context context, int res) {
		super(context);
		thumb = new CardThumbnail(context);
		thumb.setDrawableResource(res);
		addCardThumbnail(thumb);
	}
}
