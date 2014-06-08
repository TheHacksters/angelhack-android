package io.github.luiseduardobrito.angelhack.view;

import io.github.luiseduardobrito.angelhack.R;
import io.github.luiseduardobrito.angelhack.model.Company;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.ParseException;

@EViewGroup(R.layout.company_list_item)
public class CompanyItemView extends LinearLayout {

	@ViewById(R.id.company_item_layout)
	LinearLayout companyLayout;

	@ViewById(R.id.company_logo)
	ImageView companyLogo;

	@ViewById(R.id.company_name)
	TextView companyName;

	@ViewById(R.id.company_workers)
	TextView companyWorkers;

	public CompanyItemView(Context context) {
		super(context);
	}

	public void bind(Company company, OnClickListener listener) {

		companyLayout.setOnClickListener(listener);
		companyName.setText(company.getName());

		try {

			companyWorkers.setText(company.getUserCount().toString()
					.concat(" members"));
			companyWorkers.setVisibility(View.VISIBLE);

		} catch (ParseException e) {
			companyWorkers.setText("");
			companyWorkers.setVisibility(View.INVISIBLE);
		}
	}
}
