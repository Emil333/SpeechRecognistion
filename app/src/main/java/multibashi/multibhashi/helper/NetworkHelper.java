package multibashi.multibhashi.helper;

import multibashi.multibhashi.api.ApiInterface;
import multibashi.multibhashi.api.ApiService;
import multibashi.multibhashi.listeners.NetworkListener;
import multibashi.multibhashi.models.LessonResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by emil on 20/4/18.
 */

public class NetworkHelper {

    private NetworkListener listener;

    public NetworkHelper(NetworkListener listener){

        this.listener = listener;
    }

    public void getLessons(){
        ApiInterface apiInterface = ApiService.getClient().create(ApiInterface.class);
        Call<LessonResponseModel> lessons = apiInterface.getLessons();
        lessons.enqueue(new Callback<LessonResponseModel>() {
            @Override
            public void onResponse(Call<LessonResponseModel> call, Response<LessonResponseModel> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<LessonResponseModel> call, Throwable t) {
                listener.failure();
            }
        });
    }


}
