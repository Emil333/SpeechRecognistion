package multibashi.multibhashi.main.fragment;

import android.view.View;
import android.widget.TextView;

import okhttp3.ResponseBody;

/**
 * Created by emil on 21/4/18.
 */

public interface LessonFragmentContract {

    interface LessonFragmentPresenter{

        void matchText(String s);
        void setData();
        void playAudio();
        void recordAudioAndConvertToText();
    }

    interface LessonFragmentView{

        void getExtras();
        void setLearningText(String text);
        void setPronounciationText(String text);
        void setPlayButtonVisible();
        void setPlayButtonGone();
        void setMicroPhoneButtonVisible();
        void setMicroPhoneButtonGone();
        void saveFileToDisk(ResponseBody response, String s);
        void playAudioFile(String name);
        void promptSpeechInput();
        void showMatchPercentage(String s);
    }
}
