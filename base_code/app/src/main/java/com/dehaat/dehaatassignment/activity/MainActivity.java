package com.dehaat.dehaatassignment.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dehaat.dehaatassignment.R;
import com.dehaat.dehaatassignment.SessionManage.SessionManager;
import com.dehaat.dehaatassignment.fragments.AuthorsFragment;
import com.dehaat.dehaatassignment.fragments.LoginFragment;
import com.dehaat.dehaatassignment.listener.LogoutListener;

public class MainActivity extends AppCompatActivity implements LogoutListener {

    SessionManager sessionManager;

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
            sessionManager.createLoginSession();
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
}
