package multibashi.multibhashi.main.activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.ArrayList;

import multibashi.multibhashi.Constants;
import multibashi.multibhashi.listeners.NetworkListener;
import multibashi.multibhashi.helper.NetworkHelper;
import multibashi.multibhashi.main.fragment.LessonFragment;
import multibashi.multibhashi.models.LessonData;
import multibashi.multibhashi.models.LessonResponseModel;

/**
 * Created by emil on 20/4/18.
 */

public class MainPresenterImpl implements MainContract.MainPresenter, NetworkListener{


    private MainContract.MainContractView mainView;
    private NetworkHelper helper;
    private ArrayList<LessonData> lessonList;
    private int currentPage = 0;

    public MainPresenterImpl(MainContract.MainContractView mainView) {
        this.mainView = mainView;
    }

    public void requestData() {

        helper = new NetworkHelper(this);
        helper.getLessons();

    }

    @Override
    public void onSuccess(LessonResponseModel responseModel) {
        lessonList = new ArrayList<>();
        lessonList.addAll(responseModel.getLessonData());
        processList();
    }

    private void processList() {

        ArrayList<Fragment> fragmentList = new ArrayList<>();
        for (LessonData data : lessonList){
            LessonFragment fragment = new LessonFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constants.LESSON_DATA_ARGUMENT_KEY, data);
            fragment.setArguments(bundle);
            fragmentList.add(fragment);
        }
        mainView.setAdapter(fragmentList);
    }

    @Override
    public void failure() {
    }

    @Override
    public void setNextPage() {
        currentPage = currentPage + 1;
        mainView.naviagetToNextLesson(currentPage);
    }
}
