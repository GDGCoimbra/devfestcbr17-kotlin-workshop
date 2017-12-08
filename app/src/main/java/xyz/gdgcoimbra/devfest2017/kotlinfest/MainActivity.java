package xyz.gdgcoimbra.devfest2017.kotlinfest;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.gdgcoimbra.devfest2017.kotlinfest.adapter.ShowAdapter;
import xyz.gdgcoimbra.devfest2017.kotlinfest.service.TvShowService;
import xyz.gdgcoimbra.devfest2017.kotlinfest.service.models.Episode;
import xyz.gdgcoimbra.devfest2017.kotlinfest.service.models.Show;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";
    private final List<Show> mShowsOriginal = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = findViewById(R.id.recycler);
        final ShowAdapter adapter = new ShowAdapter();
        recyclerView.setAdapter(adapter);

        getShows(adapter);
        setupButton();
    }

    private void getShows(final ShowAdapter adapter) {
        TvShowService service = KotlinFestApplication.INSTANCE.getTvShowService();

        List<Integer> shows = new ArrayList<>();
        shows.add(31);
        shows.add(4);
        shows.add(13);
        shows.add(1850);
        shows.add(82);
        shows.add(169);
        shows.add(161);
        shows.add(19);
        shows.add(29);
        shows.add(73);
        shows.add(1859);
        shows.add(1851);
        shows.add(20263);
        shows.add(1871);
        shows.add(66);
        shows.add(5495);
        shows.add(11);
        shows.add(305);
        shows.add(24);
        shows.add(81);
        shows.add(7480);
        shows.add(335);


        for (Integer showID : shows) {
            service.getShow(showID, new Callback<Show>() {
                @Override
                public void onResponse(Call<Show> call, Response<Show> response) {
                    adapter.addShow(response.body());
                    mShowsOriginal.add(response.body());
                }

                @Override
                public void onFailure(Call<Show> call, Throwable t) {
                    Log.e(LOG_TAG, "onFailure ", t);
                }
            });
        }
    }

    private void setupButton() {
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenu(view);
            }
        });
    }

    public void showMenu(View view) {

        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();

        popup.setOnMenuItemClickListener(getMenuClickListeners());
        inflater.inflate(R.menu.magic_actions, popup.getMenu());
        popup.show();

    }

    private PopupMenu.OnMenuItemClickListener getMenuClickListeners() {
        return new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.has_show_from_uk: {

                        boolean result = TransformerSingleton.getInstance().hasShowFromUK(mShowsOriginal);

                        Log.d(LOG_TAG, "has_show_from_uk " + result);

                        showDialog("has_show_from_uk " + result);
                    }
                    break;

                    case R.id.number_of_shows_with_rating_above_8_5: {

                        int result = TransformerSingleton.getInstance().countShowsRatingAbove8_5(mShowsOriginal);

                        Log.d(LOG_TAG, "number_of_shows_with_rating_above_8_5 " + result);
                        showDialog("number_of_shows_with_rating_above_8_5 " + result);
                    }
                    break;

                    case R.id.all_shows_have_rating_above_7_7: {

                        boolean result = TransformerSingleton.getInstance().allShowsHaveRatingAbove7_7(mShowsOriginal);

                        Log.d(LOG_TAG, "all_shows_have_rating_above_7_7 " + result);
                        showDialog("all_shows_have_rating_above_7_7 " + result);

                    }
                    break;

                    case R.id.get_total_episodes_count: {

                        int result = TransformerSingleton.getInstance().getTotalEpisodesCount(mShowsOriginal);

                        Log.d(LOG_TAG, "get_total_episodes_count " + result);
                        showDialog("get_total_episodes_count " + result);
                    }
                    break;

                    case R.id.get_first_4_episodes_count: {

                        int result = TransformerSingleton.getInstance().getFirst4EpisodesCount(mShowsOriginal);

                        Log.d(LOG_TAG, "get_first_4_episodes_count " + result);
                        showDialog("get_first_4_episodes_count " + result);
                    }
                    break;

                    case R.id.get_highest_rating_show: {

                        Show result = TransformerSingleton.getInstance().getHighestRatingShow(mShowsOriginal);

                        Log.d(LOG_TAG, "get_highest_rating_show " + result.getName());
                        showDialog("get_highest_rating_show " + result.getName());
                    }
                    break;

                    case R.id.get_show_with_most_episodes: {

                        Show result = TransformerSingleton.getInstance().getShowWithMostEpisodes(mShowsOriginal);

                        Log.d(LOG_TAG, "get_show_with_most_episodes " + result.getName());
                        showDialog("get_show_with_most_episodes " + result.getName());
                    }
                    break;

                    case R.id.no_show_from_portugal: {

                        boolean result = TransformerSingleton.getInstance().hasNoShowFromPortugal(mShowsOriginal);

                        Log.d(LOG_TAG, "no_show_from_portugal " + result);
                        showDialog("no_show_from_portugal " + result);
                    }
                    break;

                    case R.id.average_show_rating: {

                        double result = TransformerSingleton.getInstance().averageShowRating(mShowsOriginal);

                        Log.d(LOG_TAG, "no_show_from_portugal " + result);
                        showDialog("no_show_from_portugal " + result);
                    }
                    break;

                    case R.id.get_shows_above_9_rating: {

                        List<Show> result = TransformerSingleton.getInstance().getShowsAbove9Rating(mShowsOriginal);

                        Log.d(LOG_TAG, "get_shows_above_9_rating " + result);
                        showDialog("get_shows_above_9_rating " + result);
                    }
                    break;

                    case R.id.get_shows_at_position_1_3_6: {

                        List<Show> result = TransformerSingleton.getInstance().getShowsAtPosition1_3_6(mShowsOriginal);

                        Log.d(LOG_TAG, "get_shows_at_position_1_3_6 " + result);
                        showDialog("get_shows_at_position_1_3_6 " + result);
                    }
                    break;

                    case R.id.get_all_episodes: {

                        List<Episode> result = TransformerSingleton.getInstance().getAllEpisodes(mShowsOriginal);

                        Log.d(LOG_TAG, "get_all_episodes " + result);
                        showDialog("get_all_episodes " + result);
                    }
                    break;

                    case R.id.get_status_in_upper_case: {

                        List<String> result = TransformerSingleton.getInstance().getStatusInUpperCase(mShowsOriginal);

                        Log.d(LOG_TAG, "get_status_in_upper_case " + result);
                        showDialog("get_status_in_upper_case " + result);
                    }
                    break;

                    case R.id.group_shows_by_network: {

                        Map<String, List<Show>> result = TransformerSingleton.getInstance().groupShowsByNetwork(mShowsOriginal);

                        Log.d(LOG_TAG, "group_shows_by_network " + result);
                        showDialog("group_shows_by_network " + result);
                    }
                    break;

                    case R.id.split_by_status: {

                        Pair<List<Show>, List<Show>> result = TransformerSingleton.getInstance().splitByStatus(mShowsOriginal);

                        Log.d(LOG_TAG, "split_by_status " + result);
                        showDialog("split_by_status " + result);
                    }
                    break;
                }

                return false;
            }
        };
    }


    private void showDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setPositiveButton(android.R.string.ok, null);
        builder.setMessage(message);
        builder.create().show();
    }
}
