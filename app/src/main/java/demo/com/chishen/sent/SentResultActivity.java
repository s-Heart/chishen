package demo.com.chishen.sent;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.util.ArrayList;

import demo.com.chishen.R;

@Deprecated
public class SentResultActivity extends Activity {
    private RadarChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent_result);
        initChart();
        setData();

    }

    private void initChart() {
        mChart = findViewById(R.id.chart1);
        mChart.setBackgroundColor(Color.rgb(60, 65, 82));
//        mChart.setBackgroundColor(0x00000000);
        mChart.getDescription().setEnabled(false);

        mChart.setWebLineWidth(1f);
        mChart.setWebColor(Color.WHITE);
        mChart.setWebLineWidthInner(1f);
        mChart.setWebColorInner(Color.LTGRAY);
        mChart.setWebAlpha(100);

//        mChart.setScaleX(1.3f);
//        mChart.setScaleY(1.3f);

        /*动画*/
        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        mChart.setRotationEnabled(false);


        XAxis xAxis = mChart.getXAxis();
        xAxis.setTextSize(12f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            private String[] mActivities = new String[]{"完整度", "流利度", "准确度"};

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mActivities[(int) value % mActivities.length];
            }
        });
        xAxis.setTextColor(Color.GREEN);

        YAxis yAxis = mChart.getYAxis();
        yAxis.setLabelCount(0 + 1, true);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(100f);
        yAxis.setDrawLabels(false);

        //生成图形的描述,让其不可见l.setEnabled(false)
        mChart.getLegend().setEnabled(false);

    }

    private void setData() {
        float mult = 80;
        float min = 20;
        int cnt = 3;

        ArrayList<RadarEntry> entries1 = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < cnt; i++) {
            float val1 = (float) (Math.random() * mult) + min;
            entries1.add(new RadarEntry(100f));
        }

        RadarDataSet set1 = new RadarDataSet(entries1, "Last Week");
        set1.setColor(Color.rgb(103, 110, 129));
        set1.setFillColor(Color.rgb(103, 110, 129));
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightCircleEnabled(false);
        set1.setDrawHighlightIndicators(false);


        ArrayList<IRadarDataSet> sets = new ArrayList<>();
        sets.add(set1);

        RadarData data = new RadarData(sets);
        data.setValueTextSize(6f);
        data.setDrawValues(true);
        data.setValueTextColor(Color.WHITE);

        mChart.setData(data);
        mChart.invalidate();

    }
}
