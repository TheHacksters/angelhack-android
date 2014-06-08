package io.github.luiseduardobrito.angelhack.view;

import io.github.luiseduardobrito.angelhack.R;
import io.github.luiseduardobrito.angelhack.UserState;
import io.github.luiseduardobrito.angelhack.model.User;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.ParseException;

@EViewGroup(R.layout.coworker_list_item)
public class CoworkerItemView extends LinearLayout {

	@ViewById(R.id.company_item_layout)
	LinearLayout companyLayout;

	@ViewById(R.id.coworker_picture)
	ImageView companyLogo;

	@ViewById(R.id.coworker_name)
	TextView companyName;

	@ViewById(R.id.coworker_company)
	TextView companyWorkers;

	public CoworkerItemView(Context context) {
		super(context);
	}

	public void bind(User coworker, OnClickListener listener) {

		if (listener != null) {
			companyLayout.setOnClickListener(listener);
		}

		try {

			companyName.setText(coworker.getName());
			companyWorkers.setText(UserState.getInstance().getCompany()
					.getName());
			companyWorkers.setVisibility(View.VISIBLE);

		} catch (ParseException e) {
			companyWorkers.setText("");
			companyWorkers.setVisibility(View.INVISIBLE);
		}
	}
}
