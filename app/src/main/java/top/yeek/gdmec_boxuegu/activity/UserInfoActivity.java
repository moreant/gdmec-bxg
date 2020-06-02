package top.yeek.gdmec_boxuegu.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.yeek.gdmec_boxuegu.R;

public class UserInfoActivity extends AppCompatActivity{

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_main_title)
    TextView tvMainTitle;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.im_avatar)
    ImageView imAvatar;
    @BindView(R.id.avatar)
    RelativeLayout avatar;
    @BindView(R.id.info_username)
    TextView infoUsername;
    @BindView(R.id.username)
    RelativeLayout username;
    @BindView(R.id.info_nickname)
    TextView infoNickname;
    @BindView(R.id.nickname)
    RelativeLayout nickname;
    @BindView(R.id.info_sex)
    TextView infoSex;
    @BindView(R.id.sex)
    RelativeLayout sex;
    @BindView(R.id.info_signature)
    TextView infoSignature;
    @BindView(R.id.signature)
    RelativeLayout signature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        tvMainTitle.setText("个人资料");
        titleBar.setBackgroundColor(Color.parseColor("#30b4ff"));
    }
}
