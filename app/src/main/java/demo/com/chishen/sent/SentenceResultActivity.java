package demo.com.chishen.sent;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import demo.com.chishen.R;

public class SentenceResultActivity extends Activity {

    private RadarView radarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence_result);
        radarView = findViewById(R.id.radarView);
        radarView.setData(new float[]{55, 55, 100});
        radarView.setResult("99");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                radarView.setData(new float[]{99, 45, 87});
                radarView.setResult("60");
            }
        }, 1400);

    }
}
