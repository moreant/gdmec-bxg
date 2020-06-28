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
    private OnSelectListener onSelectListener;

    public ExercisesDetailAdapter(Context context, List<ExercisesBean> ebl) {
        this.context = context;
        this.ebl = ebl;
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
            AnalysisUtils.setABCDEnable(true, vh.ivA, vh.ivB, vh.ivC, vh.ivD);
        } else {
            AnalysisUtils.setABCDEnable(false, vh.ivA, vh.ivB, vh.ivC, vh.ivD);
            switch (bean.getSelect()) {
                // 选项是正确的
                case 0:
                    if (bean.getAnswer() == 1) {
                        vh.ivA.setImageResource(R.drawable.exercises_right_icon);
                        vh.ivB.setImageResource(R.drawable.exercises_b);
                        vh.ivC.setImageResource(R.drawable.exercises_c);
                        vh.ivD.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 2) {
                        vh.ivA.setImageResource(R.drawable.exercises_a);
                        vh.ivB.setImageResource(R.drawable.exercises_right_icon);
                        vh.ivC.setImageResource(R.drawable.exercises_c);
                        vh.ivD.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 3) {
                        vh.ivA.setImageResource(R.drawable.exercises_a);
                        vh.ivB.setImageResource(R.drawable.exercises_b);
                        vh.ivC.setImageResource(R.drawable.exercises_right_icon);
                        vh.ivD.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 4) {
                        vh.ivA.setImageResource(R.drawable.exercises_a);
                        vh.ivB.setImageResource(R.drawable.exercises_b);
                        vh.ivC.setImageResource(R.drawable.exercises_c);
                        vh.ivD.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                // 选 A ，错了
                case 1:
                    vh.ivA.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.getAnswer() == 2) {
                        vh.ivB.setImageResource(R.drawable.exercises_right_icon);
                        vh.ivC.setImageResource(R.drawable.exercises_c);
                        vh.ivD.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 3) {
                        vh.ivB.setImageResource(R.drawable.exercises_b);
                        vh.ivC.setImageResource(R.drawable.exercises_right_icon);
                        vh.ivD.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 4) {
                        vh.ivB.setImageResource(R.drawable.exercises_b);
                        vh.ivC.setImageResource(R.drawable.exercises_c);
                        vh.ivD.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 2:
                    vh.ivB.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.getAnswer() == 1) {
                        vh.ivA.setImageResource(R.drawable.exercises_right_icon);
                        vh.ivC.setImageResource(R.drawable.exercises_c);
                        vh.ivD.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 3) {
                        vh.ivA.setImageResource(R.drawable.exercises_a);
                        vh.ivC.setImageResource(R.drawable.exercises_right_icon);
                        vh.ivD.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 4) {
                        vh.ivA.setImageResource(R.drawable.exercises_a);
                        vh.ivC.setImageResource(R.drawable.exercises_c);
                        vh.ivD.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 3:
                    vh.ivC.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.getAnswer() == 1) {
                        vh.ivA.setImageResource(R.drawable.exercises_right_icon);
                        vh.ivB.setImageResource(R.drawable.exercises_b);
                        vh.ivD.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 2) {
                        vh.ivA.setImageResource(R.drawable.exercises_a);
                        vh.ivB.setImageResource(R.drawable.exercises_right_icon);
                        vh.ivD.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 4) {
                        vh.ivA.setImageResource(R.drawable.exercises_a);
                        vh.ivB.setImageResource(R.drawable.exercises_b);
                        vh.ivD.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 4:
                    vh.ivD.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.getAnswer() == 1) {
                        vh.ivA.setImageResource(R.drawable.exercises_right_icon);
                        vh.ivB.setImageResource(R.drawable.exercises_b);
                        vh.ivC.setImageResource(R.drawable.exercises_c);
                    } else if (bean.getAnswer() == 2) {
                        vh.ivA.setImageResource(R.drawable.exercises_a);
                        vh.ivB.setImageResource(R.drawable.exercises_right_icon);
                        vh.ivC.setImageResource(R.drawable.exercises_c);
                    } else if (bean.getAnswer() == 3) {
                        vh.ivA.setImageResource(R.drawable.exercises_a);
                        vh.ivB.setImageResource(R.drawable.exercises_b);
                        vh.ivC.setImageResource(R.drawable.exercises_right_icon);
                    }
                default:
                    break;
            }

            vh.ivA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedPosition.contains("" + position)) {

                    }else{
                        selectedPosition.add("" + position);
                    }
                    onSelectListener.onSelectA(position, vh.ivA, vh.ivB, vh.ivC, vh.ivD);
                }
            });

            vh.ivB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedPosition.contains("" + position)) {

                    }else{
                        selectedPosition.add("" + position);
                    }
                    onSelectListener.onSelectB(position, vh.ivA, vh.ivB, vh.ivC, vh.ivD);
                }
            });

            vh.ivC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedPosition.contains("" + position)) {

                    }else{
                        selectedPosition.add("" + position);
                    }
                    onSelectListener.onSelectC(position, vh.ivA, vh.ivB, vh.ivC, vh.ivD);
                }
            });

            vh.ivD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedPosition.contains("" + position)) {

                    }else{
                        selectedPosition.add("" + position);
                    }
                    onSelectListener.onSelectD(position, vh.ivA, vh.ivB, vh.ivC, vh.ivD);
                }
            });
        }

        return convertView;
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

    public interface OnSelectListener{
        void onSelectA(int position,ImageView ivA,ImageView ivB,ImageView ivC,ImageView ivD);
        void onSelectB(int position,ImageView ivA,ImageView ivB,ImageView ivC,ImageView ivD);
        void onSelectC(int position,ImageView ivA,ImageView ivB,ImageView ivC,ImageView ivD);
        void onSelectD(int position,ImageView ivA,ImageView ivB,ImageView ivC,ImageView ivD);
    }
}
