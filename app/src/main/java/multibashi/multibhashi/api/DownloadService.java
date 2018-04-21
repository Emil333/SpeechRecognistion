package multibashi.multibhashi.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by emil on 20/4/18.
 */

public class DownloadService {

    private static Retrofit retrofit = null;
    private static final String DOWNLOAD_BASE_URL = "https://s3.ap-south-1.amazonaws.com/multibhashi-data/audio/english2kannada/";


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(DOWNLOAD_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
