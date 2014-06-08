package io.github.luiseduardobrito.angelhack.activity;

import io.github.luiseduardobrito.angelhack.R;
import io.github.luiseduardobrito.angelhack.UserState;
import io.github.luiseduardobrito.angelhack.adapter.CompanyListAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

@OptionsMenu(R.menu.choose_company)
@EActivity(R.layout.activity_choose_company)
public class ChooseCompanyActivity extends Activity {

	@Bean
	CompanyListAdapter adapter;

	@ViewById(R.id.empy_list_layout)
	LinearLayout emptyLayout;

	@ViewById(R.id.company_list_layout)
	LinearLayout companyLayout;

	@ViewById(R.id.companies_list)
	ListView list;

	@AfterViews
	void initViews() {

		setTitle("Choose company");
		list.setAdapter(adapter);

		if (UserState.getInstance() == null) {
			this.finish();
		}

		else if (UserState.getInstance().getCompany() == null) {
			emptyLayout.setVisibility(View.VISIBLE);
			companyLayout.setVisibility(View.GONE);
		}
	}

	@Click(R.id.create_company_button)
	void createCompany() {
		CreateCompanyActivity_.intent(this).start();
		this.finish();
	}

	@Click(R.id.create_empty_button)
	void emptyCompany() {
		CreateCompanyActivity_.intent(this).start();
		this.finish();
	}
}
