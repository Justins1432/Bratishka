package com.example.bratishka.repository;

import com.example.bratishka.model.Barber;
import com.example.bratishka.model.Branch;
import com.example.bratishka.model.City;
import com.example.bratishka.model.Haircut;
import com.example.bratishka.model.Message;
import com.example.bratishka.model.Product;
import com.example.bratishka.model.Record;
import com.example.bratishka.model.Resp;
import com.example.bratishka.model.Schedule;
import com.example.bratishka.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Bratishka {
    @GET("/bratishka/getcities.php")
    Call<List<City>> getCities();

    @GET("/bratishka/searchCity.php")
    Call<List<City>> getSearchCities(@Query("city") String city);

    @GET("/bratishka/getBranches.php")
    Call<List<Branch>> getCityBranches(@Query("city_id") String idCity);

    @GET("/bratishka/getSearchBranch.php")
    Call<List<Branch>> getSearchBranches(@Query("street") String street);

    @GET("/bratishka/messages.php")
    Call<List<Message>> getMessages();

    @GET("/bratishka/gethaircuts.php")
    Call<List<Haircut>> getHaircuts();

    @GET("/bratishka/getproducts.php")
    Call<List<Product>> getProducts();

    @GET("/bratishka/get_type_products.php")
    Call<List<Product>> getTypeProducts(@Query("type_id") int id);

    @GET("/bratishka/getCode.php")
    Call<Resp> getCode(@Query("email") String email);

    @GET("/bratishka/getCodeReview.php")
    Call<Resp> getReview(@Query("email") String email, @Query("code") String code);

    @GET("/bratishka/getRegistration.php")
    Call<Resp> getRegistration(@Query("email") String email, @Query("password") String password,
                               @Query("number_phone") String number);

    @GET("/bratishka/getAuth.php")
    Call<Resp> getAuth(@Query("email") String email, @Query("password") String password);

    @GET("/bratishka/getBarbersBranch.php")
    Call<List<Barber>> getBarbersBranch(@Query("branch_id") int id);

    @GET("/bratishka/getRecovery.php")
    Call<Resp> getRecovery(@Query("email") String email,
                           @Query("code") String code,
                           @Query("password") String password);

    @GET("/bratishka/getTimeBarber.php")
    Call<List<Schedule>> getTimes(@Query("id") String id, @Query("day") String day);

    @GET("/bratishka/getProfileUser.php")
    Call<User> getProfile(@Query("email") String email);

    @GET("/bratishka/editprofile.php")
    Call<Resp> editProfile(@Query("email") String email,
                           @Query("surname") String surname,
                           @Query("name") String name,
                           @Query("fathername") String fathername,
                           @Query("date_birth") String dateBorn,
                           @Query("city") String city,
                           @Query("number_phone") String number);

    /*@GET("/bratishka/updateSurname.php")
    Call<Resp> updSurname(@Query("email") String email, @Query("surname") String surname);

    @GET("/bratishka/updateName.php")
    Call<Resp> updName(@Query("email") String email, @Query("name") String name);

    @GET("/bratishka/updateFathername.php")
    Call<Resp> updFathername(@Query("email") String email, @Query("fathername") String fathername);

    @GET("/bratishka/updateDateBorn.php")
    Call<Resp> updDateBorn(@Query("email") String email, @Query("dateBorn") String dateBorn);

    @GET("/bratishka/updateNumber.php")
    Call<Resp> updNumber(@Query("email") String email, @Query("number") String number);

    @GET("/bratishka/updateCity.php")
    Call<Resp> updCity(@Query("email") String email, @Query("city") String city);*/

    @GET("/bratishka/addRecord.php")
    Call<Resp> addRecord(@Query("date_record") String date,
                         @Query("user_email") String email,
                         @Query("branch_id") int branchID,
                         @Query("h_name") String h_name,
                         @Query("h_price") String h_price,
                         @Query("schedule") String schedule,
                         @Query("barber_id") int barberID);

    @GET("/bratishka/getRecords.php")
    Call<List<Record>> getRecords(@Query("user_email") String email);

}
