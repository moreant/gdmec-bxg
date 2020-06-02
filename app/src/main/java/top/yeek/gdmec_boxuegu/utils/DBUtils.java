package top.yeek.gdmec_boxuegu.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import top.yeek.gdmec_boxuegu.bean.UserBean;
import top.yeek.gdmec_boxuegu.sqlite.SQLiteHelper;

public class DBUtils {

    private final SQLiteHelper helper;
    private final SQLiteDatabase db;
    private static DBUtils instance;

    private DBUtils(Context context) {
        helper = new SQLiteHelper(context);
        db = helper.getWritableDatabase();
    }

    /**
     * 获取数据库对象
     *
     * @param context 上下文
     * @return 数据库对象
     */
    public static DBUtils getInstance(Context context) {
        if (instance == null) {
            instance = new DBUtils(context);
        }
        return instance;
    }

    /**
     * 保存用户信息
     *
     * @param bean 用户实体
     */
    public void saveUserInfo(UserBean bean) {
        ContentValues cv = new ContentValues();
        cv.put("userName", bean.userName);
        cv.put("nickName", bean.nickName);
        cv.put("sex", bean.sex);
        cv.put("signature", bean.signature);
        db.insert(SQLiteHelper.USER_INFO, null, cv);
    }

    /**
     * 查询用户信息
     *
     * @param userName 用户名
     * @return 用户实体
     */
    public UserBean getUserInfo(String userName) {
        String sql = "SELECT * FROM " + SQLiteHelper.USER_INFO + " WHERE userName = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{userName});
        UserBean bean = null;
        while (cursor.moveToNext()) {
            bean = new UserBean();
            bean.userName = cursor.getString(cursor.getColumnIndex("userName"));
            bean.nickName = cursor.getString(cursor.getColumnIndex("nickName"));
            bean.sex = cursor.getString(cursor.getColumnIndex("sex"));
            bean.signature = cursor.getString(cursor.getColumnIndex("signature"));
        }
        cursor.close();
        return bean;
    }

    /**
     * 更新用户信息
     *
     * @param key      名称
     * @param value    内容
     * @param userName 用户名
     */
    public void updateUserInfo(String key, String value, String userName) {
        ContentValues cv = new ContentValues();
        cv.put(key, value);
        db.update(SQLiteHelper.USER_INFO, cv, "userName = ?", new String[]{userName});
    }
}
