package top.yeek.gdmec_boxuegu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.yeek.gdmec_boxuegu.R;
import top.yeek.gdmec_boxuegu.bean.ExercisesBean;

/**
 * @author Moreant
 * @Date: 2020/06/11 21:00
 */
public class ExerciseAdapter extends BaseAdapter {

    private final Context context;
    private List<ExercisesBean> exercisesBeans;

    public ExerciseAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ExercisesBean> exercisesBeans) {
        this.exercisesBeans = exercisesBeans;
        // 数据更新时提醒 view 更新
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return exercisesBeans == null ? 0 : exercisesBeans.size();
    }

    // 获取List里的具体某项
    @Override
    public ExercisesBean getItem(int position) {
        return exercisesBeans == null ? null : exercisesBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 视图持有者（保存控件）
        final ViewHolder vh;

        if (convertView == null) {
            // item 视图
            convertView = LayoutInflater.from(context).inflate(R.layout.exercises_list_item, null);
            ButterKnife.bind(this, convertView);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        // 设置 item 内容
        ExercisesBean exercisesBean = getItem(position);
        if (exercisesBean != null) {
            vh.tvOrder.setText(String.valueOf(position + 1));
            vh.tvTitle.setText(exercisesBean.getTitle());
            vh.tvContent.setText(exercisesBean.getContent());
            vh.tvOrder.setBackgroundResource(exercisesBean.getBackground());
        }else{
            //下节课
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (exercisesBean != null) {
                    Toast.makeText(context, "跳转到习题", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return convertView;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_order)
        TextView tvOrder;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_content)
        TextView tvContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
