package demo.com.chishen.gonglue;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import demo.com.chishen.R;

/**
 * Author: shishaojie
 * Date: 2017/12/1 0001 14:12
 * Description:
 */
class GonglueAdapter extends PagerAdapter {
    private Context context;

    public GonglueAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = null;
        switch (position) {
            case 0:
                v = LayoutInflater.from(context).inflate(R.layout.gonglue_first, container, false);
                break;
            case 1:
                v = LayoutInflater.from(context).inflate(R.layout.gonglue_second, container, false);
                break;
            case 2:
                v = LayoutInflater.from(context).inflate(R.layout.gonglue_third, container, false);
                break;
        }
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
