package base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.ld.hui.lazytravel.R;


/**
 * 自定义dialog基类
 * Created by tao on 2015/12/14.
 */
public abstract class BaseDialog extends Dialog {

    private boolean alignBottom = false;    //是否位于底部
    private int margin = 15;                   //设置与边缘的间距
    private boolean isCorner = true;            //是否设置圆角边框
    private int mDialogColor = Color.WHITE;     //dialog的背景颜色

    public BaseDialog(Context context) {
        this(context, false);
    }

    public BaseDialog(Context context, boolean alignBottom) {
        super(context, R.style.BaseDialog);
        this.alignBottom = alignBottom;
        if (alignBottom) {
            WindowManager.LayoutParams layoutParams = this.getWindow().getAttributes();
            layoutParams.gravity = Gravity.BOTTOM | Gravity.LEFT;
            layoutParams.x = 5;
            layoutParams.y = 5;
            onWindowAttributesChanged(layoutParams);
            getWindow().setWindowAnimations(R.style.DialogAnim);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout layout = new FrameLayout(getContext());
        View view = contentView();
        if (alignBottom) {
            view.setMinimumWidth(getContext().getResources().getDisplayMetrics().widthPixels - 10);
        }
        layout.addView(view);
        /**
         *给它设置边距，不让它左右两边占满屏幕；如果是底部弹出来的话，就是占满整个屏幕
         */
        if (view instanceof ViewGroup) {
            if (!alignBottom) {
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) view.getLayoutParams();
                int intendMargin = dipToPx(getContext(), margin);
                lp.leftMargin = intendMargin;
                lp.rightMargin = intendMargin;
            }
            view.setBackground(getDialogDrawable());
        }
        setContentView(layout);
    }

    /**
     * 创建一个背景
     */
    private Drawable getDialogDrawable() {
        GradientDrawable mGradinentDrawble = new GradientDrawable();
        mGradinentDrawble.setDither(true);
        mGradinentDrawble.setShape(GradientDrawable.RECTANGLE);
        mGradinentDrawble.setColor(mDialogColor);
        if (isCorner) {
            mGradinentDrawble.setCornerRadius(dipToPx(getContext(), 10));
        }
        return mGradinentDrawble;
    }

    /**
     * 设置是否是圆角
     *
     * @return
     */
    public void setIsCorner(boolean isCorner) {
        this.isCorner = isCorner;
    }

    /**
     * @param margin 设置间距
     * @return
     **/
    public void setMargin(int margin) {
        this.margin = margin;
    }

    /**
     * @param mDialogColor 设置dialog的背景颜色
     */
    public void setmDialogColor(int mDialogColor) {
        this.mDialogColor = mDialogColor;
    }

    /**
     * 根据手机的分辨率从dp单位转成为px
     *
     * @param appContext
     * @param dpValue
     * @return
     */
    private int dipToPx(Context appContext, float dpValue) {
        return (int) (dpValue * appContext.getResources().getDisplayMetrics().density + 0.5f);
    }

    protected abstract View contentView();

}
