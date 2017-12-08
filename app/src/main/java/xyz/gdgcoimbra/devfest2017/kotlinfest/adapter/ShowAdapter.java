package xyz.gdgcoimbra.devfest2017.kotlinfest.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import xyz.gdgcoimbra.devfest2017.kotlinfest.service.models.Show;

public class ShowAdapter extends RecyclerView.Adapter<ShowViewHolder> {

    private List<Show> mShows = new ArrayList<>();

    @Override
    public ShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ShowViewHolder.inflate(parent);
    }

    @Override
    public void onBindViewHolder(ShowViewHolder holder, int position) {
        holder.bind(mShows.get(position));
    }

    @Override
    public int getItemCount() {
        return mShows.size();
    }

    public void addShow(Show show) {
        mShows.add(show);
        notifyItemInserted(mShows.size() - 1);
    }

    public List<Show> getShows() {
        return mShows;
    }

    public void setShows(List<Show> newShows) {
        mShows.clear();
        mShows.addAll(newShows);
        notifyDataSetChanged();
    }
}
