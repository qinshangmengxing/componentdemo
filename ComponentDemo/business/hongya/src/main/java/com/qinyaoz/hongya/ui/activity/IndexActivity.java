package com.qinyaoz.hongya.ui.activity;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qinyaoz.baselib.protocol.router.RouterConstants;
import com.qinyaoz.commonui.view.tablayout.TabLayout;
import com.qinyaoz.hongya.R;
import com.qinyaoz.hongya.R2;
import com.qinyaoz.hongya.ui.fragment.BlankFragment;
import com.qinyaoz.hongya.widget.My3DPageTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
@Route(path = RouterConstants.HongYa.INDEX)
public class IndexActivity extends AppCompatActivity {
    @BindView(R2.id.hy_tablayout)
    TabLayout tabLayout;

    @BindView(R2.id.hy_viewpager)
    ViewPager viewPager;

    int[] ids = new int[] {R.mipmap.guide0, R.mipmap.guide1, R.mipmap.guide2, R.mipmap.guide3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hy_activity_index);
        ButterKnife.bind(this);

        List<Fragment> fragments = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            BlankFragment fragment = new BlankFragment();
            fragment.setName("这是第" + i + "个fragment");
            fragment.setImgResId(ids[i % ids.length]);
            fragments.add(fragment);
        }

        if (fragments.size() > 5) {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        } else {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        }

        BlankFragmentPagerAdapter adapter = new BlankFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(false, new My3DPageTransformer());
        viewPager.setOffscreenPageLimit(2);

    }


    private static class BlankFragmentPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> data;

        public BlankFragmentPagerAdapter(FragmentManager fm, List<Fragment> d) {
            super(fm);
            data = d;
        }

        @Override
        public Fragment getItem(int i) {
            return data.get(i);
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return "标题" + (position + 1);
        }
    }
}
