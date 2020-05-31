package top.yeek.gdmec_boxuegu.view;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import top.yeek.gdmec_boxuegu.R;
import top.yeek.gdmec_boxuegu.activity.LoginActivity;

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

    public MyView(Activity activity) {
        this.activity = activity;
        inflater = LayoutInflater.from(this.activity);
    }

    private void createView() {
        initView();
    }

    private void initView() {
        inflater.inflate(R.layout.main_view_my, null);
    }

    @OnClick({R.id.ll_head, R.id.rl_history, R.id.rl_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_head:
                if (readLoginStatus()) {
                    // 跳转到个人页面
                } else {
                    // 跳转到登录界面
                    Intent intent = new Intent(activity, LoginActivity.class);
                    activity.startActivityForResult(intent, 1);
                }
                break;
            case R.id.rl_history:
                break;
            case R.id.rl_setting:
                break;
        }
    }

    private boolean readLoginStatus() {
        return false;
    }
}
