package com.company.tvmvinfo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.company.tvmvinfo.R;
import com.company.tvmvinfo.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class SearchActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText editTextSearch;
    Button buttonSearch;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    public static String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        toolbar= findViewById(R.id.toolbarSearch);
        editTextSearch=findViewById(R.id.editTextSearchSearch);
        buttonSearch=findViewById(R.id.buttonSearchSearch);
        tabLayout=findViewById(R.id.tabLayoutSearch);
        viewPager2=findViewById(R.id.viewPager2Search);

        Intent intent = getIntent();
        name=intent.getStringExtra("nameForSearch");

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),getLifecycle());
        viewPager2.setAdapter(viewPagerAdapter);

        TabLayoutMediator tabLayoutMediator= new TabLayoutMediator(tabLayout, viewPager2, true, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position)
                {
                    case 0:
                        tab.setText("Movie");
                        break;
                    case 1:
                        tab.setText("Tv Show");
                        break;

                }
            }
        });
        tabLayoutMediator.attach();

        editTextSearch.setText(name);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextSearch.getText()!=null)
                {
                    goToSearch(editTextSearch.getText().toString());
                }
            }
        });
        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_DONE||actionId==EditorInfo.IME_ACTION_NEXT)
                {
                    if(editTextSearch.getText()!=null)
                    {
                        goToSearch(editTextSearch.getText().toString());
                        return true;
                    }

                }
                return false;
            }
        });


    }

    public void goToSearch(String name)
    {
        Intent intent = new Intent(SearchActivity.this,SearchActivity.class);
        intent.putExtra("nameForSearch",name);
        startActivity(intent);
        finish();
    }

}