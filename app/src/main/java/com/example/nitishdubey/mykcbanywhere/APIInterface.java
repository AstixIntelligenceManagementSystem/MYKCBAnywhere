package com.example.nitishdubey.mykcbanywhere;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Nitish Dubey on 24-01-2018.
 */

public interface APIInterface {

    @POST("login")
    Call<List<UserDetails>> getLogin(@Query("userid") String userid,@Query("password") String password);
    @POST("createuser")
    Call<List<UserDetails>> getRegistration(@Query("userid") String userid,@Query("username") String userName,@Query("usertype") String usertype,@Query("account") String account,@Query("password") String password,@Query("mobile") String mobile,@Query("actdate") String actdate);
    @POST("ftransfer")
    Call<List<UserDetails>> getFTransferStatus(@Query("sbank") String sbank,@Query("sbranch") String sbranch,@Query("saccount") String saccount,@Query("sname") String sname,@Query("dbank") String dbank,@Query("dbranch") String dbranch,@Query("daccount") String daccount
            ,@Query("dname") String dname,@Query("amount") String amount,@Query("mobile") String mobile,@Query("remark") String remark,@Query("dep_date") String dep_date
            ,@Query("dep_datetime") String dep_datetime,@Query("currency_code") String currency_code);


}
