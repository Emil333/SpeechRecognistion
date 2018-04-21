package multibashi.multibhashi.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by emil on 20/4/18.
 */

public class LessonResponseModel {

    @SerializedName("lesson_data")
    private ArrayList<LessonData> lessonData;

    public ArrayList<LessonData> getLessonData() {
        return lessonData;
    }
}
