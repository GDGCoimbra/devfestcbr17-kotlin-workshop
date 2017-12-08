package xyz.gdgcoimbra.devfest2017.kotlinfest.service;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.gdgcoimbra.devfest2017.kotlinfest.utils.Constants;
import xyz.gdgcoimbra.devfest2017.kotlinfest.service.models.Show;

public class TvShowService {

    private final TvMazeClient mClient;

    public TvShowService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.SERVICE_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mClient = retrofit.create(TvMazeClient.class);
    }

    public void getShow(int showID, Callback<Show> callback) {
        mClient.getShow(showID).enqueue(callback);
    }
}
