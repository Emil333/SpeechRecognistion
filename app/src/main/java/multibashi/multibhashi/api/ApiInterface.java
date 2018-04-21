package multibashi.multibhashi.api;

import multibashi.multibhashi.models.LessonResponseModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by emil on 20/4/18.
 */

public interface ApiInterface {

    @GET("getLessonData.php")
    Call<LessonResponseModel> getLessons();

    @GET
    Call<ResponseBody> downloadFileWithDynamicUrlSync(@Url String fileUrl);
}
