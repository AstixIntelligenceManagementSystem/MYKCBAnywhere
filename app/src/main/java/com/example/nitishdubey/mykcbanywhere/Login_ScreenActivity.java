package com.example.nitishdubey.mykcbanywhere;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import retrofit2.Call;
import retrofit2.Callback;

import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;


import com.google.gson.JsonObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Login_ScreenActivity extends Activity implements View.OnClickListener {

    LinearLayout ll_login_register;
    Button btn_login_register,btn_login,btn_register;
    boolean isLoginButtonCld=true;
    String strUserId,strUserName,strUserType,straccount,strPassword,strMobile,strActDate;
    EditText userId,userName,userType,account,password,mobile;
    String jsonLoginUrl="http://196.41.37.54:55255/eCCL_CMD/login";
    String jsonRegisterUrl="http://196.41.37.54:55255/eCCL_CMD/createuser";
    public ProgressDialog pDialogRegisterLogin;
    private APIInterface apiInterface;
    final int version = Build.VERSION.SDK_INT;
    int errorColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__screen);
        pDialogRegisterLogin = new ProgressDialog(Login_ScreenActivity.this);
        initializeViews();
        if (version >= 23) {
            errorColor = ContextCompat.getColor(getApplicationContext(), R.color.errorColor);
        } else {
            errorColor = getResources().getColor(R.color.errorColor);
        }
    }

    private void initializeViews() {
        //Nitish Dubey Done Changes
        ll_login_register= (LinearLayout) findViewById(R.id.ll_login_register);
        btn_login_register= (Button) findViewById(R.id.btn_login_register);
        btn_login= (Button) findViewById(R.id.btn_login);
        btn_register= (Button) findViewById(R.id.btn_register);
        btn_login.setOnClickListener(this);
        btn_login_register.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        btn_register.setBackgroundResource(R.drawable.button_background);
        btn_login.setBackgroundResource(R.drawable.clkd_button_background);
        isLoginButtonCld=true;
        btn_login_register.setText(getString(R.string.login));
        pageSetupForLoginOrRegister();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_login:
                btn_register.setBackgroundResource(R.drawable.button_background);
                btn_login.setBackgroundResource(R.drawable.clkd_button_background);
                isLoginButtonCld=true;
                btn_login_register.setText(getString(R.string.login));
                pageSetupForLoginOrRegister();
                break;
            case R.id.btn_register:
                btn_login.setBackgroundResource(R.drawable.button_background);
                btn_register.setBackgroundResource(R.drawable.clkd_button_background);
                isLoginButtonCld=false;
                btn_login_register.setText(getString(R.string.register));
                pageSetupForLoginOrRegister();
                break;
            case R.id.btn_login_register:
                if(isLoginButtonCld)
                {
                    if(validateLoginFields())
                    {
                        JSONObject jsonObject = new JSONObject();

                        try {
                            jsonObject.accumulate("userid", strUserId);
                            jsonObject.accumulate("password", strPassword);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        invokeWS(jsonLoginUrl,jsonObject.toString());
                 /*   new HttpAsyncTask().execute(jsonLoginUrl);
                        break;*/
                       // validateLoginDataRetrofit();
                       // validateLoginDataVolley();
                    }

                }
                else
                {
                    if(validateRegisterFields())
                    {

                       /* params.put("userid",strUserId);
                        params.put("username",strUserName);
                        params.put("usertype",strUserType);
                        params.put("account",straccount);
                        params.put("password",strPassword);
                        params.put("mobile",strMobile);
                        long syncTIMESTAMP = System.currentTimeMillis();
                        Date datefromat = new Date(syncTIMESTAMP);
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        strActDate = df.format(datefromat);
                        params.put("actdate",strActDate);*/
                        JSONObject jsonObject = new JSONObject();

                        try {
                            jsonObject.accumulate("userid", strUserId);
                            jsonObject.accumulate("username",strUserName);
                            jsonObject.accumulate("usertype",strUserType);
                            jsonObject.accumulate("account",straccount);
                            jsonObject.accumulate("password",strPassword);
                            jsonObject.accumulate("mobile",strMobile);
                            long syncTIMESTAMP = System.currentTimeMillis();
                            Date datefromat = new Date(syncTIMESTAMP);
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            strActDate = df.format(datefromat);
                            jsonObject.accumulate("actdate",strActDate);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        invokeWS(jsonRegisterUrl,jsonObject.toString());
                      //  validateRegisterData();
                       // validateRegisterDataRetrofit();
                    }

                }
                break;
        }
    }

    public boolean validateRegisterFields()
    {
        boolean isAllDataFilled=false;
        if(!TextUtils.isEmpty(userId.getText().toString().trim()))
        {
            strUserId=userId.getText().toString().trim();
            isAllDataFilled=true;

        }
        else
        {
            userId.requestFocus();
            userId.setError(setErrorColor("Please fill userId first to Register"));
            return isAllDataFilled;
        }
        if(!TextUtils.isEmpty(userName.getText().toString().trim()))
        {
            strUserName=userName.getText().toString().trim();
            isAllDataFilled=true;
        }

        else
        {
            userName.requestFocus();
            userName.setError(setErrorColor("Please fill userName first to Register"));
            return isAllDataFilled;
        }
        if(!TextUtils.isEmpty(userType.getText().toString().trim()))
        {
            strUserType=userType.getText().toString().trim();
            isAllDataFilled=true;
        }

        else
        {
            userType.requestFocus();
            userType.setError(setErrorColor("Please fill userType first to Register"));
            return isAllDataFilled;
        }
        if(!TextUtils.isEmpty(account.getText().toString().trim()))
        {
            straccount=account.getText().toString().trim();
            isAllDataFilled=true;
        }

        else
        {
            account.requestFocus();
            account.setError(setErrorColor("Please fill account first to Register"));
            return isAllDataFilled;
        }
        if(!TextUtils.isEmpty(password.getText().toString().trim()))
        {
            strPassword=password.getText().toString().trim();
            isAllDataFilled=true;
        }

        else
        {
            password.requestFocus();
            password.setError(setErrorColor("Please fill password first to Register"));
            return isAllDataFilled;
        }
        if(!TextUtils.isEmpty(mobile.getText().toString().trim()))
        {
            strMobile=mobile.getText().toString().trim();
            isAllDataFilled=true;
        }

        else
        {
            mobile.requestFocus();
            mobile.setError(setErrorColor("Please fill mobile first to Register"));
            return isAllDataFilled;
        }
        return isAllDataFilled;
    }

    public boolean validateLoginFields()
    {
        boolean isAllDataFilled=false;
        if(!TextUtils.isEmpty(userId.getText().toString().trim()))
        {
            strUserId=userId.getText().toString().trim();
            isAllDataFilled=true;

        }
        else
        {
            //Get the defined errorColor from color resource.
            userId.requestFocus();
            userId.setError(setErrorColor("Please fill loginId first to Login"));
            return isAllDataFilled;
        }
         if(!TextUtils.isEmpty(password.getText().toString().trim()))
        {
            strPassword=password.getText().toString().trim();
            isAllDataFilled=true;
        }

        else
        {
            password.requestFocus();
            password.setError(setErrorColor("Please fill password first to Login"));

        }
        return isAllDataFilled;
    }
    public void pageSetupForLoginOrRegister()
    {
        ll_login_register.removeAllViews();
        LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewInflated=null;
        if(isLoginButtonCld)
        {
            viewInflated=inflater.inflate(R.layout.login_layout,null);
            userId= (EditText) viewInflated.findViewById(R.id.ed_userId);
            password= (EditText) viewInflated.findViewById(R.id.ed_password);
        }
        else
        {
            viewInflated=inflater.inflate(R.layout.registration_page,null);
            userId= (EditText) viewInflated.findViewById(R.id.ed_userId);
            password= (EditText) viewInflated.findViewById(R.id.ed_password);
            userName= (EditText) viewInflated.findViewById(R.id.ed_userName);
            userType= (EditText) viewInflated.findViewById(R.id.ed_userType);
            account= (EditText) viewInflated.findViewById(R.id.ed_accountNumber);
            mobile= (EditText) viewInflated.findViewById(R.id.ed_mobileNumber);
        }

        if(viewInflated!=null)
        {
            ll_login_register.addView(viewInflated);
        }


    }

    public void validateRegisterData()
    {
        pDialogRegisterLogin.setTitle("Please Wait");
        pDialogRegisterLogin.setMessage("While we register your account.");
        pDialogRegisterLogin.setIndeterminate(false);
        pDialogRegisterLogin.setCancelable(false);
        pDialogRegisterLogin.setCanceledOnTouchOutside(false);
        pDialogRegisterLogin.show();
        StringRequest strRqst=new StringRequest(Request.Method.POST, jsonRegisterUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(pDialogRegisterLogin.isShowing())
                        {
                            pDialogRegisterLogin.dismiss();
                        }
                        System.out.println("Response Register = "+response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(pDialogRegisterLogin.isShowing())
                {
                    pDialogRegisterLogin.dismiss();
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String,String>();
                params.put("userid",strUserId);
                params.put("username",strUserName);
                params.put("usertype",strUserType);
                params.put("account",straccount);
                params.put("password",strPassword);
                params.put("mobile",strMobile);
                long syncTIMESTAMP = System.currentTimeMillis();
                Date datefromat = new Date(syncTIMESTAMP);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                 strActDate = df.format(datefromat);
                params.put("actdate",strActDate);



                return params;
            }
        };
        VolleySingeltonRequest.getmInstance(getApplicationContext()).addToRequestqueue(strRqst);
    }
    public void validateLoginDataVolley()
    {
        pDialogRegisterLogin.setTitle("Please Wait");
        pDialogRegisterLogin.setMessage("Validating login details...");
        pDialogRegisterLogin.setIndeterminate(false);
        pDialogRegisterLogin.setCancelable(false);
        pDialogRegisterLogin.setCanceledOnTouchOutside(false);
        pDialogRegisterLogin.show();
        Map<String, String> jsonParams = new HashMap<String, String>();
        jsonParams.put("userid",strUserId);
        jsonParams.put("password",strPassword);
      JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, jsonLoginUrl, new JSONObject(jsonParams),
              new Response.Listener<JSONObject>() {
                  @Override
                  public void onResponse(JSONObject response) {
                      if(pDialogRegisterLogin.isShowing())
                      {
                          pDialogRegisterLogin.dismiss();
                      }
                      System.out.println("Response Login = "+response);
                  }
              }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
              if(pDialogRegisterLogin.isShowing())
              {
                  pDialogRegisterLogin.dismiss();
              }
          }
      }){
          @Override
          protected Map<String, String> getParams() throws AuthFailureError {
              Map<String, String> params = new HashMap<String, String>();
              params.put("userid", strUserId);
              params.put("password", strPassword);
              return params;
          }
          };
        VolleySingeltonRequest.getmInstance(getApplicationContext()).addToRequestqueue(jsonObjectRequest);
    }


    public void validateLoginDataRetrofit()
    {
        apiInterface=ApiClent.getApiClient().create(APIInterface.class);
        Call<List<UserDetails>> call=apiInterface.getLogin(strUserId,strPassword);
        call.enqueue(new Callback<List<UserDetails>>() {
            @Override
            public void onResponse(Call<List<UserDetails>> call, retrofit2.Response<List<UserDetails>> response) {
                System.out.println(" Data = "+response.message());
            }

            @Override
            public void onFailure(Call<List<UserDetails>> call, Throwable t) {
                System.out.println(" Data Fail = ");
            }
        });
    }

    public void validateRegisterDataRetrofit()
    {
        long syncTIMESTAMP = System.currentTimeMillis();
        Date datefromat = new Date(syncTIMESTAMP);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        strActDate = df.format(datefromat);
        apiInterface=ApiClent.getApiClient().create(APIInterface.class);
        //(@Query("userid") String userid,@Query("username") String userName,@Query("usertype") String usertype,@Query("account") String account,@Query("password") String password,@Query("mobile") String mobile,@Query("actdate") String actdate);
        Call<List<UserDetails>> call=apiInterface.getRegistration(strUserId,strUserName,strUserType,straccount,strPassword,strMobile,strActDate);
        call.enqueue(new Callback<List<UserDetails>>() {
            @Override
            public void onResponse(Call<List<UserDetails>> call, retrofit2.Response<List<UserDetails>> response) {
                System.out.println(" Data = "+response.message());
            }

            @Override
            public void onFailure(Call<List<UserDetails>> call, Throwable t) {
                System.out.println(" Data Fail = ");
            }
        });
    }



    public SpannableStringBuilder setErrorColor(String errorString)
    {
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(errorColor);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(errorString);
        spannableStringBuilder.setSpan(foregroundColorSpan, 0, errorString.length(), 0);
        return spannableStringBuilder;
    }


    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {



            return POST(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

        }
    }


    public String POST(String jsonurl)
    {
        String output="";
        try
        {
        URL url = new URL(jsonurl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
            String json = "";

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();

            try {
                jsonObject.accumulate("userid", strUserId);
                jsonObject.accumulate("password", strPassword);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();



        OutputStream os = conn.getOutputStream();
        os.write(json.getBytes());
        os.flush();
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));


        System.out.println("Output from Server .... \n");
            StringBuilder sb=new StringBuilder();
        while ((output = br.readLine()) != null) {
            sb.append(output);
        }
            output=sb.toString();
        conn.disconnect();

    } catch (MalformedURLException e) {

        e.printStackTrace();

    } catch (IOException e) {

        e.printStackTrace();

    }
    return output;
    }
    public void invokeWS(String urlTemp,String jsonParams){

        // Show Progress Dialog

        pDialogRegisterLogin.setTitle("Please Wait");
        pDialogRegisterLogin.setMessage("Validating login details...");
        pDialogRegisterLogin.setIndeterminate(false);
        pDialogRegisterLogin.setCancelable(false);
        pDialogRegisterLogin.setCanceledOnTouchOutside(false);
        pDialogRegisterLogin.show();

        // Make RESTful webservice call using AsyncHttpClient object
        //sunil
        AsyncHttpClient client = new AsyncHttpClient();
        StringEntity entity=null;
        try {
             entity = new StringEntity(jsonParams);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        client.post(Login_ScreenActivity.this,urlTemp,entity, "application/json" ,new AsyncHttpResponseHandler() {

            // When the response returned by REST has Http response code '200'

            @Override

            public void onSuccess(String response) {

                // Hide Progress Dialog

                pDialogRegisterLogin.dismiss();

                try {

                    // JSON Object

                    JSONObject obj = new JSONObject(response);

                    // When the JSON response has status boolean value assigned with true

                    if(obj.getBoolean("status")){

                        Toast.makeText(getApplicationContext(), "You are successfully logged in!", Toast.LENGTH_LONG).show();

                        // Navigate to Home screen



                    }

                    // Else display error message

                    else{



                        Toast.makeText(getApplicationContext(), obj.getString("error_msg"), Toast.LENGTH_LONG).show();

                    }

                } catch (JSONException e) {

                    // TODO Auto-generated catch block

                    Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();

                    e.printStackTrace();



                }

            }

            // When the response returned by REST has Http response code other than '200'


            @Override
            public void onFailure(int statusCode, Throwable error,

                                  String content) {

                // Hide Progress Dialog

                pDialogRegisterLogin.dismiss();

                // When Http response code is '404'

                if(statusCode == 404){

                    Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();

                }

                // When Http response code is '500'

                else if(statusCode == 500){

                    Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();

                }

                // When Http response code other than 404, 500

                else{
//nitish dubey
                    Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();

                }

            }

        });

    }



}
