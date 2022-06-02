package com.example.bratishka.ui.mynotes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bratishka.R;
import com.example.bratishka.adapter.viewpager.ViewPagerMyNotes;
import com.example.bratishka.ui.mynotes.notes.PastEntriesFragment;
import com.example.bratishka.ui.mynotes.notes.UpcomingEntriesFragment;
import com.google.android.material.tabs.TabLayout;


public class MyNotesFragment extends Fragment {
    private MyNotesViewModel mViewModel;
    private TabLayout tabLayoutMyNotes;
    private ViewPager viewPagerMyNotes;

    public static MyNotesFragment newInstance() {
        return new MyNotesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_my_notes, container, false);

        this.tabLayoutMyNotes = root.findViewById(R.id.tabLayoutMyNotes);
        this.viewPagerMyNotes = root.findViewById(R.id.viewPagerMyNotes);
        tabLayoutMyNotes.setupWithViewPager(viewPagerMyNotes);

        ViewPagerMyNotes pagerMyNotes = new ViewPagerMyNotes(
                getChildFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        );

        pagerMyNotes.addFragment(new UpcomingEntriesFragment(), "Предстоящие");
        pagerMyNotes.addFragment(new PastEntriesFragment(), "Прошедшие");

        viewPagerMyNotes.setAdapter(pagerMyNotes);

        initComponents();

        return root;
    }

    private void initComponents(){
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setCustomView(R.layout.toolbar_title_mynotes_fragment);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MyNotesViewModel.class);
        // TODO: Use the ViewModel
    }

}