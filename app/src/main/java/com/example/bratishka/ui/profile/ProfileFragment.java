package com.example.bratishka.ui.profile;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bratishka.R;
import com.example.bratishka.databinding.FragmentProfileBinding;
import com.example.bratishka.model.User;
import com.example.bratishka.repository.NetworkService;
import com.example.bratishka.ui.profile.editprofile.EditProfileActivity;
import com.example.bratishka.util.Constants;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private TextView txtSurname, txtName, txtFathername;
    private TextView txtEmail, txtDateBirth, txtNumber, txtCity;
    private ImageView img;

    private View root;
    private List<User> users;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel notificationsViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        setHasOptionsMenu(true);
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        initComponents();
        return root;
    }

    private void initComponents() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setCustomView(R.layout.toolbar_title_profile_fragment);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_bratishka);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences preferences
                = root.getContext().getSharedPreferences(Constants.PREFERENCES_USER, MODE_PRIVATE);
        String email = preferences.getString(Constants.PREFERENCES_USER_EMAIL, null);

        this.txtSurname = root.findViewById(R.id.surnameUser);
        this.txtName = root.findViewById(R.id.nameUser);
        this.txtFathername = root.findViewById(R.id.fathernameUser);
        this.txtEmail = root.findViewById(R.id.emailUser);
        this.txtDateBirth = root.findViewById(R.id.dateBornUser);
        this.txtNumber = root.findViewById(R.id.numberPhoneUser);
        this.txtCity = root.findViewById(R.id.cityUser);
        this.img = root.findViewById(R.id.imgUser);

        NetworkService.getInstance().getBratishkaApi().getProfile(email)
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        users = response.body();
                        txtEmail.setText(email);

                        for (User user : users) {
                            try {
                                String surname = user.getSurname();
                                txtSurname.setText(surname);
                                String name = user.getName();
                                txtName.setText(name);
                                String fathername = user.getFathername();
                                txtFathername.setText(fathername);
                                String city = user.getCity();
                                txtCity.setText(city);
                                String phone = user.getNumberPhone();
                                txtNumber.setText(phone);
                            } catch (NullPointerException e) {
                                e.printStackTrace();
                            }

                            try {
                                String dateBirth = user.getDateBirth();
                                txtDateBirth.setText(dateBirth);
                            } catch (ParseException | NullPointerException e) {
                                e.printStackTrace();
                                txtDateBirth.setText("");
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Toast.makeText(root.getContext(), "Error!", Toast.LENGTH_SHORT).show();
                        t.fillInStackTrace();
                    }
                });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_profile, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.edit_profile_id) {
            Intent intent = new Intent(this.getActivity(), EditProfileActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}