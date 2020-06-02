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
import top.yeek.gdmec_boxuegu.utils.AnalysisUtils;
import top.yeek.gdmec_boxuegu.utils.MD5Utils;

public class ModifyPswActivity extends AppCompatActivity {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_main_title)
    TextView tvMainTitle;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.et_original_password)
    EditText etOriginalPassword;
    @BindView(R.id.et_new_password)
    EditText etNewPassword;
    @BindView(R.id.et_re_new_password)
    EditText etReNewPassword;
    @BindView(R.id.btn_save)
    Button btnSave;
    private String originPsw;
    private String newPsw;
    private String reNewPsw;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_psw);
        ButterKnife.bind(this);
        tvMainTitle.setText("修改密码");
        userName = AnalysisUtils.readLoginUserName(this);
    }

    @OnClick({R.id.tv_back, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                this.finish();
                break;
            case R.id.btn_save:
                getEditString();
                if (TextUtils.isEmpty(originPsw)) {
                    Toast.makeText(this, "请输入原始密码", Toast.LENGTH_SHORT).show();
                } else if (!MD5Utils.md5(originPsw).equals(readPsw(userName))) {
                    Toast.makeText(this, "输入的密码与原始密码不一致", Toast.LENGTH_SHORT).show();
                } else if (MD5Utils.md5(newPsw).equals(readPsw(userName))) {
                    Toast.makeText(this, "输入的密码与原始密码不能一致", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(newPsw)) {
                    Toast.makeText(this, "请输入新密码", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(reNewPsw)) {
                    Toast.makeText(this, "请再次输入新密码", Toast.LENGTH_SHORT).show();
                } else if (!newPsw.equals(reNewPsw)) {
                    Toast.makeText(this, "两次输入的新密码不一致", Toast.LENGTH_SHORT).show();
                } else {
                    modifyPsw(newPsw);
                    startActivity(new Intent(this, LoginActivity.class));
                    this.finish();
                    // 通过 Setting 的实例关闭 Setting 界面
                    SettingActivity.instance.finish();
                }
                break;
        }
    }

    /**
     * 修改密码
     *
     * @param newPsw 新密码
     */
    private void modifyPsw(String newPsw) {
        String md5Psw = MD5Utils.md5(newPsw);
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(userName, md5Psw);
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
     * 获取输入框的字符串
     */
    private void getEditString() {
        originPsw = etOriginalPassword.getText().toString().trim();
        newPsw = etNewPassword.getText().toString().trim();
        reNewPsw = etReNewPassword.getText().toString().trim();
    }
}
