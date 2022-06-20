package com.example.bratishka.ui.branches;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.bratishka.R;
import com.example.bratishka.adapter.viewpager.ViewPagerBranchesAdapter;
import com.example.bratishka.databinding.FragmentBranchesBinding;
import com.example.bratishka.ui.branches.uibranches.ListBranchesFragment;
import com.example.bratishka.ui.branches.uibranches.MapBranchesFragment;
import com.google.android.material.tabs.TabLayout;

public class BranchesFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private FragmentBranchesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BranchesViewModel homeViewModel =
                new ViewModelProvider(this).get(BranchesViewModel.class);
        setHasOptionsMenu(true);

        binding = FragmentBranchesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        this.tabLayout = root.findViewById(R.id.tabLayoutBranches);
        this.viewPager = root.findViewById(R.id.viewPagerBranches);

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerBranchesAdapter adapter = new ViewPagerBranchesAdapter(
                getChildFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        );

        adapter.addFragment(new ListBranchesFragment(), "Список");
        adapter.addFragment(new MapBranchesFragment(), "На карте");
        viewPager.setAdapter(adapter);

        initComponents();

        return root;
    }

    private void initComponents(){
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setCustomView(R.layout.toolbar_title_branches_fragment);

    }

}