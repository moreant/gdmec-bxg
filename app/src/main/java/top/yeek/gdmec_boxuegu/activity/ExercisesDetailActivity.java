package top.yeek.gdmec_boxuegu.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.yeek.gdmec_boxuegu.R;
import top.yeek.gdmec_boxuegu.adapter.ExercisesDetailAdapter;
import top.yeek.gdmec_boxuegu.bean.ExercisesBean;
import top.yeek.gdmec_boxuegu.utils.AnalysisUtils;

public class ExercisesDetailActivity extends AppCompatActivity {


    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_main_title)
    TextView tvMainTitle;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.lv_exercise)
    ListView lvExercise;
    private int id;
    private String title;
    private List<ExercisesBean> ebl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_detail);
        ButterKnife.bind(this);

        id = getIntent().getIntExtra("id", 0);
        title = getIntent().getStringExtra("title");

        initData();

        titleBar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tvMainTitle.setText(title);

        TextView tv = new TextView(this);
        tv.setTextColor(Color.parseColor("#000000"));
        tv.setText("一、选择题");
        tv.setTextSize(16.0f);
        tv.setPadding(10, 15, 0, 0);
        lvExercise.addHeaderView(tv);

        ExercisesDetailAdapter adapter =  new ExercisesDetailAdapter(this, new ExercisesDetailAdapter.OnSelectListener() {
            @Override
            public void onSelectA(int position, ImageView ivA, ImageView ivB, ImageView ivC, ImageView ivD) {
                if (ebl.get(position).getAnswer() != 1) {
                    ebl.get(position).setSelect(1);
                } else {
                    ebl.get(position).setSelect(0);
                }

                switch (ebl.get(position).getAnswer()) {
                    case 1:
                        ivA.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 2:
                        ivA.setImageResource(R.drawable.exercises_right_icon);
                        ivB.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 3:
                        ivA.setImageResource(R.drawable.exercises_right_icon);
                        ivC.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 4:
                        ivA.setImageResource(R.drawable.exercises_right_icon);
                        ivD.setImageResource(R.drawable.exercises_error_icon);
                        break;
                }
                AnalysisUtils.setABCDEnable(false, ivA, ivB, ivC, ivD);
            }

            @Override
            public void onSelectB(int position, ImageView ivA, ImageView ivB, ImageView ivC, ImageView ivD) {
                if (ebl.get(position).getAnswer() != 2) {
                    ebl.get(position).setSelect(2);
                } else {
                    ebl.get(position).setSelect(0);
                }

                switch (ebl.get(position).getAnswer()) {
                    case 1:
                        ivB.setImageResource(R.drawable.exercises_right_icon);
                        ivA.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 2:
                        ivB.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 3:
                        ivB.setImageResource(R.drawable.exercises_right_icon);
                        ivC.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 4:
                        ivB.setImageResource(R.drawable.exercises_right_icon);
                        ivD.setImageResource(R.drawable.exercises_error_icon);
                        break;
                }
                AnalysisUtils.setABCDEnable(false, ivA, ivB, ivC, ivD);
            }

            @Override
            public void onSelectC(int position, ImageView ivA, ImageView ivB, ImageView ivC, ImageView ivD) {
                if (ebl.get(position).getAnswer() != 3) {
                    ebl.get(position).setSelect(3);
                } else {
                    ebl.get(position).setSelect(0);
                }

                switch (ebl.get(position).getAnswer()) {
                    case 1:
                        ivC.setImageResource(R.drawable.exercises_right_icon);
                        ivA.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 2:
                        ivC.setImageResource(R.drawable.exercises_right_icon);
                        ivB.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 3:
                        ivC.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 4:
                        ivC.setImageResource(R.drawable.exercises_right_icon);
                        ivD.setImageResource(R.drawable.exercises_error_icon);
                        break;
                }
                AnalysisUtils.setABCDEnable(false, ivA, ivB, ivC, ivD);
            }

            @Override
            public void onSelectD(int position, ImageView ivA, ImageView ivB, ImageView ivC, ImageView ivD) {
                if (ebl.get(position).getAnswer() != 4) {
                    ebl.get(position).setSelect(4);
                } else {
                    ebl.get(position).setSelect(0);
                }

                switch (ebl.get(position).getAnswer()) {
                    case 1:
                        ivD.setImageResource(R.drawable.exercises_right_icon);
                        ivA.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 2:
                        ivD.setImageResource(R.drawable.exercises_right_icon);
                        ivB.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 3:
                        ivD.setImageResource(R.drawable.exercises_right_icon);
                        ivC.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 4:
                        ivD.setImageResource(R.drawable.exercises_right_icon);
                        break;
                }
                AnalysisUtils.setABCDEnable(false, ivA, ivB, ivC, ivD);
            }
        });

        adapter.setData(ebl);
        lvExercise.setAdapter(adapter);
    }

    private void initData() {
        InputStream is = null;
        try {
            is = getResources().getAssets().open("chapter" + id + ".xml");
            ebl = AnalysisUtils.getExercisesInfos(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.tv_back)
    public void onViewClicked() {
        finish();
    }
}
