package com.example.nitishdubey.mykcbanywhere;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nitish Dubey on 24-01-2018.
 */


/* params.put("userid",strUserId);
         params.put("username",strUserName);
         params.put("usertype",strUserType);
         params.put("account",straccount);
         params.put("password",strPassword);
         params.put("mobile",strMobile);
         long syncTIMESTAMP = System.currentTimeMillis();
         Date datefromat = new Date(syncTIMESTAMP);
         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         String strActDate = df.format(datefromat);
         params.put("actdate",strActDate);*/
public class UserDetails {

    //String strUserId,strUserName,strUserType,straccount,strPassword,strMobile;
    @SerializedName("response")
    private String strResponse;


    public String getStrResponse()
    {
        return strResponse;
    }

}
