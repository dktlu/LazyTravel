package adapter;

import android.content.Context;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.ld.hui.lazytravel.R;

import java.util.List;

import base.BaseApplication;
import model.BookTravelInfo;
import model.PersonInfo;
import utils.Util;

/**
 * Created by tao on 2015/12/15.
 */
public class BookTravelAdapter extends BaseRecyclerAdapter<BaseRecyclerAdapter.BaseRecyclerViewHolder, BookTravelInfo> {

    private Context context;

    public BookTravelAdapter(List<BookTravelInfo> mDataList, Context context) {
        super(mDataList);
        this.context = context;
    }

    @Override
    public BaseRecyclerViewHolder createViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        BaseRecyclerViewHolder viewHolder = null;
        View view = inflater.inflate(R.layout.item_book_travel, null);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position, BookTravelInfo data) {
        ViewHolder viewHolder = (ViewHolder) holder;
        UrlImageViewHelper.setUrlDrawable(viewHolder.ivHeadPhoto, data.getUserHeadImg(),
                R.drawable.default_avatar);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point size = new Point();
        wm.getDefaultDisplay().getSize(size);
        int width = size.x;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                width, width / 2);
        params.setMargins(Util.dip2px(context, 10), Util.dip2px(context, 10),
                Util.dip2px(context, 10), 0);
        viewHolder.ivPic.setLayoutParams(params);
        UrlImageViewHelper.setUrlDrawable(viewHolder.ivPic, data.getHeadImage());
        viewHolder.tvTravelTitle.setText(data.getTitle());
        viewHolder.tvUserName.setText(data.getUserName());
        viewHolder.tvViewCount.setText(data.getViewCount() + "浏览");
    }

    class ViewHolder extends BaseRecyclerViewHolder {

        TextView tvTravelTitle;
        TextView tvUserName;
        TextView tvViewCount;
        ImageView ivPic;
        ImageView ivHeadPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTravelTitle = (TextView) itemView.findViewById(R.id.tv_travel_title);
            tvUserName = (TextView) itemView.findViewById(R.id.tv_user_name);
            tvViewCount = (TextView) itemView.findViewById(R.id.tv_view_count);
            ivPic = (ImageView) itemView.findViewById(R.id.iv_pic);
            ivHeadPhoto = (ImageView) itemView.findViewById(R.id.iv_head_photo);
        }
    }
}
