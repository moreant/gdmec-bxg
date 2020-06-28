package top.yeek.gdmec_boxuegu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.yeek.gdmec_boxuegu.R;
import top.yeek.gdmec_boxuegu.bean.ExercisesBean;
import top.yeek.gdmec_boxuegu.utils.AnalysisUtils;

/**
 * @author Moreant
 * @Date: 2020/06/28 17:40
 */
public class ExercisesDetailAdapter extends BaseAdapter {

    private final Context context;
    private List<ExercisesBean> ebl;
    private List<String> selectedPosition = new ArrayList<>();


    public ExercisesDetailAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return ebl == null ? 0 : ebl.size();
    }

    @Override
    public ExercisesBean getItem(int position) {
        return ebl == null ? null : ebl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            // item 视图
            convertView = LayoutInflater.from(context).inflate(R.layout.exercise_detail_list_item, null);
            ButterKnife.bind(this, convertView);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        ExercisesBean bean = getItem(position);
        if (bean != null) {
            vh.tvSubject.setText(bean.getSubject());
            vh.tvA.setText(bean.getA());
            vh.tvB.setText(bean.getB());
            vh.tvC.setText(bean.getC());
            vh.tvD.setText(bean.getD());
        }

        if (!selectedPosition.contains("" + position)) {
            vh.ivA.setImageResource(R.drawable.exercises_a);
            vh.ivB.setImageResource(R.drawable.exercises_b);
            vh.ivC.setImageResource(R.drawable.exercises_c);
            vh.ivD.setImageResource(R.drawable.exercises_d);
            AnalysisUtils.setABCDEnable(true,vh.ivA,vh.ivB,vh.ivC,vh.ivD);
        }

        return null;
    }

    static
    class ViewHolder {
        @BindView(R.id.tv_subject)
        TextView tvSubject;
        @BindView(R.id.iv_a)
        ImageView ivA;
        @BindView(R.id.tv_a)
        TextView tvA;
        @BindView(R.id.iv_b)
        ImageView ivB;
        @BindView(R.id.tv_b)
        TextView tvB;
        @BindView(R.id.iv_c)
        ImageView ivC;
        @BindView(R.id.tv_c)
        TextView tvC;
        @BindView(R.id.iv_d)
        ImageView ivD;
        @BindView(R.id.tv_d)
        TextView tvD;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
