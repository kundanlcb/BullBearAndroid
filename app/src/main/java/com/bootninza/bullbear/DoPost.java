package com.bootninza.bullbear;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bootninza.bullbear.util.SharedPreference;
import com.bootninza.bullbear.webservices.BullBearService;
import com.bootninza.bullbear.webservices.RestCallBack;
import com.bootninza.bullbear.webservices.RestClient;
import com.bootninza.bullbear.webservices.WebService;
import com.bootninza.bullbear.webservices.models.Feed;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DoPost extends AppCompatActivity implements RestCallBack<Object> {

    private BullBearService bullBearService;
    private EditText editTextPostContent;
    private SharedPreference preference;
    private WebService<Object> webService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_post);
        bullBearService = new RestClient().getRestClient().create(BullBearService.class);
        preference=new SharedPreference(getApplication());
        editTextPostContent = findViewById(R.id.editTextPostContent);
        webService = new WebService<>(this, getApplication());
    }

    public void captureFromCamera(View view) {
        System.out.println("Camera clicked");
    }

    public void captureFromGallery(View view) {
        System.out.println("Gallery clicked");
    }

    public void doPost(View view) {
        Feed feed = new Feed();
        feed.setPostContent(editTextPostContent.getText().toString());
        webService.addFeedApi(feed,preference.getToken());
        System.out.println("Post clicked");
    }


    @Override
    public void onSuccess(Object response) {
        onBackPressed();
        System.out.println("onResponse: "+response);
    }

    @Override
    public void onFailure(Throwable t) {
        System.out.println("onFailure: "+t.toString());
    }
}