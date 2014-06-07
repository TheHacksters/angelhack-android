package io.github.luiseduardobrito.androidboilerplate.fragment;

import io.github.luiseduardobrito.androidboilerplate.R;
import io.github.luiseduardobrito.androidboilerplate.card.SimpleThumbnailCard;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardGridArrayAdapter;
import it.gmariotti.cardslib.library.view.CardGridView;

import java.util.ArrayList;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.app.Fragment;

/**
 * A placeholder fragment containing a simple view.
 */
@EFragment(R.layout.fragment_profile)
public class PlaceholderFragment extends Fragment {

	@ViewById(R.id.myGrid)
	CardGridView gridView;

	@AfterViews
	void initViews() {

		ArrayList<Card> cards = new ArrayList<Card>();

		for (int i = 0; i < 15; i++) {
			// Create a Card
			SimpleThumbnailCard card = new SimpleThumbnailCard(getActivity(),
					R.drawable.carddemo_ic_gmaps_large);
			card.setTitle("Welcome!");
			card.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras purus odio, accumsan vitae dui in, vulputate laoreet velit. Quisque ac sem suscipit, adipiscing augue non, pharetra nunc. Phasellus sed lobortis nisi.");

			cards.add(card);
		}

		CardGridArrayAdapter adapter = new CardGridArrayAdapter(getActivity(),
				cards);
		gridView.setAdapter(adapter);
	}
}