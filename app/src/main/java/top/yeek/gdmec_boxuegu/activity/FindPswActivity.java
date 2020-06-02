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
                    Toast.makeText(this, "找回密码", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void saveSecurity(String validateName) {
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(AnalysisUtils.readLoginUserName(this) + "_security", validateName);
        editor.apply();
    }

}
