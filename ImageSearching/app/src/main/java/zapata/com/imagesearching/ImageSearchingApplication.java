package zapata.com.imagesearching;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by lenovo on 12/9/2015.
 */
public class ImageSearchingApplication  extends Application {

    public static final String TAG = ImageSearchingApplication.class.getSimpleName();
    private RequestQueue mRequestQueue;

    private static ImageSearchingApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized ImageSearchingApplication getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueueCancelOld(Request<T> req) {
        cancelPendingRequests(TAG);
        req.setTag(TAG);
        getRequestQueue().add(req);
    }
    
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public static void logMessage(String message , String tag){
        Log.d(TextUtils.isEmpty(tag) ? TAG : tag, message);
    }

}
