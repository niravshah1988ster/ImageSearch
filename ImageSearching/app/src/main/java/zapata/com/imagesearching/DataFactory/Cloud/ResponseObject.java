package zapata.com.imagesearching.DataFactory.Cloud;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import zapata.com.imagesearching.DataFactory.pojo.ImageResults;
import zapata.com.imagesearching.ImageSearchingApplication;

/**
 * Created by lenovo on 12/13/2015.
 */
public abstract class ResponseObject<T> implements Response.Listener<JSONObject>, Response.ErrorListener {


    T responseClass;

    @Override
    public void onResponse(JSONObject response) {
        ImageSearchingApplication.logMessage("Response => " + response.toString(), null);
        result(response.toString() , responseClass);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        sendErrorMessage(error.toString());
    }

    /**
     * Convert response to convertToThisClass using Gson
     * @param response
     * @param convertToThisClass
     */
    public abstract void result(String response, T convertToThisClass);
    public abstract void sendErrorMessage(String error);
}
