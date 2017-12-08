package xyz.gdgcoimbra.devfest2017.kotlinfest.service;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import xyz.gdgcoimbra.devfest2017.kotlinfest.service.models.Show;

public interface TvMazeClient {

    @GET(" http://api.tvmaze.com/shows/{showID}?embed=episodes")
    Call<Show> getShow(@Path("showID") int showID);
}
