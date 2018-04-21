package multibashi.multibhashi.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by emil on 20/4/18.
 */

public class LessonData implements Serializable{

    @SerializedName("type")
    private String type;
    @SerializedName("conceptName")
    private String conceptName;
    @SerializedName("pronunciation")
    private String pronunciation;
    @SerializedName("targetScript")
    private String targetScript;
    @SerializedName("audio_url")
    private String audioUrl;

    public String getType() {
        return type;
    }

    public String getConceptName() {
        return conceptName;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public String getTargetScript() {
        return targetScript;
    }

    public String getAudioUrl() {
        return audioUrl;
    }
}
