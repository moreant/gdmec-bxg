package top.yeek.gdmec_boxuegu.activity;

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
import top.yeek.gdmec_boxuegu.utils.AnalysisUtils;
import top.yeek.gdmec_boxuegu.utils.MD5Utils;

public class FindPswActivity extends AppCompatActivity {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_main_title)
    TextView tvMainTitle;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_validate_name)
    EditText etValidateName;
    @BindView(R.id.tv_reset_password)
    TextView tvResetPassword;
    @BindView(R.id.btn_validate)
    Button btnValidate;
    private String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_psw);
        ButterKnife.bind(this);
        from = getIntent().getStringExtra("from");
        if ("security".equals(from)) {
            tvMainTitle.setText("设置密保");
        }else{
            tvMainTitle.setText("找回密码");
            tvUserName.setVisibility(View.VISIBLE);
            etUsername.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.tv_back, R.id.btn_validate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                this.finish();
                break;
            case R.id.btn_validate:
                String validateName = etValidateName.getText().toString().trim();
                if ("security".equals(from)) {
                    tvMainTitle.setText("设置密保");
                    if (TextUtils.isEmpty(validateName)) {
                        Toast.makeText(this, "请输入要验证的姓名", Toast.LENGTH_SHORT).show();
                    }else{
                        saveSecurity(validateName);
                        Toast.makeText(this, "设置成功", Toast.LENGTH_SHORT).show();
                        this.finish();
                    }
                }else{
                    String userName = etUsername.getText().toString().trim();
                    String sp_security =  readSecurity(userName);
                    if (TextUtils.isEmpty(userName)) {
                        Toast.makeText(this, "请输入您的用户名", Toast.LENGTH_SHORT).show();
                    } else if (!isExistUserName(userName)) {
                        Toast.makeText(this, "您输入的用户名不存在", Toast.LENGTH_SHORT).show();
                    } else if (!validateName.equals(sp_security)) {
                        Toast.makeText(this, "输入的密保不正确", Toast.LENGTH_SHORT).show();
                    } else {
                        tvResetPassword.setVisibility(View.VISIBLE);
                        tvResetPassword.setText("初始密码：123456");
                        savePsw(userName);
                    }
                }
                break;
        }
    }

    private void savePsw(String userName) {
        String md5Psw = MD5Utils.md5("123456");
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(userName, md5Psw);
        editor.apply();
    }

    private boolean isExistUserName(String userName) {
        boolean hasUsername = false;
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        String spPsw = sp.getString(userName, "");
        if (!TextUtils.isEmpty(spPsw)) {
            hasUsername = true;
        }
        return hasUsername;
    }

    /**
     * 读取密保
     * @param userName 用户名
     * @return 密保
     */
    private String readSecurity(String userName) {
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        return sp.getString(userName + "_security", "");
    }


    /**
     * 保存密保
     * @param validateName 密保
     */
    private void saveSecurity(String validateName) {
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(AnalysisUtils.readLoginUserName(this) + "_security", validateName);
        editor.apply();
    }

}
