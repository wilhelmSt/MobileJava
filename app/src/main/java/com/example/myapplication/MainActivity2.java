package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity2 extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private Button buttonToAct1;

    private String[] titles = new String[]{"Tab1", "Tab2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // tabs viewPager2
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new ViewPagerFragmentSateAdpater(this));
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(titles[position])
        ).attach();

    }

    public  class ViewPagerFragmentSateAdpater extends FragmentStateAdapter{
        public ViewPagerFragmentSateAdpater(FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new tab1();
                case 1:
                    return new tab2();
            }
            return new tab1();
        }
        @Override
        public int getItemCount() {
            return titles.length;
        }
    }
}