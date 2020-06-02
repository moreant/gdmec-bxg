package top.yeek.gdmec_boxuegu.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.yeek.gdmec_boxuegu.R;

public class SettingActivity extends AppCompatActivity {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_main_title)
    TextView tvMainTitle;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.rl_modify_password)
    RelativeLayout rlModifyPassword;
    @BindView(R.id.rl_security_setting)
    RelativeLayout rlSecuritySetting;
    @BindView(R.id.rl_login_out)
    RelativeLayout rlLoginOut;
    public static SettingActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        tvMainTitle.setText("设置");
        // 设置实例，使得其他界面能关闭本界面
        instance = this;
        titleBar.setBackgroundColor(Color.parseColor("#30b4ff"));
    }

    @OnClick({R.id.tv_back, R.id.rl_modify_password, R.id.rl_security_setting, R.id.rl_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                this.finish();
                break;
            case R.id.rl_modify_password:
                startActivity(new Intent(this, ModifyPswActivity.class));
                break;
            case R.id.rl_security_setting:
                Intent intent = new Intent(this, FindPswActivity.class);
                intent.putExtra("from", "security");
                startActivity(intent);
                break;
            case R.id.rl_login_out:
                clearLoginStatus();
                setResult(RESULT_OK, new Intent().putExtra("isLogin", false));
                this.finish();
                break;
        }
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
}
