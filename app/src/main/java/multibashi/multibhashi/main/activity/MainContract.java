package multibashi.multibhashi.main.activity;

import android.support.v4.app.Fragment;

import java.util.ArrayList;

/**
 * Created by emil on 20/4/18.
 */

public interface MainContract {

    interface MainContractView{

        void setAdapter(ArrayList<Fragment> fragmentList);

        void naviagetToNextLesson(int currentPage);
    }

    interface MainPresenter{

        void setNextPage();
    }
}
