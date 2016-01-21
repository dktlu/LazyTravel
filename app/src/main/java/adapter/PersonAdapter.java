package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ld.hui.lazytravel.R;

import java.util.List;

import model.PersonInfo;

/**
 * Created by tao on 2015/12/15.
 */
public class PersonAdapter extends BaseRecyclerAdapter<BaseRecyclerAdapter.BaseRecyclerViewHolder, PersonInfo> {

    public PersonAdapter(List<PersonInfo> mDataList) {
        super(mDataList);
    }

    @Override
    public BaseRecyclerViewHolder createViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        BaseRecyclerViewHolder viewHolder = null;
        View view = inflater.inflate(R.layout.item_people, null);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position, PersonInfo data) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvAge.setText(data.getAge());
        viewHolder.tvLevel.setText("Lv" + data.getLevel());
        viewHolder.tvName.setText(data.getNickName());
        if ("男".equals(data.getSex())) {
            viewHolder.ivGender.setBackgroundResource(R.drawable.icon_boy);
        } else {
            viewHolder.ivGender.setBackgroundResource(R.drawable.icon_girl);
        }
    }

    class ViewHolder extends BaseRecyclerViewHolder {

        TextView tvName;
        TextView tvAge;
        TextView tvLevel;
        ImageView ivGender;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvAge = (TextView) itemView.findViewById(R.id.tv_age);
            tvLevel = (TextView) itemView.findViewById(R.id.tv_level);
            ivGender = (ImageView) itemView.findViewById(R.id.iv_gender);
        }
    }
}
