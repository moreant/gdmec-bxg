package top.yeek.gdmec_boxuegu.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.yeek.gdmec_boxuegu.R;
import top.yeek.gdmec_boxuegu.view.MyView;

public class MainActivity extends AppCompatActivity {

    private MyView myView;

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_main_title)
    TextView tvMainTitle;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.fl_body)
    FrameLayout flBody;
    @BindView(R.id.iv_course)
    ImageView ivCourse;
    @BindView(R.id.tv_course)
    TextView tvCourse;
    @BindView(R.id.rl_course)
    RelativeLayout rlCourse;
    @BindView(R.id.iv_exercises)
    ImageView ivExercises;
    @BindView(R.id.tv_exercises)
    TextView tvExercises;
    @BindView(R.id.rl_exercises)
    RelativeLayout rlExercises;
    @BindView(R.id.iv_my)
    ImageView ivMy;
    @BindView(R.id.tv_my)
    TextView tvMy;
    @BindView(R.id.rl_my)
    RelativeLayout rlMy;
    @BindView(R.id.ll_bottom_bar)
    LinearLayout llBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // 隐藏标题栏的返回按钮
        tvBack.setVisibility(View.GONE);
        tvMainTitle.setText("博学谷课程");
        titleBar.setBackgroundColor(Color.parseColor("#30B4FF"));
        setInitStatus();
    }

    private void setInitStatus() {
        clearBottomImagesState();
        setSelectedStatus(0);
        createView(0);
    }


    @OnClick({R.id.rl_course, R.id.rl_exercises, R.id.rl_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 课程
            case R.id.rl_course:
                clearBottomImagesState();
                selectDisplayView(0);
                break;
            // 习题
            case R.id.rl_exercises:
                clearBottomImagesState();
                selectDisplayView(1);
                break;
            // 我
            case R.id.rl_my:
                clearBottomImagesState();
                selectDisplayView(2);
                break;
        }
    }

    /**
     * 设置视图
     *
     * @param index 索引
     */
    private void selectDisplayView(int index) {
        removeAllView();
        createView(index);
        setSelectedStatus(index);
    }

    /**
     * 设置标题栏底部栏
     *
     * @param index 索引
     */
    private void setSelectedStatus(int index) {
        switch (index) {
            case 0:
                //课程
                rlCourse.setSelected(true);
                ivCourse.setImageResource(R.drawable.main_course_icon_selected);
                tvCourse.setTextColor(Color.parseColor("#0097f7"));
                titleBar.setVisibility(View.VISIBLE);
                tvMainTitle.setText("博学谷课程");
                break;
            case 1:
                // 习题
                rlExercises.setSelected(true);
                ivExercises.setImageResource(R.drawable.main_exercises_icon_selected);
                tvExercises.setTextColor(Color.parseColor("#0097f7"));
                titleBar.setVisibility(View.VISIBLE);
                tvMainTitle.setText("博学谷习题");
                break;
            case 2:
                // 我的
                rlMy.setSelected(true);
                ivMy.setImageResource(R.drawable.main_my_icon_selected);
                tvMy.setTextColor(Color.parseColor("#0097f7"));
                titleBar.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * 创建视图
     *
     * @param index 索引
     */
    private void createView(int index) {
        switch (index) {
            case 0:
                //课程
                Toast.makeText(this, "课程界面", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                // 习题
                Toast.makeText(this, "习题界面", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                // 我的
                if (myView == null) {
                    myView = new MyView(this);
                    flBody.addView(myView.getView());
                } else {
                    myView.getView();
                }
                myView.showView();
                break;
        }
    }

    /**
     * 隐藏其他视图
     */
    private void removeAllView() {
        for (int i = 0; i < flBody.getChildCount(); i++) {
            flBody.getChildAt(i).setVisibility(View.GONE);
        }
    }

    /**
     * 清除底部栏 mainBottomBar 的样式
     */
    private void clearBottomImagesState() {
        tvCourse.setTextColor(Color.parseColor("#666666"));
        tvExercises.setTextColor(Color.parseColor("#666666"));
        tvMy.setTextColor(Color.parseColor("#666666"));

        ivCourse.setImageResource(R.drawable.main_course_icon);
        ivExercises.setImageResource(R.drawable.main_exercises_icon);
        ivMy.setImageResource(R.drawable.main_my_icon);


        for (int i = 0; i < llBottomBar.getChildCount(); i++) {
            llBottomBar.getChildAt(i).setSelected(false);
        }
    }

    protected long exitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 退出博学谷
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                if (readLoginStatus()) {
                    clearLoginStatus();
                }
                MainActivity.this.finish();
                // 正常退出
                // 亲测会导致无法清除用户信息
                // System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 清除登录状态
     */
    private void clearLoginStatus() {
        SharedPreferences sp = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLogin", false);
        editor.putString("loginUserName", "");
        editor.apply();
    }

    /**
     * 读取登录状态
     *
     * @return true：已登录
     */
    private boolean readLoginStatus() {
        SharedPreferences sp = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        return sp.getBoolean("isLogin", false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            boolean isLogin = data.getBooleanExtra("isLogin", false);
            if (isLogin) {
                clearBottomImagesState();
                selectDisplayView(2);
            }

            if (myView != null) {
                myView.setLoginParams(isLogin);
            }
        }


    }
}
