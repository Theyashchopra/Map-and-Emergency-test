package com.nopalyer.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    String[] perms = {
            Manifest.permission.SEND_SMS,
            };
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 31;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        Toast.makeText(this, "App running", Toast.LENGTH_SHORT).show();
        checkper();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.map:
                Toast.makeText(MainActivity.this, "MAP VIEW", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MapView.class));
                overridePendingTransition(0, 0);
                return true;
            case R.id.emg:
                Toast.makeText(MainActivity.this, "EMERGENCY", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), EmergencyView.class));
                overridePendingTransition(0, 0);
                return true;
            case R.id.logout:
                Toast.makeText(MainActivity.this, "LOGOUT", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 31: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(),"Permissions available",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission failed", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }

    public void checkper() {
        Toast.makeText(this, "chcking permissions", Toast.LENGTH_SHORT).show();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
                Toast.makeText(this,"permissions available",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,"accessing permission",Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }
}
