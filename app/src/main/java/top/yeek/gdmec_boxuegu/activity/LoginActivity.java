package top.yeek.gdmec_boxuegu.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import top.yeek.gdmec_boxuegu.utils.MD5Utils;

/**
 * 登录界面逻辑
 */
public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_main_title)
    TextView tvMainTitle;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_psw)
    EditText etPsw;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_find_psw)
    TextView tvFindPsw;
    @BindView(R.id.activity_login)
    LinearLayout activityLogin;
    private String userName;
    private String psw;
    private String spPsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        titleBar.setBackgroundColor(Color.TRANSPARENT);
        tvMainTitle.setText("登录");
    }

    // 点击事件
    @OnClick({R.id.tv_back, R.id.btn_login, R.id.tv_register, R.id.tv_find_psw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 回到上一页
            case R.id.tv_back:
                LoginActivity.this.finish();
                break;
            case R.id.btn_login:
                userName = etUserName.getText().toString().trim();
                psw = etPsw.getText().toString().trim();
                String md5Psw = MD5Utils.md5(psw);
                spPsw = readPsw(userName);

                // 输入验证
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(psw)) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else if (!md5Psw.equals(spPsw) && TextUtils.isEmpty(spPsw)) {
                    Toast.makeText(this, "输入的用户名和密码不一致", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(spPsw)) {
                    Toast.makeText(this, "用户名不存在", Toast.LENGTH_SHORT).show();
                } else if (md5Psw.equals(spPsw)) {
                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                    saveLoginStatus(userName);
                    Intent data = new Intent();
                    data.putExtra("isLogin", true);
                    setResult(RESULT_OK, data);
                    LoginActivity.this.finish();
                }
                break;
            case R.id.tv_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_find_psw:
                Toast.makeText(this, "跳转到返回密码界面，待完成", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 保存登录信息
     *
     * @param userName 用户名
     */
    private void saveLoginStatus(String userName) {
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLogin", true);
        editor.putString("loginUserName", userName);
        editor.apply();
    }

    /**
     * 读取密码
     *
     * @param userName 用户名
     * @return 用户名对应的密码
     */
    private String readPsw(String userName) {
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        return sp.getString(userName, "");
    }

    /**
     * 读取注册页面返回的数据
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            String userName = data.getStringExtra("userName");
            if (TextUtils.isEmpty(userName)) {
                etUserName.setText(userName);
                etUserName.setSelection(userName.length());
            }
        }

    }
}
