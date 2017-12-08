package xyz.gdgcoimbra.devfest2017.kotlinfest;

import android.app.Application;
import android.util.Log;

import xyz.gdgcoimbra.devfest2017.kotlinfest.service.TvShowService;

public class KotlinFestApplication extends Application {

    private static final String LOG_TAG = "KotlinFestApplication";

    public static KotlinFestApplication INSTANCE;
    private TvShowService mTvShowService;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

    public TvShowService getTvShowService() {
        TvShowService result = mTvShowService;
        if (result == null) {
            synchronized (TvShowService.class) {
                result = mTvShowService;
                if (result == null) {
                    Log.d(LOG_TAG, "TvShowService initialized");
                    mTvShowService = result = new TvShowService();
                }
            }
        }
        return result;
    }
}
