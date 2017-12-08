package xyz.gdgcoimbra.devfest2017.kotlinfest;


import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xyz.gdgcoimbra.devfest2017.kotlinfest.service.models.Episode;
import xyz.gdgcoimbra.devfest2017.kotlinfest.service.models.Show;

public class TransformerSingleton {

    private static volatile TransformerSingleton INSTANCE;

    private TransformerSingleton() {

    }

    public static TransformerSingleton getInstance() {
        TransformerSingleton result = INSTANCE;
        if (result == null) {
            synchronized (TransformerSingleton.class) {
                result = INSTANCE;
                if (result == null) {
                    INSTANCE = result = new TransformerSingleton();
                }
            }
        }

        return result;
    }

    public boolean hasShowFromUK(List<Show> shows) {

        for (Show show : shows) {
            if (show.getNetwork() != null && show.getNetwork().getCountry().getCode().equals("GB")) {
                return true;
            }
        }
        return false;
    }

    public int countShowsRatingAbove8_5(List<Show> shows) {

        int count = 0;
        for (Show show : shows) {
            if (show.getRating().getAverage() >= 8.5) {
                count++;
            }
        }
        return count;
    }

    public boolean allShowsHaveRatingAbove7_7(List<Show> shows) {

        for (Show show : shows) {
            if (show.getRating().getAverage() < 7.7) {
                return false;
            }
        }
        return true;
    }

    public int getTotalEpisodesCount(List<Show> shows) {

        int count = 0;
        for (Show show : shows) {
            if (show.getEmbedded() != null) {
                count += show.getEmbedded().getEpisodes().size();
            }
        }
        return count;
    }

    public int getFirst4EpisodesCount(List<Show> shows) {

        int count = 0;
        for (int i = 0; i < shows.size() && i < 4; i++) {
            count += shows.get(i).getEmbedded().getEpisodes().size();
        }
        return count;
    }

    public Show getHighestRatingShow(List<Show> shows) {

        Show highest = shows.get(0);
        for (Show show : shows) {
            if (show.getRating().getAverage() > highest.getRating().getAverage()) {
                highest = show;
            }
        }
        return highest;
    }

    public Show getShowWithMostEpisodes(List<Show> shows) {

        Show highest = shows.get(0);
        for (Show show : shows) {
            if (show.getEmbedded().getEpisodes().size() > highest.getEmbedded().getEpisodes().size()) {
                highest = show;
            }
        }
        return highest;
    }

    public boolean hasNoShowFromPortugal(List<Show> shows) {

        for (Show show : shows) {
            if (show.getNetwork() != null && show.getNetwork().getCountry().getCode().equals("PT")) {
                return false;
            }
        }
        return true;
    }

    public double averageShowRating(List<Show> shows) {

        double averageRating = 0;
        for (Show show : shows) {
            averageRating += show.getRating().getAverage();
        }
        return averageRating / shows.size();
    }

    public List<Show> getShowsAbove9Rating(List<Show> shows) {

        List<Show> showsAbove9Rating = new ArrayList<>();
        for (Show show : shows) {
            if (show.getRating().getAverage() >= 9) {
                showsAbove9Rating.add(show);
            }
        }
        return showsAbove9Rating;
    }

    public List<Show> getShowsAtPosition1_3_6(List<Show> shows) {

        List<Show> showsAt1_3_6 = new ArrayList<>();
        showsAt1_3_6.add(shows.get(1));
        showsAt1_3_6.add(shows.get(3));
        showsAt1_3_6.add(shows.get(6));

        return showsAt1_3_6;
    }

    public List<Episode> getAllEpisodes(List<Show> shows) {

        List<Episode> episodes = new ArrayList<>();
        for (Show show : shows) {
            for (Episode episode : show.getEmbedded().getEpisodes()) {
                episodes.add(episode);
            }
        }
        return episodes;
    }

    public List<String> getStatusInUpperCase(List<Show> shows) {

        List<String> status = new ArrayList<>();
        for (Show show : shows) {
            status.add(show.getStatus().toUpperCase());
        }
        return status;
    }

    public Map<String, List<Show>> groupShowsByNetwork(List<Show> shows) {

        Map<String, List<Show>> status = new HashMap<>();
        for (Show show : shows) {

            String network;
            if (show.getNetwork() != null) {
                network = show.getNetwork().getName();
            } else {
                network = "No network";
            }

            if (status.get(network) == null) {
                status.put(network, new ArrayList<Show>());
            }
            status.get(network).add(show);
        }
        return status;
    }

    public Pair<List<Show>, List<Show>> splitByStatus(List<Show> shows) {
        List<Show> running = new ArrayList<>();
        List<Show> ended = new ArrayList<>();

        for (Show show : shows) {
            if (show.getStatus().equalsIgnoreCase("running")) {
                running.add(show);
            } else {
                ended.add(show);
            }
        }

        return new Pair<>(running, ended);
    }

}
