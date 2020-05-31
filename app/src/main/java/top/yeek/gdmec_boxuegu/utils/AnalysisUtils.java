package top.yeek.gdmec_boxuegu.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AnalysisUtils {

    public static String readLoginUserName(Context context) {
        SharedPreferences sp = context.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        return sp.getString("loginUserName", "");
    }
}
