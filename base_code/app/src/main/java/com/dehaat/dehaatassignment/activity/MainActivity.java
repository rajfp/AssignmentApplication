package com.dehaat.dehaatassignment.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.graphics.Color;
import android.os.Bundle;
import com.dehaat.dehaatassignment.R;
import com.dehaat.dehaatassignment.SessionManage.SessionManager;
import com.dehaat.dehaatassignment.fragments.AuthorsFragment;
import com.dehaat.dehaatassignment.fragments.LoginFragment;
import com.dehaat.dehaatassignment.listener.LogoutListener;
import com.dehaat.dehaatassignment.listener.SessionListener;

public class MainActivity extends AppCompatActivity implements LogoutListener, SessionListener {

    public SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkLoginStatus();
        initViews();
    }

    private void checkLoginStatus() {

        sessionManager = new SessionManager(this);
        if (sessionManager.isLoggedIn())
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new AuthorsFragment(), null).commit();

        else {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new LoginFragment(), null).commit();
        }
    }

    private void initViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() == null)
            return;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.assignment);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onLogout() {
        sessionManager.destroySession();
        finish();
        startActivity(getIntent());
    }

    @Override
    public void createSession() {
        if(!sessionManager.isLoggedIn())
        sessionManager.createLoginSession();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
