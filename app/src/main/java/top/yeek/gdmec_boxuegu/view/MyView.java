package top.yeek.gdmec_boxuegu.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.yeek.gdmec_boxuegu.R;
import top.yeek.gdmec_boxuegu.activity.LoginActivity;
import top.yeek.gdmec_boxuegu.utils.AnalysisUtils;

public class MyView {


    private final Activity activity;
    private final LayoutInflater inflater;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.ll_head)
    LinearLayout llHead;
    @BindView(R.id.iv_course_history)
    ImageView ivCourseHistory;
    @BindView(R.id.rl_history)
    RelativeLayout rlHistory;
    @BindView(R.id.iv_user_info)
    ImageView ivUserInfo;
    @BindView(R.id.rl_setting)
    RelativeLayout rlSetting;
    @BindView(R.id.main_view_my)
    LinearLayout mainViewMy;
    private View view;

    public MyView(Activity activity) {
        this.activity = activity;
        inflater = LayoutInflater.from(this.activity);
    }

    /**
     * 创建视图
     */
    private void createView() {
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        view = inflater.inflate(R.layout.main_view_my, null);
        ButterKnife.bind(this, view);
    }

    @OnClick({R.id.ll_head, R.id.rl_history, R.id.rl_setting})
    void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_head:
                if (readLoginStatus()) {
                    // 跳转到个人页面
                    Toast.makeText(activity, "个人页面", Toast.LENGTH_SHORT).show();
                } else {
                    // 跳转到登录界面
                    Intent intent = new Intent(activity, LoginActivity.class);
                    activity.startActivityForResult(intent, 1);
                }
                break;
            case R.id.rl_history:
                if (readLoginStatus()) {
                    Toast.makeText(activity, "历史记录", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity, "您还未登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rl_setting:
                if (readLoginStatus()) {
                    Toast.makeText(activity, "设置", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity, "您还未登录", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * 设置用户名
     * @param isLogin 是否已登录
     */
    public void setLoginParams(boolean isLogin) {
        if (isLogin) {
            tvUsername.setText(AnalysisUtils.readLoginUserName(activity));
        } else {
            tvUsername.setText("点击登录");
        }
    }

    /**
     * 单例
     *
     * @return 返回视图
     */
    public View getView() {
        if (view == null) {
            createView();
        }
        return view;
    }

    public void showView() {
        if (view == null) {
            createView();
        }
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 是否登录
     *
     * @return true: 登录
     */
    private boolean readLoginStatus() {
        SharedPreferences sp = activity.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        return sp.getBoolean("isLogin", false);
    }


}
