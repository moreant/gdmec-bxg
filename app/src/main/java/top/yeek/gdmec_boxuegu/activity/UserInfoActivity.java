package top.yeek.gdmec_boxuegu.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.yeek.gdmec_boxuegu.R;
import top.yeek.gdmec_boxuegu.bean.UserBean;
import top.yeek.gdmec_boxuegu.utils.AnalysisUtils;
import top.yeek.gdmec_boxuegu.utils.DBUtils;

public class UserInfoActivity extends AppCompatActivity {

    public static final int CHANGE_NICKNAME = 1;
    public static final int CHANGE_SIGNATURE = 2;

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_main_title)
    TextView tvMainTitle;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.rl_avatar)
    RelativeLayout rlAvatar;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.rl_username)
    RelativeLayout rlUsername;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.rl_nickname)
    RelativeLayout rlNickname;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.rl_sex)
    RelativeLayout rlSex;
    @BindView(R.id.tv_signature)
    TextView tvSignature;
    @BindView(R.id.rl_signature)
    RelativeLayout rlSignature;
    private String spUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        tvMainTitle.setText("个人资料");
        titleBar.setBackgroundColor(Color.parseColor("#30b4ff"));
        spUserName = AnalysisUtils.readLoginUserName(this);
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        UserBean bean = null;
        bean = DBUtils.getInstance(this).getUserInfo(spUserName);
        // 如果数据库没有数据库 设置默认数据并存到数据库中
        if (bean == null) {
            bean = new UserBean();
            bean.userName = spUserName;
            bean.nickName = "moreant";
            bean.sex = "男";
            bean.signature = "这个人很懒";
            DBUtils.getInstance(this).saveUserInfo(bean);
        }
        setValue(bean);
    }

    /**
     * 将读取到的数据显示到视图中
     *
     * @param bean 用户实体
     */
    private void setValue(UserBean bean) {
        tvNickname.setText(bean.nickName);
        tvUsername.setText(bean.userName);
        tvSex.setText(bean.sex);
        tvSignature.setText(bean.signature);
    }


    @OnClick({R.id.tv_back, R.id.rl_nickname, R.id.rl_sex, R.id.rl_signature})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                this.finish();
                break;
            case R.id.rl_nickname:
                String name = tvNickname.getText().toString();
                enterActivityForResult(name, "昵称", CHANGE_NICKNAME);
                break;
            case R.id.rl_sex:
                String sex = tvSex.getText().toString();
                sexDialog(sex);
                break;
            case R.id.rl_signature:
                String signature = tvSignature.getText().toString();
                enterActivityForResult(signature, "签名", CHANGE_SIGNATURE);
                break;
        }
    }

    /**
     * 集中跳转
     *
     * @param content     值
     * @param title       标题
     * @param requestCode 请求代码
     */
    private void enterActivityForResult(String content, String title, int requestCode) {
        Bundle bundle = new Bundle();
        bundle.putString("content", content);
        bundle.putString("title", title);
        bundle.putInt("flag", requestCode);
        Intent intent = new Intent(this, ChangeUserInfoActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 显示设置性别对话框
     *
     * @param sex 当前性别
     */
    private void sexDialog(String sex) {
        int sexFlag = 0;
        if ("女".equals(sex)) {
            sexFlag = 1;
        }
        final String[] items = {"男", "女"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("性别");
        builder.setSingleChoiceItems(items, sexFlag, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(UserInfoActivity.this, items[which], Toast.LENGTH_SHORT).show();
                setSex(items[which]);
            }
        });
        builder.create().show();
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    private void setSex(String sex) {
        tvSex.setText(sex);
        DBUtils.getInstance(this).updateUserInfo("sex", sex, spUserName);
    }


    /**
     * 处理回传数据
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHANGE_NICKNAME:
                if (data != null) {
                    String nickName = data.getStringExtra("nickName");
                    tvNickname.setText(nickName);
                    DBUtils.getInstance(this).updateUserInfo("nickName", nickName, spUserName);
                }
                break;
            case CHANGE_SIGNATURE:
                if (data != null) {
                    String signature = data.getStringExtra("signature");
                    tvSignature.setText(signature);
                    DBUtils.getInstance(this).updateUserInfo("signature", signature, spUserName);
                }
                break;
        }
    }
}
