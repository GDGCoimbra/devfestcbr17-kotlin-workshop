package xyz.gdgcoimbra.devfest2017.kotlinfest.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import xyz.gdgcoimbra.devfest2017.kotlinfest.R;
import xyz.gdgcoimbra.devfest2017.kotlinfest.service.models.Show;
import xyz.gdgcoimbra.devfest2017.kotlinfest.utils.ViewUtils;

public class ShowViewHolder extends RecyclerView.ViewHolder {

    private final TextView mNameTextView;
    private final ImageView mPictureImageView;
    private final TextView mNetworkTextView;
    private final TextView mStatusTextView;
    private final TextView mRatingTextView;
    private final TextView mCountryTextView;
    private final TextView mGenresTextView;

    public ShowViewHolder(View itemView) {
        super(itemView);

        mNameTextView = itemView.findViewById(R.id.show_name);
        mPictureImageView = itemView.findViewById(R.id.show_picture);
        mNetworkTextView = itemView.findViewById(R.id.show_network);
        mStatusTextView = itemView.findViewById(R.id.show_status);
        mRatingTextView = itemView.findViewById(R.id.show_rating);
        mCountryTextView = itemView.findViewById(R.id.show_country);
        mGenresTextView = itemView.findViewById(R.id.show_genres);
    }

    public static ShowViewHolder inflate(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_view_holder, parent, false);
        return new ShowViewHolder(itemView);
    }

    public void bind(Show show) {
        Glide.with(itemView.getContext()).load(show.getImage().getMedium()).into(mPictureImageView);

        mNameTextView.setText(show.getName());
        if (show.getNetwork() != null) {
            ViewUtils.setVisible(mNetworkTextView);
            ViewUtils.setVisible(mCountryTextView);

            mNetworkTextView.setText(show.getNetwork().getName());
            mCountryTextView.setText(show.getNetwork().getCountry().getName() + " " + show.getNetwork().getCountry().getCode());
        } else {
            ViewUtils.setGone(mNetworkTextView);
            ViewUtils.setGone(mCountryTextView);
        }
        mStatusTextView.setText(show.getStatus());
        mRatingTextView.setText(show.getRating().getAverage().toString() + "*");

        StringBuilder sb = new StringBuilder();
        for (String genre : show.getGenres()) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(genre.toUpperCase());
        }

        mGenresTextView.setText(sb);
    }
}
