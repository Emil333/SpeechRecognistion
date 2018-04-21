package multibashi.multibhashi.listeners;

import java.util.ArrayList;

import multibashi.multibhashi.models.LessonResponseModel;

/**
 * Created by emil on 20/4/18.
 */

public interface NetworkListener {

    void onSuccess(LessonResponseModel responseModel);
    void failure();
}
