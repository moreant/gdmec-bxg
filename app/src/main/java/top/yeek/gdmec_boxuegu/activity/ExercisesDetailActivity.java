package top.yeek.gdmec_boxuegu.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import top.yeek.gdmec_boxuegu.R;

public class ExercisesDetailActivity extends AppCompatActivity {

    private int id;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_detail);
        id = getIntent().getIntExtra("id", 0);
        title = getIntent().getStringExtra("title");
    }
}
