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
    public static final String SURNAME = "Фамилия: не выбрано";
    public static final String NAME = "Имя: не выбрано";
    public static final String FATHERNAME = "Отчество: не выбрано";
    public static final String NUMBER_PHONE = "Номер телефона: не выбрано";
    public static final String DATE_BORN = "Дата рождения: не выбрано";
    public static final String CITY = "Город: не выбрано";

    private FragmentProfileBinding binding;
    private TextView txtSurname, txtName, txtFathername;
    private TextView txtEmail, txtDateBirth, txtNumber, txtCity;
    private ImageView img;

    private View root;
    private List<User> users;
    private String surname;
    private String name;
    private String fathername;
    private String numberPhone;
    private String city;
    private String dateBirth;
    private User user;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel notificationsViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        setHasOptionsMenu(true);
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        initComponents();
        initDataUser();
        return root;
    }

    private void initComponents() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setCustomView(R.layout.toolbar_title_profile_fragment);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_bratishka);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.txtSurname = root.findViewById(R.id.surnameUser);
        this.txtName = root.findViewById(R.id.nameUser);
        this.txtFathername = root.findViewById(R.id.fathernameUser);
        this.txtEmail = root.findViewById(R.id.emailUser);
        this.txtDateBirth = root.findViewById(R.id.dateBornUser);
        this.txtNumber = root.findViewById(R.id.numberPhoneUser);
        this.txtCity = root.findViewById(R.id.cityUser);
        this.img = root.findViewById(R.id.imgUser);
    }

    private void initDataUser() {
        SharedPreferences preferences
                = root.getContext().getSharedPreferences(Constants.PREFERENCES_USER, MODE_PRIVATE);
        String email = preferences.getString(Constants.PREFERENCES_USER_EMAIL, null);

        NetworkService.getInstance().getBratishkaApi().getProfile(email)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call,
                                           Response<User> response) {
                        user = response.body();
                        txtEmail.setText(email);
                        surname = user.getSurname();
                        name = user.getName();
                        fathername = user.getFathername();
                        numberPhone = user.getNumberPhone();
                        city = user.getCity();

                        try {
                            dateBirth = user.getDateBirth();
                            txtDateBirth.setText(dateBirth);
                        } catch (ParseException | NullPointerException e) {
                            txtDateBirth.setText(DATE_BORN);
                            e.printStackTrace();
                        }

                        try {
                            txtSurname.setText(surname);

                        } catch (NullPointerException e){
                            e.printStackTrace();
                            txtSurname.setText(SURNAME);
                        }

                        try {
                            txtName.setText(name);
                        } catch (NullPointerException e){
                            e.printStackTrace();
                            txtName.setText(NAME);
                        }

                        try {
                            txtFathername.setText(fathername);
                        } catch (NullPointerException e){
                            e.printStackTrace();
                            txtFathername.setText(FATHERNAME);
                        }

                        try {
                            txtNumber.setText(numberPhone);
                        } catch (NullPointerException e){
                            e.printStackTrace();
                            txtNumber.setText(NUMBER_PHONE);
                        }

                        try {
                            txtCity.setText(city);
                        } catch (NullPointerException e){
                            txtCity.setText(CITY);
                        }

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
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
            intent.putExtra("user", user);

            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        initDataUser();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}