package com.bws.chillerfiller.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bws.chillerfiller.R;
import com.bws.chillerfiller.common.Common;
import com.bws.chillerfiller.common.Constant;
import com.bws.chillerfiller.itemcategory.productlist.ProductListActivity;
import com.bws.chillerfiller.signup.SignUpActivity;
import com.bws.chillerfiller.util.MyProgressDilaog;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;

public class LoginActivityTest extends AppCompatActivity {

    ProgressDialog myDialog;

    EditText edtEmail,edtPassword;
    Button btnLogin,btnSignUp;

    boolean isAllFieldsChecked = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAllFieldsChecked = CheckAllFields();
                //if (isAllFieldsChecked) {
                    //httpRequestLogin();
                startActivity(new Intent(LoginActivityTest.this, ProductListActivity.class));

                //}
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivityTest.this, SignUpActivity.class));
            }
        });
    }


    private void httpRequestLogin() {
        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("EmailID", "izhar.md89@gmail.com");
            jsonObject.put("password", "Izhar@123");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AsyncHttpClient client = new AsyncHttpClient();
        System.out.println("JSON==" + jsonObject.toString());
        String contentType = "application/json; charset=utf-8";
        HttpEntity entity = Common.entity(jsonObject);
        client.post(this, Common.BASE_URL + Common.UserloginDetails, entity, contentType, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                myDialog = MyProgressDilaog.showProgressDialog(LoginActivityTest.this, "Please Wait..");
                myDialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String asynchResult = new String(response);
                Log.d("Cart Respose===", asynchResult);
                try {
                    JSONObject object = new JSONObject(asynchResult);

                    startActivity(new Intent(LoginActivityTest.this, ProductListActivity.class));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] bytes, Throwable throwable) {
                Log.d("HTTP", "Post...");
            }

            @Override
            public void onFinish() {
                super.onFinish();
                myDialog.dismiss();
            }
        });
    }


    private boolean CheckAllFields() {
        if (edtEmail.length() == 0) {
            edtEmail.setError("Email id required");
            return false;
        }

        if (edtPassword.length() == 0) {
            edtPassword.setError("Password required");
            return false;
        } /*else if (etPassword.length() < 8) {
            etPassword.setError("Password must be minimum 8 characters");
            return false;
        }*/

        // after all validation return true.
        return true;
    }
}
