package demo.com.chishen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import demo.com.chishen.chooseexc.ChooseExcActivity;
import demo.com.chishen.gonglue.GonglueActivity;
import demo.com.chishen.sent.SentenceResultActivity;


public class MainActivity extends Activity {

    private LinearLayout rlMain;
    private View guide1View;
    private View guide2View;
    private View guide3View;

    private Button btnGonglue;
    private Button btnChooseExc;
    private Button btnSentResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initGuide();

        jumpGonglue();

        jumpChooseExc();

        jumpSentResult();
    }

    private void jumpSentResult() {
        btnSentResult = findViewById(R.id.btn_sent_result);
        btnSentResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SentenceResultActivity.class);
                startActivity(intent);
            }
        });
    }

    private void jumpChooseExc() {
        btnChooseExc = findViewById(R.id.btn_choose_exc);
        btnChooseExc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooseExcActivity.class);
                startActivity(intent);
            }
        });
    }

    private void jumpGonglue() {
        btnGonglue = findViewById(R.id.btn_gonglue);
        btnGonglue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GonglueActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initGuide() {
        rlMain = findViewById(R.id.rl_main);
        guide1View = LayoutInflater.from(this).inflate(R.layout.danci_guide_first, rlMain, false);
        guide2View = LayoutInflater.from(this).inflate(R.layout.danci_guide_second, rlMain, false);
        guide3View = LayoutInflater.from(this).inflate(R.layout.danci_guide_third, rlMain, false);
        rlMain.addView(guide1View);
        guide1View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rlMain.removeView(guide1View);
                guide1View = null;
                rlMain.addView(guide2View);
            }
        });
        guide2View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rlMain.removeView(guide2View);
                guide2View = null;
                rlMain.addView(guide3View);
            }
        });
        guide3View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rlMain.removeView(guide3View);
                guide3View = null;
                Toast.makeText(getApplicationContext(), "引导完成", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
