package io.github.luiseduardobrito.angelhack;

import io.github.luiseduardobrito.angelhack.model.Company;
import io.github.luiseduardobrito.angelhack.model.User;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EApplication;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;

@EApplication
public class App extends Application {

	static {
		ParseUser.registerSubclass(User.class);
		ParseObject.registerSubclass(Company.class);
	}

	@AfterInject
	void init() {
		Parse.initialize(this, "6jzH3my3Ty5KlVCWBIAyUTzYJwFEzG9KNXp6RYTC",
				"9Q3cqztyphOXA2nEpXoFXw9NbcB1PylzBtBYl3Tn");

		// PushService.setDefaultPushCallback(this, MainActivity_.class);

		ParseInstallation.getCurrentInstallation().saveInBackground();
	}
}
