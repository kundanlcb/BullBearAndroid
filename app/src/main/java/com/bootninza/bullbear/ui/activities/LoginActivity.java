package com.bootninza.bullbear.ui.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.bootninza.bullbear.R;
import com.bootninza.bullbear.webservices.RestCallBack;
import com.bootninza.bullbear.webservices.WebService;
import com.bootninza.bullbear.webservices.models.User;

public class LoginActivity extends AppCompatActivity implements RestCallBack<Object> {

    private EditText editTextPhone;
    private EditText editTextPassword;
    private WebService<Object> webService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextPassword = findViewById(R.id.edit_text_password);
        editTextPhone = findViewById(R.id.edit_text_phone);
        webService = new WebService<>(this, getApplication());
    }

    public void doLogin(View view) {
        webService.calLoginApi(editTextPhone.getText().toString(), editTextPassword.getText().toString());
    }

    @Override
    public void onSuccess(Object response) {
        User user = (User) response;
        Log.i("login successful", user.getUserName());

    }

    @Override
    public void onFailure(Throwable t) {
        Log.e("login  failed", t.getMessage());
    }
}