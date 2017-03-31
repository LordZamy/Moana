package moanainc.com.moana.controller;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import moanainc.com.moana.R;

/*
 * Created by Micah Terrell on 2/13/2017.
 */

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        Button mLoginButton;
        Button mRegisterButton;
        TextView mTitleView;

        mLoginButton = (Button) findViewById(R.id.main_goto_login_button);
        mRegisterButton = (Button) findViewById(R.id.main_goto_register_button);
        mTitleView = (TextView) findViewById(R.id.titleView);
        Typeface disney_font = Typeface.createFromAsset(getAssets(),  "fonts/disneyui.ttf");
        mLoginButton.setTypeface(disney_font);
        mRegisterButton.setTypeface(disney_font);
        mTitleView.setTypeface(disney_font);
    }

    public void gotoLogin(View view) {
        Intent gotoLogin = new Intent(getBaseContext(), LoginActivity.class);
        getBaseContext().startActivity(gotoLogin);
    }

    public void gotoRegister(View view) {
        Intent gotoRegister = new Intent(getBaseContext(), RegisterActivity.class);
        getBaseContext().startActivity(gotoRegister);
    }

    @Override
    public void onBackPressed() {
        Toast toast = Toast.makeText(getApplicationContext(), "You are not logged in", Toast.LENGTH_LONG);
        toast.show();
    }
}
