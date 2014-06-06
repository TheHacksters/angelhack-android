package io.github.luiseduardobrito.androidboilerplate.fragment;

import io.github.luiseduardobrito.androidboilerplate.R;
import io.github.luiseduardobrito.androidboilerplate.card.BirthCard;
import it.gmariotti.cardslib.library.view.CardView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.app.Fragment;

@EFragment(R.layout.fragment_feed)
public class FeedFragment extends Fragment {

	@ViewById(R.id.card)
	CardView birthCardView;

	@AfterViews
	void initViews() {

		// Create a Card
		BirthCard birthCard = new BirthCard(getActivity());

		// Set card in the cardView
		birthCardView.setCard(birthCard);
	}
}
