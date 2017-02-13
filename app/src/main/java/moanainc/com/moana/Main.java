package moanainc.com.moana;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import moanainc.com.moana.controllers.HomeActivity;
import moanainc.com.moana.controllers.LoginActivity;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        Intent gotoHome = new Intent(getBaseContext(), HomeActivity.class);
        getBaseContext().startActivity(gotoHome);
    }
}
