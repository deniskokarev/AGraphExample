package ok.graphexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.view.View;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private final static HashMap<Integer, Integer> buttonToFigure = new HashMap<Integer, Integer>() {{
        put(R.id.circle_button, MyDrawView.CIRCLE);
        put(R.id.triangle_button, MyDrawView.TRIANGLE);
        put(R.id.square_button, MyDrawView.SQUARE);
    }};

    MyDrawView myDrawView;
    GraphViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDrawView = findViewById(R.id.view_canvas);
        model = new ViewModelProvider(this).get(GraphViewModel.class);
        model.getFigure().observe(this, f -> myDrawView.setFigure(f));
    }

    // otherwise AS gives pointless unboxing may be null warning
    @SuppressWarnings("ConstantConditions")
    public void onClick(View view) {
        int buttonId = view.getId();
        model.setFigure(buttonToFigure.getOrDefault(buttonId, MyDrawView.CIRCLE));
        myDrawView.invalidate();
    }
}