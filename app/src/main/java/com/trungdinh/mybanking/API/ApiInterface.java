package com.trungdinh.mybanking.API;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public interface ApiInterface {

    //Create Account
    @POST("/account/create/{bank-id}")
    Call<CreateAccount> CREATE_ACCOUNT_CALL(@Path("bank-id") String bankid, @Query("ssn") String ssn,
                                            @Query("name") String Name, @Query("email") String Email);
    //Change Password
    @GET("/account/password/{email}")
    Call<ChangePassword> CHANGE_PASSWORD_CALL(@Path("email") String email, @Query("old_password") String oldPassword,
                                              @Query("new_password") String NewPassword);
    //Change Email
    @GET("/account/email/{old-email}")
    Call<ChangeEmail> CHANGE_EMAIL_CALL(@Path("old-email") String OldEmail, @Query("password") String Password,
                                        @Query("new_email") String NewEmail);
    //Initiate Account Deletion
    @GET("/account/delete/initiate/{email}")
    Call<InitDeletion> INIT_DELETION_CALL(@Path("email") String Email, @Query("password") String Password);

    //Confirm Account Deletion
    @GET("/account/delete/confirm/{email}")
    Call<ConfirmDeletion> CONFIRM_DELETION_CALL(@Path("email") String Email, @Query("password") String Password, @Query("token") String Token);
}
