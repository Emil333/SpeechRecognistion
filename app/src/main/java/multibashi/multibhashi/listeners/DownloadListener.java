package multibashi.multibhashi.listeners;

import okhttp3.ResponseBody;

/**
 * Created by emil on 20/4/18.
 */

public interface DownloadListener {

    void onSuccess(ResponseBody response, String fileName);
    void failure();
}
