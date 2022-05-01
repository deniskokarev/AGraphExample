package ok.graphexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.lang.invoke.MethodHandles;

import ok.MyDrawView;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MethodHandles.lookup().lookupClass().getName();

    MyDrawView myDrawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDrawView = findViewById(R.id.view_canvas);
    }

    public void onClick(View view) {
        int buttonId = view.getId();
        Log.d(TAG, String.format("button id = %d", buttonId));
        switch (buttonId) {
            case R.id.triangle_button:
                myDrawView.setFigure(MyDrawView.TRIANGLE);
                break;
            case R.id.square_button:
                myDrawView.setFigure(MyDrawView.SQUARE);
                break;
            case R.id.circle_button:
            default:
                myDrawView.setFigure(MyDrawView.CIRCLE);
        }
        myDrawView.invalidate();
    }
}