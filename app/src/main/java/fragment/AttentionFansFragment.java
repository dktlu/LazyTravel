package fragment;

import android.os.Bundle;

import com.ld.hui.lazytravel.R;

import adapter.ViewPageFragmentAdapter;
import base.BaseViewPagerFragment;

/**
 * 关注、粉丝viewpager页面
 */
public class AttentionFansFragment extends BaseViewPagerFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSetupTabAdapter(ViewPageFragmentAdapter adapter) {
        String[] title = getResources().getStringArray(R.array.friends_viewpage_arrays);
    }

}
