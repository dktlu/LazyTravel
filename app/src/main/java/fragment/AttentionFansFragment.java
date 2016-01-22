package fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ld.hui.lazytravel.R;

import adapter.ViewPageFragmentAdapter;

public class AttentionFansFragment extends BaseViewPagerFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attention_fans, container, false);
    }

    @Override
    protected void onSetupTabAdapter(ViewPageFragmentAdapter adapter) {

    }

}
