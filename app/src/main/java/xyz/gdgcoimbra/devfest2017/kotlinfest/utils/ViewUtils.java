package xyz.gdgcoimbra.devfest2017.kotlinfest.utils;


import android.view.View;

public class ViewUtils {

    public static void setGone(View view) {
        view.setVisibility(View.GONE);
    }

    public static void setVisible(View view) {
        view.setVisibility(View.VISIBLE);
    }

    public static boolean isGone(View view) {
        return view.getVisibility() == View.GONE;
    }

    public static boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }
}
