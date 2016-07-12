package greenway.com.gt.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {

    private static final String FILE = "prefData";
    private static final String NAME = "NAME";
    private SharedPreferences sp;

    public void setName(Context context, String name) {
        sp = context.getSharedPreferences(FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(NAME, name);
        editor.commit();
    }

    public static String getName(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(FILE, Context.MODE_PRIVATE);
        return prefs.getString(NAME, null);
    }

}

