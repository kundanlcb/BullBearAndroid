package com.bootninza.bullbear.ui.home;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.bootninza.bullbear.webservices.RestCallBack;
import com.bootninza.bullbear.webservices.WebService;
import com.bootninza.bullbear.webservices.models.Feed;

import java.util.Collections;
import java.util.List;

public class HomeViewModel extends AndroidViewModel implements RestCallBack<Object> {

    private final MutableLiveData<List<Feed>> liveFeedData;
    private final WebService<Object> webService;


    public HomeViewModel(@NonNull Application application) {
        super(application);
        webService= new WebService<>(this,application);
        liveFeedData = new MutableLiveData<>();
    }

    public void callFeedApi() {
        webService.callFeedApi();
    }


    public MutableLiveData<List<Feed>> getFeed() {
        return liveFeedData;
    }



    @Override
    public void onSuccess(Object response) {
        Log.i("HVM -onResponse",response.toString());
        List<Feed> feedList= (List<Feed>) response;
        Collections.sort(feedList, (o1, o2) -> o2.getUpdateDateTime().compareTo(o1.getUpdateDateTime()));
        liveFeedData.setValue(feedList);
    }

    @Override
    public void onFailure(Throwable t) {
        Log.i("HVM -onResponse",t.toString());
    }
}