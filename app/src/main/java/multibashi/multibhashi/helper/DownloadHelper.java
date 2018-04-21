package multibashi.multibhashi.helper;

import multibashi.multibhashi.api.ApiInterface;
import multibashi.multibhashi.listeners.DownloadListener;
import multibashi.multibhashi.api.DownloadService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by emil on 20/4/18.
 */

public class DownloadHelper {

    private DownloadListener listener;

    public DownloadHelper(DownloadListener listener){

        this.listener = listener;
    }


    public void getAudioFile(String url, final String fileName){
        ApiInterface apiInterface = DownloadService.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.downloadFileWithDynamicUrlSync(url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                listener.onSuccess(response.body(), fileName);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.failure();
            }
        });
    }
}
