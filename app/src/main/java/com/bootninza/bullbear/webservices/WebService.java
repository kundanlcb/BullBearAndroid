package com.bootninza.bullbear.webservices;

import android.app.Application;
import androidx.annotation.NonNull;
import com.bootninza.bullbear.util.SharedPreference;
import com.bootninza.bullbear.webservices.models.Equity;
import com.bootninza.bullbear.webservices.models.Feed;
import com.bootninza.bullbear.webservices.models.User;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebService<T> implements Callback<T> {
    private final RestCallBack<T> restCallBack;
    private final BullBearService bullBearService;
    private final SharedPreference preference;

    public WebService(RestCallBack<T> restCallBack, Application application) {
        preference = new SharedPreference(application);
        bullBearService = new RestClient().getRestClient().create(BullBearService.class);
        this.restCallBack = restCallBack;
    }

    public void callFeedApi() {
        bullBearService.getFeeds(preference.getToken()).enqueue((Callback<List<Feed>>) this);
    }

    public void callEquityApi() {
        bullBearService.getEquities(preference.getToken()).enqueue((Callback<List<Equity>>) this);
    }

    public void calLoginApi(String phone, String password) {
        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        bullBearService.login(user).enqueue((Callback<User>) this);
    }

    @Override
    public void onResponse(@NonNull Call<T> call, Response<T> response) {
        String token = response.headers().get("Authorization");
        if (null != token) {
            preference.addToken(token);
        }
        restCallBack.onSuccess(response.body());
    }

    @Override
    public void onFailure(@NonNull Call<T> call, Throwable t) {
        restCallBack.onFailure(t);
    }

    public void addFeedApi(Feed feed, String token) {
        bullBearService.addFeed(feed, token).enqueue((Callback<Feed>) this);
    }
}
