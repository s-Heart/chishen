package demo.com.chishen.sent;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/**
 * Author: shishaojie
 * Date: 2017/12/4 0004 18:30
 * Description:自定义雷达图
 */
public class RadarView extends View {

    private float radius;//圆半径
    private float angle = (float) (Math.PI * 2 / 3);//维度间的角度
    private float centerX; // 中心X
    private float centerY; // 中心Y
    private float[] stopXs = new float[3];//顶点坐标x
    private float[] stopYs = new float[3];//顶点坐标y
    private float maxValue = 100; // 数据最大值

    private String[] titles = {"完整度", "准确度", "流利度"};
    private float[] data;
    private String result;
    private int width;//屏幕宽
    private int height;//屏幕高


    public RadarView(Context context) {
        super(context);
        init();
    }

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RadarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        radius = Math.min(height, width) / 2 * 0.8f;
        centerX = width / 2;
        centerY = height / 2;
        postInvalidate();
        super.onSizeChanged(width, height, oldWidth, oldHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawCircle(canvas);
        drawLines(canvas);
        drawText(canvas);
        drawRegion(canvas);
        drawResult(canvas);
    }

    /**
     * 绘制圆
     *
     * @param canvas
     */
    private void drawCircle(Canvas canvas) {
        //雷达区画笔
        Paint mainPaint = new Paint();
        mainPaint.setAntiAlias(true);
        mainPaint.setStrokeWidth(1f);
        mainPaint.setStyle(Paint.Style.STROKE);
//        mainPaint.setColor(Color.parseColor("#b3f0e8"));
        mainPaint.setColor(Color.DKGRAY);
        canvas.drawCircle(centerX, centerY, radius, mainPaint);
    }

    /**
     * 绘制虚线
     *
     * @param canvas
     */
    private void drawLines(Canvas canvas) {

        for (int i = 0; i < 3; i++) {
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(1f);
//            paint.setColor(Color.parseColor("#b3f0e8"));
            paint.setColor(Color.DKGRAY);
            float stopX = (float) (centerX + radius * Math.sin(angle * i));
            float stopY = (float) (centerY + radius * (-Math.cos(angle * i)));
            stopXs[i] = stopX;
            stopYs[i] = stopY;
            Path path = new Path();
            path.moveTo(centerX, centerY);
            path.lineTo(stopX, stopY);
            PathEffect effects = new DashPathEffect(new float[]{5, 5, 5, 5}, 1);
            paint.setPathEffect(effects);
            canvas.drawPath(path, paint);
        }

        for (int i = 0; i < stopXs.length; i++) {
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.DKGRAY);
            paint.setStrokeWidth(1f);
            Path path = new Path();
            path.moveTo(stopXs[i], stopYs[i]);
            path.lineTo(stopXs[(i + 1) % stopXs.length], stopYs[(i + 1) % stopYs.length]);
            PathEffect effects = new DashPathEffect(new float[]{5, 5, 5, 5}, 1);
            paint.setPathEffect(effects);
            canvas.drawPath(path, paint);
        }

    }

    /**
     * 绘制文字
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        // 文本画笔
        Paint textPaint = new Paint();
        textPaint.setTextSize((float) (0.036 * width));
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.DKGRAY);
//        textPaint.setColor(Color.parseColor("#b3f0e8"));
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        for (int i = 0; i < stopXs.length; i++) {
            float dis = textPaint.measureText(titles[i]);// 文本长度
            if (i == 0) {//完整度
                canvas.drawText(titles[i], stopXs[i] - dis / 2, stopYs[i] - 15, textPaint);
            } else if (i == 1) {//准确度
                canvas.drawText(titles[i], stopXs[i] + 15, stopYs[i] + fontHeight / 2, textPaint);
            } else {
                canvas.drawText(titles[i], stopXs[i] - 15 - dis, stopYs[i] + fontHeight / 2, textPaint);
            }
        }
    }

    /**
     * 绘制区域
     *
     * @param canvas
     */
    private void drawRegion(Canvas canvas) {
        if (data == null) {
            return;
        }
        //数据区画笔
        Paint valuePaint = new Paint();
        valuePaint.setAntiAlias(true);
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        valuePaint.setColor(Color.DKGRAY);
//        valuePaint.setColor(Color.parseColor("#b3f0e8"));
        Path path = new Path();
        valuePaint.setAlpha(255);
        for (int i = 0; i < data.length; i++) {
            double percent = data[i] / maxValue;
            float x = (float) (centerX + radius * Math.sin(angle * i) * percent);
            float y = (float) (centerY + radius * (-Math.cos(angle * i) * percent));
            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }
        valuePaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, valuePaint);
        valuePaint.setAlpha(127);
        // 绘制填充区域
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, valuePaint);
    }

    /**
     * 绘制结果
     *
     * @param canvas
     */
    private void drawResult(Canvas canvas) {
        if (result == null) {
            return;
        }
        Paint paint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize((float) (0.036 * width) * 2);
        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(Color.parseColor("#20ad9a"));
        paint.setColor(Color.DKGRAY);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        float dis = paint.measureText(result);// 文本长度
        canvas.drawText(result, centerX - dis / 2, centerY + fontHeight / 3, paint);
    }

    public void setData(float[] data) {
        this.data = data;
        invalidate();
    }

    public void setResult(String result) {
        this.result = result;
        invalidate();
    }

}
