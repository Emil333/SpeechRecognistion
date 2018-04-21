package multibashi.multibhashi.main.fragment;

import android.text.TextUtils;

import multibashi.multibhashi.Constants;
import multibashi.multibhashi.Utility;
import multibashi.multibhashi.helper.DownloadHelper;
import multibashi.multibhashi.listeners.DownloadListener;
import multibashi.multibhashi.models.LessonData;
import okhttp3.ResponseBody;

/**
 * Created by emil on 21/4/18.
 */

public class LessonFragmentPresenterImpl implements LessonFragmentContract.LessonFragmentPresenter, DownloadListener{


    private final LessonFragmentContract.LessonFragmentView mView;
    private final LessonData data;
    private DownloadHelper helper;
    private String fileName;


    public LessonFragmentPresenterImpl(LessonFragmentContract.LessonFragmentView mView, LessonData data) {

        this.mView = mView;
        this.data = data;
    }

    @Override
    public void setData() {

        if (data.getConceptName() != null){
            mView.setLearningText(data.getConceptName());
        }
        if (data.getPronunciation() != null){
            mView.setPronounciationText(data.getTargetScript());
        }
        if (data.getType().equals(Constants.TYPE_LESSON)){
            mView.setPlayButtonVisible();
            mView.setMicroPhoneButtonGone();
        }else{
            mView.setPlayButtonGone();
            mView.setMicroPhoneButtonVisible();
        }
        String url = data.getAudioUrl();
        int length = url.length();
        fileName = url.substring(75, length);
        helper = new DownloadHelper(this);
        helper.getAudioFile(data.getAudioUrl(), fileName);
    }


    @Override
    public void onSuccess(ResponseBody response, String fileName) {

        mView.saveFileToDisk(response, fileName);
    }

    @Override
    public void failure() {

    }
    @Override
    public void playAudio() {

        if (fileName != null && !TextUtils.isEmpty(fileName)){
            mView.playAudioFile(fileName);
        }
    }

    @Override
    public void recordAudioAndConvertToText() {

        mView.promptSpeechInput();
    }

    @Override
    public void matchText(String s) {

        int numMatch = Utility.lockMatch(s, data.getTargetScript());
        mView.showMatchPercentage(numMatch + "% Matched");
    }
}
