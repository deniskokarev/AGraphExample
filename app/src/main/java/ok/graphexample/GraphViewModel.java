package ok.graphexample;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GraphViewModel extends ViewModel {
    private MutableLiveData<Integer> figure;

    public LiveData<Integer> getFigure() {
        if (figure == null) {
            figure = new MutableLiveData<>(MyDrawView.CIRCLE);
        }
        return figure;
    }

    public void setFigure(int f) {
        figure.postValue(f);
    }
}
