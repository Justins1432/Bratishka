package com.example.bratishka.ui.entry.uientry;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bratishka.R;
import com.example.bratishka.adapter.ViewPagerShopAdapter;
import com.example.bratishka.ui.entry.uientry.shop.EntireRangeOfStoreFragment;
import com.example.bratishka.ui.entry.uientry.shop.OilForBeardStoreFragment;
import com.example.bratishka.ui.entry.uientry.shop.OtherOfStoreFragment;
import com.example.bratishka.ui.entry.uientry.shop.PowderForHairOfStoreFragment;
import com.example.bratishka.ui.entry.uientry.shop.ShampooOfStoreFragment;
import com.google.android.material.tabs.TabLayout;

public class ShopFragment extends Fragment {
    private TabLayout tabLayoutStore;
    private ViewPager viewPagerStore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        this.tabLayoutStore = view.findViewById(R.id.tabLayout_store);
        this.viewPagerStore = view.findViewById(R.id.viewPager_store);

        tabLayoutStore.setupWithViewPager(viewPagerStore);

        ViewPagerShopAdapter adapter = new ViewPagerShopAdapter(
                getChildFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        );

        adapter.addFragment(new EntireRangeOfStoreFragment(), "Всё");
        adapter.addFragment(new ShampooOfStoreFragment(), "Шампунь");
        adapter.addFragment(new PowderForHairOfStoreFragment(), "Пудра");
        adapter.addFragment(new OilForBeardStoreFragment(), "Масло");
        adapter.addFragment(new OtherOfStoreFragment(), "Другое");

        viewPagerStore.setAdapter(adapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}