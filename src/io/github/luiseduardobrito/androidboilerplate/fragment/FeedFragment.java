package io.github.luiseduardobrito.androidboilerplate.fragment;

import io.github.luiseduardobrito.androidboilerplate.R;
import io.github.luiseduardobrito.androidboilerplate.card.SimpleThumbnailCard;
import it.gmariotti.cardslib.library.view.CardView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.app.Fragment;

@EFragment(R.layout.fragment_feed)
public class FeedFragment extends Fragment {

	@ViewById(R.id.card)
	CardView cardView;

	@AfterViews
	void initViews() {

		// Create a Card
		SimpleThumbnailCard card = new SimpleThumbnailCard(getActivity(),
				R.drawable.carddemo_ic_gmaps_large);
		card.setTitle("Welcome!");
		card.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras purus odio, accumsan vitae dui in, vulputate laoreet velit. Quisque ac sem suscipit, adipiscing augue non, pharetra nunc. Phasellus sed lobortis nisi.");

		// Show card in view
		cardView.setCard(card);
	}
}
