package com.bootninza.bullbear.ui.dashboard;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bootninza.bullbear.webservices.BullBearService;
import com.bootninza.bullbear.webservices.RestCallBack;
import com.bootninza.bullbear.webservices.RestClient;
import com.bootninza.bullbear.webservices.WebService;
import com.bootninza.bullbear.webservices.models.Equity;
import com.bootninza.bullbear.webservices.models.Feed;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardViewModel extends AndroidViewModel implements RestCallBack<Object> {

    private final MutableLiveData<List<Equity>> liveEquityData;
    private MutableLiveData<String> mText;
    private final WebService<Object> webService;

    public DashboardViewModel(@NonNull Application application) {
        super(application);
        webService=new WebService<Object>(this,application);
        liveEquityData = new MutableLiveData<>();
    }

    public void callEquityApi() {
        webService.callEquityApi();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<List<Equity>> getEquities() {
        return liveEquityData;
    }

    @Override
    public void onSuccess(Object response) {
        Log.i("HVM -onResponse",response.toString());
        List<Equity> equityList= (List<Equity>) response;
        liveEquityData.setValue(equityList);
    }

    @Override
    public void onFailure(Throwable t) {

    }
}