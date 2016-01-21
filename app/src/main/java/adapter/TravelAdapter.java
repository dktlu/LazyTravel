package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ld.hui.lazytravel.R;

import java.util.List;

import model.TravelInfo;

/**
 * Created by tao on 2015/12/14.
 */
public class TravelAdapter extends BaseRecyclerAdapter<BaseRecyclerAdapter.BaseRecyclerViewHolder, TravelInfo> {

    public TravelAdapter(List<TravelInfo> mDataList) {
        super(mDataList);
    }

    @Override
    public BaseRecyclerViewHolder createViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        BaseRecyclerAdapter.BaseRecyclerViewHolder holder = null;
        View view = inflater.inflate(R.layout.item_travel, null);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position, TravelInfo data) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvName.setText(data.getTravelerName());
        viewHolder.tvAge.setText(data.getAge());
        viewHolder.tvIntroduce.setText(data.getContent());
        viewHolder.tvLikeCount.setText(data.getLikeNum());
        viewHolder.tvCommentCount.setText(data.getCommentNum());
        if ("男".equals(data.getSex())) {
            viewHolder.ivGender.setBackgroundResource(R.drawable.icon_boy);
        } else {
            viewHolder.ivGender.setBackgroundResource(R.drawable.icon_girl);
        }
    }

    class ViewHolder extends BaseRecyclerViewHolder {

        TextView tvName;
        TextView tvAge;
        TextView tvIntroduce;
        TextView tvLikeCount;
        TextView tvCommentCount;
        ImageView ivGender;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvAge = (TextView) itemView.findViewById(R.id.tv_age);
            tvIntroduce = (TextView) itemView.findViewById(R.id.tv_introduce);
            tvLikeCount = (TextView) itemView.findViewById(R.id.tv_like_count);
            tvCommentCount = (TextView) itemView.findViewById(R.id.tv_comment_count);
            ivGender = (ImageView) itemView.findViewById(R.id.iv_gender);
        }
    }
}
