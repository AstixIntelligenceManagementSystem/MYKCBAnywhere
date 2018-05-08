package com.example.nitishdubey.mykcbanywhere;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class QuickTranserActivity extends AppCompatActivity {

    public ProgressDialog pDialogRegisterLogin;
    private APIInterface apiInterface;
    String sBank,sBranch,sAccount,sName,bank,dBranch,account,dname,Amount,mobileNumber,Remark,CurrencyCode;
    EditText ed_sBank,ed_sBranch,ed_sAccount,ed_sName,ed_bank,ed_dbranch,ed_account,ed_dname,ed_Amount,ed_mobileNumber,ed_Remark,ed_CurrencyCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_transer);
        initializeViews();
    }

    private void initializeViews() {
        ed_sBank= (EditText) findViewById(R.id.ed_sBank);
        ed_sBranch= (EditText) findViewById(R.id.ed_sBranch);
        ed_sAccount= (EditText) findViewById(R.id.ed_sAccount);
        ed_sName= (EditText) findViewById(R.id.ed_sName);
        ed_bank= (EditText) findViewById(R.id.ed_bank);
        ed_dbranch= (EditText) findViewById(R.id.ed_dbranch);
        ed_account= (EditText) findViewById(R.id.ed_account);
        ed_dname= (EditText) findViewById(R.id.ed_dname);
        ed_Amount= (EditText) findViewById(R.id.ed_Amount);
        ed_mobileNumber= (EditText) findViewById(R.id.ed_mobileNumber);
        ed_Remark= (EditText) findViewById(R.id.ed_Remark);
        ed_CurrencyCode= (EditText) findViewById(R.id.ed_CurrencyCode);

    }

    public void validateQuickTransferRetrofit()
    {
        long syncTIMESTAMP = System.currentTimeMillis();
        Date datefromat = new Date(syncTIMESTAMP);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dfOnlyDate = new SimpleDateFormat("yyyy-MM-dd");
       String  strActDate = df.format(datefromat);
        String strDateOnly=df.format(dfOnlyDate);
        apiInterface=ApiClent.getApiClient().create(APIInterface.class);
        //(@Query("userid") String userid,@Query("username") String userName,@Query("usertype") String usertype,@Query("account") String account,@Query("password") String password,@Query("mobile") String mobile,@Query("actdate") String actdate);
        Call<List<UserDetails>> call=apiInterface.getFTransferStatus(sBank,sBranch,sAccount,sName,bank,dBranch,account,dname,Amount,mobileNumber,Remark,strDateOnly,strActDate,CurrencyCode);
        call.enqueue(new Callback<List<UserDetails>>() {
            @Override
            public void onResponse(Call<List<UserDetails>> call, retrofit2.Response<List<UserDetails>> response) {
                System.out.println(" Data = "+response.body());
            }

            @Override
            public void onFailure(Call<List<UserDetails>> call, Throwable t) {
                System.out.println(" Data Fail = ");
            }
        });
    }

// String sBank,sBranch,sAccount,sName,bank,dBranch,account,dname,Amount,mobileNumber,Remark,CurrencyCode;
//EditText ed_sBank,ed_sBranch,ed_sAccount,ed_sName,ed_bank,ed_dbranch,ed_account,ed_dname,ed_Amount,ed_mobileNumber,ed_Remark,ed_CurrencyCode;

    public boolean validateRegisterFields()
    {
        boolean isAllDataFilled=false;
        if(!TextUtils.isEmpty(ed_sBank.getText().toString().trim()))
        {
            sBank=ed_sBank.getText().toString().trim();
            isAllDataFilled=true;

        }
        else
        {
            ed_sBank.setError("Please fill source Bank first to Transfer");
            return isAllDataFilled;
        }
        if(!TextUtils.isEmpty(ed_sBranch.getText().toString().trim()))
        {
            sBranch=ed_sBranch.getText().toString().trim();
            isAllDataFilled=true;
        }

        else
        {
            ed_sBranch.setError("Please fill userName to Register");
            return isAllDataFilled;
        }
      if(!TextUtils.isEmpty(ed_sAccount.getText().toString().trim()))
        {
            sAccount=ed_sAccount.getText().toString().trim();
            isAllDataFilled=true;
        }

        else
        {
            ed_sAccount.setError("Please fill userType to Register");
            return isAllDataFilled;
        }
        if(!TextUtils.isEmpty(ed_sName.getText().toString().trim()))
        {
            sName=ed_sName.getText().toString().trim();
            isAllDataFilled=true;
        }

        else
        {
            ed_sName.setError("Please fill account to Register");
            return isAllDataFilled;
        }
        if(!TextUtils.isEmpty(ed_bank.getText().toString().trim()))
        {
            bank=ed_bank.getText().toString().trim();
            isAllDataFilled=true;
        }

        else
        {
            ed_bank.setError("Please fill password to login");
            return isAllDataFilled;
        }
        if(!TextUtils.isEmpty(ed_dbranch.getText().toString().trim()))
        {
            dBranch=ed_dbranch.getText().toString().trim();
            isAllDataFilled=true;
        }

        else
        {
            ed_dbranch.setError("Please fill mobile to register");
            return isAllDataFilled;
        }
        if(!TextUtils.isEmpty(ed_account.getText().toString().trim()))
        {
            account=ed_account.getText().toString().trim();
            isAllDataFilled=true;
        }

        else
        {
            ed_account.setError("Please fill mobile to register");
            return isAllDataFilled;
        }
        if(!TextUtils.isEmpty(ed_dname.getText().toString().trim()))
        {
            dname=ed_dname.getText().toString().trim();
            isAllDataFilled=true;
        }

        else
        {
            ed_dname.setError("Please fill mobile to register");
            return isAllDataFilled;
        }
        if(!TextUtils.isEmpty(ed_Amount.getText().toString().trim()))
        {
            Amount=ed_Amount.getText().toString().trim();
            isAllDataFilled=true;
        }

        else
        {
            ed_Amount.setError("Please fill mobile to register");
            return isAllDataFilled;
        }
        if(!TextUtils.isEmpty(ed_mobileNumber.getText().toString().trim()))
        {
            mobileNumber=ed_mobileNumber.getText().toString().trim();
            isAllDataFilled=true;
        }

        else
        {
            ed_mobileNumber.setError("Please fill mobile to register");
            return isAllDataFilled;
        }



        return isAllDataFilled;
    }
}
