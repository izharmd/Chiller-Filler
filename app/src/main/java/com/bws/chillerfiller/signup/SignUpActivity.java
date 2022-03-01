package com.bws.chillerfiller.signup;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bws.chillerfiller.R;
import com.bws.chillerfiller.common.Common;
import com.bws.chillerfiller.util.MyProgressDilaog;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;

public class SignUpActivity extends AppCompatActivity {

    TextView txtNewRagistraion;
    EditText edtTitle, edtFName, edtLName, edtEmail, edtMobileNo;
    Button txtRegister;
    LinearLayout llDontHaveAccount;
    boolean isAllFieldsChecked = false;
    ProgressDialog myDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        initView();

        clickEvent();
    }

    private void clickEvent() {

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAllFieldsChecked = CheckAllFields();
                if (isAllFieldsChecked) {
                    httpRequetRegister();
                }
            }
        });

        findViewById(R.id.txtAllreadyAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void httpRequetRegister() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("RestaurantID_FK", "1");
            jsonObject.put("Title",edtTitle.getText().toString());
            jsonObject.put("FirstName",edtFName.getText().toString());
            jsonObject.put("LastName",edtLName.getText().toString());
            jsonObject.put("EmailID",edtEmail.getText().toString());
            jsonObject.put("MobilePhone",edtMobileNo.getText().toString());
            // jsonObject.put("IsActive","Y");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AsyncHttpClient client = new AsyncHttpClient();
        System.out.println("JSON=="+jsonObject.toString());
        String contentType = "application/json; charset=utf-8";
        HttpEntity entity = Common.entity(jsonObject);
        client.post(this, Common.BASE_URL + Common.RegistrationDetails, entity, contentType, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                myDialog = MyProgressDilaog.showProgressDialog(SignUpActivity.this, "Please Wait..");
                myDialog.show();
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String asynchResult = new String(response);
                Log.d("Cart Respose===", asynchResult);
                Toast.makeText(SignUpActivity.this,asynchResult,Toast.LENGTH_LONG).show();
                finish();
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

    private void initView() {
        txtRegister = findViewById(R.id.txtRegister);
        edtTitle = findViewById(R.id.edtTitle);
        edtFName = findViewById(R.id.edtFName);
        edtLName = findViewById(R.id.edtLName);
        edtEmail = findViewById(R.id.edtEmail);
        edtMobileNo = findViewById(R.id.edtMobileNo);
        llDontHaveAccount = findViewById(R.id.llDontHaveAccount);

    }

    private boolean CheckAllFields() {
        if (edtTitle.length() == 0) {
            edtTitle.setError("Title is required");
            return false;
        }
        if (edtFName.length() == 0) {
            edtFName.setError("First Name is required");
            return false;
        }
        if (edtLName.length() == 0) {
            edtLName.setError("Last Name is required");
            return false;
        }
        if (edtEmail.length() == 0) {
            edtEmail.setError("Email is required");
            return false;
        }
        if (edtMobileNo.length() == 0) {
            edtMobileNo.setError("Phone No is required");
            return false;
        }
        return true;
    }
}
