package com.example.bratishka.ui.entry.bookahaircut;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bratishka.R;
import com.example.bratishka.adapter.viewpager.ViewPagerEntries;
import com.example.bratishka.model.Haircut;
import com.google.android.material.tabs.TabLayout;

public class BookAHaircutActivity extends AppCompatActivity {
    public static final String NAME_HAIRCUT = "name_haircut";
    private TextView textViewPrice, textViewName;
    private Haircut haircut;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_ahaircut);
        initComponents();
    }

    private void initComponents() {
        this.haircut = (Haircut) this.getIntent().getSerializableExtra(NAME_HAIRCUT);
        initTextView();
        initTabLayoutAndViewPager();
        initSpinner();
    }

    private void initTextView() {
        this.textViewPrice = findViewById(R.id.productPrice);
        this.textViewName = findViewById(R.id.productDescription);

        this.textViewPrice.setText(this.haircut.getPrice());
        this.textViewName.setText(this.haircut.getName());

    }

    private void initTabLayoutAndViewPager() {
        this.tabLayout = findViewById(R.id.tabLayoutEntry);
        this.viewPager = findViewById(R.id.viewPagerEntry);
        this.tabLayout.setupWithViewPager(viewPager);

        ViewPagerEntries viewPagerEntries = new ViewPagerEntries(
                getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        );
        viewPagerEntries.addFragment(new ByAppointmentFragment(), "По записи");
        viewPagerEntries.addFragment(new LiveQueueFragment(), "Живая очередь");
        viewPager.setAdapter(viewPagerEntries);

    }

    /**
     * Список точек
     */

    private void initSpinner() {
        this.spinner = findViewById(R.id.spinnerPoints);

        /*try {
            BranchesRepository branchesRepository = new BranchesRepository(this);
            ArrayList<Branch> branches = branchesRepository.getBranches();
            BranchesSpinnerAdapter spinnerAdapter = new BranchesSpinnerAdapter(this, android.R.layout.simple_spinner_item,branches);
            this.spinner.setAdapter(spinnerAdapter);

        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

}