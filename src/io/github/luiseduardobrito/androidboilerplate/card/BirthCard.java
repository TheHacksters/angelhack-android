/*
 * ******************************************************************************
 *   Copyright (c) 2013-2014 Gabriele Mariotti.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *  *****************************************************************************
 */

package io.github.luiseduardobrito.androidboilerplate.card;

import io.github.luiseduardobrito.androidboilerplate.R;
import io.github.luiseduardobrito.androidboilerplate.drawable.RoundCornersDrawable;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardExpand;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This class provides a simple card as Google Now Birthday
 * 
 * @author Gabriele Mariotti (gabri.mariotti@gmail.com)
 */
public class BirthCard extends Card {

	public int USE_VIGNETTE = 1;

	public BirthCard(Context context) {
		super(context);
		init();
	}

	public BirthCard(Context context, int innerLayout) {
		super(context, innerLayout);
		init();
	}

	private void init() {

		// Add Header
		GoogleNowBirthHeader header = new GoogleNowBirthHeader(getContext(),
				R.layout.carddemo_googlenowbirth_inner_header);
		header.setButtonExpandVisible(true);
		header.mName = "Luis Eduardo Brito";
		header.mSubName = "Birthday today";
		addCardHeader(header);

		// Add Expand Area
		CardExpand expand = new ExpandCard(getContext());
		addCardExpand(expand);

		// Set clickListener
		setOnClickListener(new OnCardClickListener() {
			@Override
			public void onClick(Card card, View view) {
				Toast.makeText(getContext(), "Click Listener card",
						Toast.LENGTH_LONG).show();
			}
		});

		// Add Thumbnail
		GoogleNowBirthThumb thumbnail = new GoogleNowBirthThumb(getContext());
		float density = getContext().getResources().getDisplayMetrics().density;
		thumbnail
				.setUrlResource("https://lh5.googleusercontent.com/-JrAPB30M4Yk/T1WgXuZ9RzI/AAAAAAAAAJM/r2kdGh7bktY/w437-h438-no/374577_339329239411117_100000021327423_1441646_2060320903_n.jpg");
		thumbnail.setErrorResource(R.drawable.ic_ic_error_loading);
		addCardThumbnail(thumbnail);
	}

	@Override
	public void setupInnerViewElements(ViewGroup parent, View view) {

		TextView title = (TextView) view
				.findViewById(R.id.card_main_inner_simple_title);
		title.setText("Wish Happy Birthday");
		title.setTextColor(mContext.getResources().getColor(
				R.color.carddemo_googlenowbirth_title));
		title.setGravity(Gravity.CENTER_VERTICAL);

	}

	class GoogleNowBirthThumb extends CardThumbnail {

		public GoogleNowBirthThumb(Context context) {
			super(context);
		}

		@Override
		public void setupInnerViewElements(ViewGroup parent, View viewImage) {
			/*
			 * viewImage.getLayoutParams().width = 250;
			 * viewImage.getLayoutParams().height = 250;
			 */
		}

		@Override
		public boolean applyBitmap(View imageView, Bitmap bitmap) {

			// RounderImage
			int CORNER_RADIUS = 12; // dips
			// int MARGIN = 12; // dips

			float density = getContext().getResources().getDisplayMetrics().density;
			int mCornerRadius = (int) (CORNER_RADIUS * density + 0.5f);
			int mMargin = 0;

			RoundCornersDrawable round = new RoundCornersDrawable(bitmap,
					mCornerRadius, mMargin);

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				imageView.setBackground(round);
			}

			return true;
		}
	}

	class GoogleNowBirthHeader extends CardHeader {

		String mName;
		String mSubName;

		public GoogleNowBirthHeader(Context context, int innerLayout) {
			super(context, innerLayout);
		}

		@Override
		public void setupInnerViewElements(ViewGroup parent, View view) {

			TextView txName = (TextView) view.findViewById(R.id.text_birth1);
			TextView txSubName = (TextView) view.findViewById(R.id.text_birth2);

			txName.setText(mName);
			txSubName.setText(mSubName);
		}
	}
}