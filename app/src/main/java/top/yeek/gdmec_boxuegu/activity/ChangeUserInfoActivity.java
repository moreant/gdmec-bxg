package top.yeek.gdmec_boxuegu.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.yeek.gdmec_boxuegu.R;

import static top.yeek.gdmec_boxuegu.activity.UserInfoActivity.CHANGE_NICKNAME;
import static top.yeek.gdmec_boxuegu.activity.UserInfoActivity.CHANGE_SIGNATURE;


public class ChangeUserInfoActivity extends AppCompatActivity {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_main_title)
    TextView tvMainTitle;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.iv_delete)
    ImageView ivDelete;
    @BindView(R.id.tv_save)
    TextView tvSave;
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_info);
        ButterKnife.bind(this);
        String content = getIntent().getStringExtra("content");
        String title = getIntent().getStringExtra("title");
        flag = getIntent().getIntExtra("flag", 0);
        titleBar.setBackgroundColor(Color.parseColor("#30b4ff"));
        tvMainTitle.setText(title);
        tvSave.setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(content)) {
            etContent.setText(content);
            etContent.setSelection(content.length());
        }
        contentListener();
    }

    /**
     * 监听输入信息
     */
    private void contentListener() {
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Editable editable = etContent.getText();
                int len = editable.length();

                // 长度大于 0 时显示删除按钮
                if (len > 0) {
                    ivDelete.setVisibility(View.VISIBLE);
                } else {
                    ivDelete.setVisibility(View.GONE);
                }

                switch (flag) {
                    case CHANGE_NICKNAME:
                        if (len > 8) {
                            int selectionEnd = Selection.getSelectionEnd(editable);
                            String content = editable.toString().trim();
                            etContent.setText(content.substring(0, 8));
                            editable = etContent.getText();
                            // 不是很懂为什么书上要判断长度
                            // if (selectionEnd > editable.length()) {
                            //     selectionEnd = editable.length();
                            // }
                            Selection.setSelection(editable, 8);
                        }
                        break;
                    case CHANGE_SIGNATURE:
                        if (len > 16) {
                            int selectionEnd = Selection.getSelectionEnd(editable);
                            String content = editable.toString().trim();
                            etContent.setText(content.substring(0, 16));
                            editable = etContent.getText();
                            // if (selectionEnd > editable.length()) {
                            //     selectionEnd = editable.length();
                            // }
                            Selection.setSelection(editable, selectionEnd);
                        }
                        break;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @OnClick(R.id.tv_back)
    public void onTvBackClicked() {
        this.finish();
    }

    @OnClick(R.id.tv_save)
    public void onTvSaveClicked() {
        Intent intent = new Intent();
        String content = etContent.getText().toString().trim();
        switch (flag) {
            case CHANGE_NICKNAME:
                if (!TextUtils.isEmpty(content)) {
                    intent.putExtra("nickName", content);
                    setResult(RESULT_OK, intent);
                    Toast.makeText(this, "昵称保存成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case CHANGE_SIGNATURE:
                if (!TextUtils.isEmpty(content)) {
                    intent.putExtra("signature", content);
                    Toast.makeText(this, "签名保存成功", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(this, "签名不能为空", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    @OnClick(R.id.iv_delete)
    public void onIvDeleteClicked() {
        etContent.setText("");
    }
}
