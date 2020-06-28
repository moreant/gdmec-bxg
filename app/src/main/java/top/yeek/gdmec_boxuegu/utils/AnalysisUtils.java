package top.yeek.gdmec_boxuegu.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Xml;
import android.widget.ImageView;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import top.yeek.gdmec_boxuegu.bean.ExercisesBean;

public class AnalysisUtils {

    /**
     * 读取用户名
     *
     * @param context 上下文
     * @return 用户名
     */
    public static String readLoginUserName(Context context) {
        SharedPreferences sp = context.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        return sp.getString("loginUserName", "");
    }

    public static List<ExercisesBean> getExercisesInfos(InputStream is) throws Exception {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "utf-8");
        List<ExercisesBean> exercisesBeans = null;
        ExercisesBean exercisesBean = null;
        int type = parser.getEventType();
        while (type != XmlPullParser.END_DOCUMENT) {
            switch (type) {
                case XmlPullParser.START_TAG:
                    if ("infos".equals(parser.getName())) {
                        exercisesBeans = new ArrayList<>();
                    } else if ("exercises".equals(parser.getName())) {
                        exercisesBean = new ExercisesBean();
                        String ids = parser.getAttributeValue(0);
                        exercisesBean.setSubjectId(Integer.parseInt(ids));
                    } else if ("subject".equals(parser.getName())) {
                        exercisesBean.setSubject(parser.nextText());
                    } else if ("a".equals(parser.getName())) {
                        exercisesBean.setA(parser.nextText());
                    } else if ("b".equals(parser.getName())) {
                        exercisesBean.setB(parser.nextText());
                    } else if ("c".equals(parser.getName())) {
                        exercisesBean.setC(parser.nextText());
                    } else if ("d".equals(parser.getName())) {
                        exercisesBean.setD(parser.nextText());
                    } else if ("answer".equals(parser.getName())) {
                        exercisesBean.setAnswer(Integer.parseInt(parser.nextText()));
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("exercises".equals(parser.getName())) {
                        exercisesBeans.add(exercisesBean);
                        exercisesBean = null;
                    }
                    break;
            }
            type = parser.next();
        }
        return exercisesBeans;
    }

    public static void setABCDEnable(boolean value, ImageView ivA, ImageView ivB, ImageView ivC, ImageView ivD) {
        ivA.setEnabled(value);
        ivB.setEnabled(value);
        ivC.setEnabled(value);
        ivD.setEnabled(value);
    }
}
