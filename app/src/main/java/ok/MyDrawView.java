package ok;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.ColorInt;

import ok.graphexample.R;

public class MyDrawView extends View {
    public final static int CIRCLE = 0;
    public final static int TRIANGLE = 1;
    public final static int SQUARE = 2;

    private Paint brush;
    private float figures[][];
    private int figure;

    public MyDrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.MyDrawView,
                0, 0);
        brush = new Paint();
        try {
            @ColorInt int color = a.getInteger(R.styleable.MyDrawView_color, Color.WHITE);
            brush.setColor(color);
        } finally {
            a.recycle();
        }
        brush.setStrokeWidth(3);
        figure = CIRCLE;
    }

    private float[] makePoly(int n, double angle, int radius, int cx, int cy) {
        float points[] = new float[4 * n];
        double sector = 2 * Math.PI / n;
        int x1 = cx + (int) (radius * Math.cos(angle));
        int y1 = cy - (int) (radius * Math.sin(angle));
        for (int i = 0; i < n; i++) {
            angle += sector;
            int x2 = cx + (int) (radius * Math.cos(angle));
            int y2 = cy - (int) (radius * Math.sin(angle));
            points[4 * i] = x1;
            points[4 * i + 1] = y1;
            points[4 * i + 2] = x2;
            points[4 * i + 3] = y2;
            x1 = x2;
            y1 = y2;
        }
        return points;
    }

    @Override
    public void onSizeChanged(int w, int h, int oldWidth, int oldHeight) {
        super.onSizeChanged(w, h, oldWidth, oldHeight);
        int diameter = Math.min(w, h);
        int radius = diameter / 2;
        int cx = w / 2;
        int cy = h / 2;
        figures = new float[][]{
                makePoly(360, 0, radius, cx, cy),
                makePoly(3, -Math.PI / 6, radius, cx, cy),
                makePoly(4, Math.PI / 4, radius, cx, cy)
        };
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLines(figures[figure], brush);
    }

    public void setFigure(int n) {
        figure = n;
        invalidate();
    }
}
