package multibashi.multibhashi.main.fragment;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import multibashi.multibhashi.Constants;
import multibashi.multibhashi.R;
import multibashi.multibhashi.Utility;
import multibashi.multibhashi.models.LessonData;
import okhttp3.ResponseBody;

import static android.app.Activity.RESULT_OK;

/**
 * Created by emil on 20/4/18.
 */

public class LessonFragment extends Fragment implements LessonFragmentContract.LessonFragmentView, View.OnClickListener {

    @BindView(R.id.learning_text)
    TextView learningTxt;
    @BindView(R.id.translated_text)
    TextView translatedTxt;
    @BindView(R.id.play_button)
    AppCompatImageView playButton;
    @BindView(R.id.microphone_button)
    AppCompatImageView microPhoneButton;
    @BindView(R.id.user_narrated_text)
    TextView userNarratedText;

    private LessonFragmentPresenterImpl mPresenter;
    private static final int REQ_CODE_SPEECH_INPUT = 101;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lesson, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getExtras();
        playButton.setOnClickListener(this);
        microPhoneButton.setOnClickListener(this);
    }

    @Override
    public void getExtras() {

        Bundle bundle = getArguments();
        LessonData data = (LessonData) bundle.getSerializable(Constants.LESSON_DATA_ARGUMENT_KEY);
        mPresenter = new LessonFragmentPresenterImpl(this, data);
        mPresenter.setData();
    }


    @Override
    public void setLearningText(String text) {
        learningTxt.setText(text);
    }

    @Override
    public void setPronounciationText(String text) {
        translatedTxt.setText(text);
    }

    @Override
    public void setPlayButtonVisible() {
        playButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPlayButtonGone() {
        playButton.setVisibility(View.GONE);
    }

    @Override
    public void setMicroPhoneButtonVisible() {
        microPhoneButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void setMicroPhoneButtonGone() {
        microPhoneButton.setVisibility(View.GONE);
    }

    @Override
    public void saveFileToDisk(ResponseBody response, String fileName) {
        Utility.writeResponseBodyToDisk(response, fileName, getContext());
    }

    @Override
    public void playAudioFile(String name) {
        setPlayButtonGone();
        playAudio(getContext(), name);
    }

    @Override
    public void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "kn-IN");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getContext(), getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showMatchPercentage(String s) {
        userNarratedText.setText(s);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mPresenter.matchText(result.get(0));
                }
                break;
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play_button:
                mPresenter.playAudio();
                break;
            case R.id.microphone_button:
                mPresenter.recordAudioAndConvertToText();
                break;

        }
    }

    public void playAudio(Context context, String fileName) {
        MediaPlayer mp = new MediaPlayer();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                playButton.setVisibility(View.VISIBLE);
            }
        });
        try {
            mp.setDataSource(context.getExternalFilesDir(null) + File.separator + fileName);
            mp.prepare();
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
