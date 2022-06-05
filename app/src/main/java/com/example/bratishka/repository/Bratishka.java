package com.example.bratishka.repository;

import com.example.bratishka.model.Barber;
import com.example.bratishka.model.Branch;
import com.example.bratishka.model.City;
import com.example.bratishka.model.Haircut;
import com.example.bratishka.model.Message;
import com.example.bratishka.model.Product;
import com.example.bratishka.model.Resp;

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

}
