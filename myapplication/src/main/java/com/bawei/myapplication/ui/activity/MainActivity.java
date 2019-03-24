package com.bawei.myapplication.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bawei.myapplication.R;
import com.bawei.myapplication.ui.fragment.FragmentNew;
import com.bawei.myapplication.utils.FragmentHot;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private FragmentManager fragmentManager;
    private FragmentHot fragmentHot;
    private FragmentNew fragmentNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fragmentHot = new FragmentHot();
        fragmentNew = new FragmentNew();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame, fragmentHot);
        fragmentTransaction.add(R.id.frame, fragmentNew);
        fragmentTransaction.show(fragmentHot).hide(fragmentNew);
        fragmentTransaction.commit();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (id == R.id.action_rb) {
            transaction.show(fragmentHot).hide(fragmentNew).commit();
            return true;
        } else if (id == R.id.action_sy) {
            transaction.show(fragmentNew).hide(fragmentHot).commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
