package com.company.tvmvinfo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.company.tvmvinfo.R;
import com.company.tvmvinfo.adapter.ViewPagerForClick;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ClickMoreActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    public static String category;
    String cat;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_more);

        toolbar=findViewById(R.id.toolbarClickMore);
        setSupportActionBar(toolbar);
        tabLayout=findViewById(R.id.tabLayoutClickMore);
        viewPager2=findViewById(R.id.viewPager2ClickMore);
        textView=findViewById(R.id.textViewCategoryClick);

        Intent intent = getIntent();
        category=intent.getStringExtra("categoryForClick");
        cat = intent.getStringExtra("cat");
        textView.setText(cat);
        ViewPagerForClick viewPagerForClick = new ViewPagerForClick(getSupportFragmentManager(),getLifecycle());
        viewPager2.setAdapter(viewPagerForClick);

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

    }
}