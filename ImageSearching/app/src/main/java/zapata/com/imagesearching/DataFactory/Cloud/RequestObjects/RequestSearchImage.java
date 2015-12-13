package zapata.com.imagesearching.DataFactory.Cloud.RequestObjects;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import zapata.com.imagesearching.DataFactory.Cloud.ResponseObject;
import zapata.com.imagesearching.DataFactory.pojo.ImageResults;

/**
 * Created by lenovo on 12/13/2015.
 */
public class RequestSearchImage extends JsonObjectRequest {

    public RequestSearchImage(int method, String url, JSONObject jsonRequest, ResponseObject<ImageResults> response) {
        super(method, url, jsonRequest, response, response);
    }

}
