package com.example.bratishka.ui.entry;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.example.bratishka.R;
import com.example.bratishka.adapter.DiscountsAdapter;
import com.example.bratishka.adapter.ViewPagerEntryAdapter;
import com.example.bratishka.databinding.FragmentEntryBinding;
import com.example.bratishka.model.Discount;
import com.example.bratishka.repository.DiscountsRepository;
import com.example.bratishka.ui.entry.uientry.HaircutsFragments;
import com.example.bratishka.ui.entry.uientry.MyNotificationsActivity;
import com.example.bratishka.ui.entry.uientry.ShopFragment;
import com.example.bratishka.ui.entry.uientry.ShoppingCartActivity;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.ArrayList;

public class EntryFragment extends Fragment {
    private RecyclerView recyclerView;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private FragmentEntryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EntryViewModel dashboardViewModel =
                new ViewModelProvider(this).get(EntryViewModel.class);
        setHasOptionsMenu(true);

        binding = FragmentEntryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        this.tabLayout = root.findViewById(R.id.tabLayoutEntry);
        this.viewPager = root.findViewById(R.id.viewPagerEntry);
        this.recyclerView = root.findViewById(R.id.recycler_view_promotion_and_discounts);

        try {
            DiscountsRepository repository = new DiscountsRepository(root.getContext());
            ArrayList<Discount> discounts = repository.getDiscounts();
            LinearLayoutManager manager = new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false);
            this.recyclerView.setLayoutManager(manager);
            this.recyclerView.setAdapter(new DiscountsAdapter(root.getContext(), discounts));
        }catch (IOException e){
            e.printStackTrace();
        }

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerEntryAdapter adapter = new ViewPagerEntryAdapter(
                getChildFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        );

        adapter.addFragment(new HaircutsFragments(), "Стрижки");
        adapter.addFragment(new ShopFragment(), "Магазин");

        viewPager.setAdapter(adapter);

        initComponents();

        return root;
    }

    private void initComponents() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setCustomView(R.layout.toolbar_title_entry_fragment);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_entry, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.shoppingBasket_id) {
            Intent intent = new Intent(getContext(), ShoppingCartActivity.class);
            startActivity(intent);
        }

        if (item.getItemId() == R.id.notifications_id) {
            Intent intent = new Intent(getContext(), MyNotificationsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}