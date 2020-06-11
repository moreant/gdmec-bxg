package top.yeek.gdmec_boxuegu.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.yeek.gdmec_boxuegu.R;
import top.yeek.gdmec_boxuegu.adapter.ExerciseAdapter;
import top.yeek.gdmec_boxuegu.bean.ExercisesBean;

/**
 * @author Moreant
 * @Date: 2020/06/11 21:25
 */
public class ExercisesView {

    private final Activity activity;
    private final LayoutInflater layoutInflater;
    @BindView(R.id.lv_exercises_list)
    ListView lvExercisesList;


    private View view;
    private ExerciseAdapter exerciseAdapter;
    private ArrayList<ExercisesBean> exercisesBeans;

    public ExercisesView(Activity activity) {
        this.activity = activity;
        layoutInflater = LayoutInflater.from(activity);
    }

    private void createView() {
        initView();
    }

    private void initView() {
        view = layoutInflater.inflate(R.layout.main_view_exercises, null);
        ButterKnife.bind(this, view);
        exerciseAdapter = new ExerciseAdapter(activity);
        initData();
        exerciseAdapter.setData(exercisesBeans);
        lvExercisesList.setAdapter(exerciseAdapter);
    }

    private void initData() {
        exercisesBeans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ExercisesBean exercisesBean = new ExercisesBean();
            exercisesBean.setId(i + 1);
            switch (i) {
                case 0:
                    exercisesBean.setTitle("第 1 章 Android 基础入门");
                    exercisesBean.setContent("共计 5 题");
                    exercisesBean.setBackground(R.drawable.exercises_bg_1);
                    break;
                case 1:
                    exercisesBean.setTitle("第 2 章 Android UI开发");
                    exercisesBean.setContent("共计 5 题");
                    exercisesBean.setBackground(R.drawable.exercises_bg_2);
                    break;
                case 2:
                    exercisesBean.setTitle("第 3 章 Activity");
                    exercisesBean.setContent("共计 5 题");
                    exercisesBean.setBackground(R.drawable.exercises_bg_3);
                    break;
                case 3:
                    exercisesBean.setTitle("第 4 章 数据存储");
                    exercisesBean.setContent("共计 5 题");
                    exercisesBean.setBackground(R.drawable.exercises_bg_4);
                    break;
                case 4:
                    exercisesBean.setTitle("第 5 章 SQLite数据库");
                    exercisesBean.setContent("共计 5 题");
                    exercisesBean.setBackground(R.drawable.exercises_bg_1);
                    break;
                case 5:
                    exercisesBean.setTitle("第 6 章 广播接收者");
                    exercisesBean.setContent("共计 5 题");
                    exercisesBean.setBackground(R.drawable.exercises_bg_2);
                    break;
                case 6:
                    exercisesBean.setTitle("第 7 章 服务");
                    exercisesBean.setContent("共计 5 题");
                    exercisesBean.setBackground(R.drawable.exercises_bg_3);
                    break;
                case 7:
                    exercisesBean.setTitle("第 8 章 内容提供者");
                    exercisesBean.setContent("共计 5 题");
                    exercisesBean.setBackground(R.drawable.exercises_bg_4);
                    break;
                case 8:
                    exercisesBean.setTitle("第 9 章 网络编程");
                    exercisesBean.setContent("共计 5 题");
                    exercisesBean.setBackground(R.drawable.exercises_bg_1);
                    break;
                case 9:
                    exercisesBean.setTitle("第 10 章 高级编程");
                    exercisesBean.setContent("共计 5 题");
                    exercisesBean.setBackground(R.drawable.exercises_bg_2);
                    break;
                default:
                    break;
            }
            exercisesBeans.add(exercisesBean);
        }
    }

    public View getView() {
        if (view == null) {
            createView();
        }
        return view;
    }

    public void showView() {
        if (view == null) {
            createView();
        }
        view.setVisibility(View.VISIBLE);
    }

}