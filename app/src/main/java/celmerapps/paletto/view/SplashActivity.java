package celmerapps.paletto.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import celmerapps.paletto.R;

/**
 * Created by Mati on 2017-07-19.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        final TextView txt_app_name = (TextView) findViewById(R.id.txt_appName_splashscreen);
        final TextView txt_company_name = (TextView) findViewById(R.id.txt_CompanyName_splashscreen);

        final int animationDuration = getResources().getInteger(
                android.R.integer.config_longAnimTime);

        txt_company_name.setAlpha(0f);
        txt_company_name.setVisibility(View.VISIBLE);
        txt_app_name.setAlpha(0f);
        txt_app_name.setVisibility(View.VISIBLE);

        txt_app_name.animate()
                .alpha(1f)
                .setDuration(animationDuration)
                .setListener(null);

        final Intent intent = new Intent(this, ListActivity.class);

        Handler mHandler = new Handler();

        mHandler.postDelayed(new Runnable() {
            public void run() {
                txt_company_name.animate()
                        .alpha(1f)
                        .setDuration(animationDuration)
                        .setListener(null);
            }
        }, 300);

        // animacja wyjazd tekstu

        mHandler.postDelayed(new Runnable() {
            public void run() {
                txt_company_name.animate()
                        .translationX(-1000)
                        .alpha(0);
                txt_app_name.animate()
                        .translationX(1000)
                        .alpha(0);
            }
        }, 1500);

        // Nowy Activity

        mHandler.postDelayed(new Runnable() {
            public void run() {
                startActivity(intent);finish();
            }
        }, 1700);


    }
}
