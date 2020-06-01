package top.yeek.gdmec_boxuegu.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        tvMainTitle.setText("设置");
        titleBar.setBackgroundColor(Color.parseColor("#30b4ff"));
    }

    @OnClick({R.id.tv_back, R.id.rl_modify_password, R.id.rl_security_setting, R.id.rl_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                this.finish();
                break;
            case R.id.rl_modify_password:
                Toast.makeText(this, "修改密码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_security_setting:
                Toast.makeText(this, "设置密保", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_login_out:
                clearLoginStatus();
                Intent intent = new Intent();
                intent.putExtra("isLogin", false);
                setResult(RESULT_OK, intent);
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
