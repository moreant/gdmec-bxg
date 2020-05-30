package top.yeek.gdmec_boxuegu.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.yeek.gdmec_boxuegu.R;
import top.yeek.gdmec_boxuegu.utils.MD5Utils;

public class RegisterActivity extends AppCompatActivity {

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
    @BindView(R.id.et_psw_again)
    EditText etPswAgain;
    @BindView(R.id.btn_register)
    Button btnRegister;

    private String userName;
    private String psw;
    private String psw_again;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }


    private void saveRegisterInfo(String userName, String psw) {
        String md5Psw = MD5Utils.md5(psw);
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(userName, md5Psw);
        editor.apply();
    }

    private boolean isExistUserName(String userName) {
        boolean has_userName = false;
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        String spPsw = sp.getString(userName, "");
        if (!TextUtils.isEmpty(spPsw)) {
            has_userName = true;
        }
        return has_userName;
    }

    private void getEditString() {
        userName = etUserName.getText().toString().trim();
        psw = etPsw.getText().toString().trim();
        psw_again = etPswAgain.getText().toString().trim();
    }

    @OnClick({R.id.tv_back, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                RegisterActivity.this.finish();
                break;
            case R.id.btn_register:
                getEditString();
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(RegisterActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(psw)) {
                    Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(psw_again)) {
                    Toast.makeText(RegisterActivity.this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                } else if (!psw.equals(psw_again)) {
                    Toast.makeText(RegisterActivity.this, "输入两次的密码不一样", Toast.LENGTH_SHORT).show();
                } else if (isExistUserName(userName)) {
                    Toast.makeText(RegisterActivity.this, "次用户名已存在", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    saveRegisterInfo(userName, psw);
                    Intent data = new Intent();
                    data.putExtra("userName", userName);
                    setResult(RESULT_OK, data);
                    RegisterActivity.this.finish();
                }
                break;
        }
    }
}
