package io.github.luiseduardobrito.angelhack.activity;

import io.github.luiseduardobrito.angelhack.R;
import io.github.luiseduardobrito.angelhack.adapter.CompanyListAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.widget.ListView;

@OptionsMenu(R.menu.choose_company)
@EActivity(R.layout.activity_choose_company)
public class ChooseCompanyActivity extends Activity {

	@Bean
	CompanyListAdapter adapter;

	@ViewById(R.id.companies_list)
	ListView list;

	@AfterViews
	void initViews() {
		setTitle("Choose company");
		list.setAdapter(adapter);
	}

	@Click(R.id.create_company_button)
	void createCompany() {
		CreateCompanyActivity_.intent(this).start();
		this.finish();
	}
}
