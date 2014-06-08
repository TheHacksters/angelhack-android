package io.github.luiseduardobrito.angelhack.activity;

import io.github.luiseduardobrito.angelhack.R;
import io.github.luiseduardobrito.angelhack.adapter.CompanyInvitationListAdapter;
import io.github.luiseduardobrito.angelhack.exception.AppException;
import io.github.luiseduardobrito.angelhack.model.Company;
import io.github.luiseduardobrito.angelhack.model.User;

import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseException;

@OptionsMenu(R.menu.create_company)
@EActivity(R.layout.activity_create_company)
public class CreateCompanyActivity extends Activity {

	@Bean
	CompanyInvitationListAdapter adapter;

	@ViewById(R.id.invitations_list)
	ListView list;

	@ViewById(R.id.name)
	EditText name;

	@ViewById(R.id.email)
	EditText email;

	@AfterViews
	void init() {
		list.setAdapter(adapter);
	}

	@AfterViews
	void initViews() {
		setTitle("Create company");
	}

	@Click(R.id.add_member)
	void addMember() {
		adapter.add(email.getText().toString());
		email.setText("");
	}

	@Click(R.id.create_company_button)
	void create() {

		if (name.getText().toString().isEmpty()) {
			AlertDialog.Builder builder = new Builder(this);
			builder.setTitle("Error");
			builder.setMessage("Company name cannot be empty");
			builder.setCancelable(false);
			builder.setPositiveButton(R.string.ok, new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
		}

		else {
			performCreateInBg(name.getText().toString());
		}
	}

	@Background
	void performCreateInBg(String name) {

		Company company;

		try {

			company = new Company(name, (User) User.getCurrentUser());
			User me = (User) User.getCurrentUser();
			me.addCompany(company);

			List<String> emailList = adapter.getList();
			company.batchInvite(emailList);

			showResult();

		} catch (ParseException e) {
		}

	}

	@UiThread
	void showResult() {
		Toast.makeText(getApplicationContext(), "Company created successfully",
				Toast.LENGTH_SHORT).show();
		this.finish();
	}

	/**
	 * Show auth error based on AppException
	 * 
	 * @param e
	 */
	@UiThread
	void showError(AppException e) {
		showError(e.getMessage());
	}

	/**
	 * Show auth error based on string
	 * 
	 * @param e
	 */
	@UiThread
	void showError(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
}
