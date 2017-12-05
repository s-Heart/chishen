package demo.com.chishen.gonglue;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import demo.com.chishen.R;

/**
 * Author: shishaojie
 * Date: 2017/12/1 0001 11:50
 * Description:
 */
public class GonglueActivity extends Activity {

    private ViewPager viewPager;
    private PagerAdapter gonglueAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gonglue);
        viewPager = findViewById(R.id.vp_gonglue);
        gonglueAdapter = new GonglueAdapter(this);
        viewPager.setAdapter(gonglueAdapter);
    }
}
