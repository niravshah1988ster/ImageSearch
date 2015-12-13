package zapata.com.imagesearching.DataFactory.Cloud;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.common.base.Joiner;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import zapata.com.imagesearching.Adapter.ImageAdapter;
import zapata.com.imagesearching.DataFactory.Cloud.RequestObjects.RequestSearchImage;
import zapata.com.imagesearching.DataFactory.SearchFactory;
import zapata.com.imagesearching.DataFactory.pojo.ImageResults;
import zapata.com.imagesearching.DataFactory.pojo.PageDetail;
import zapata.com.imagesearching.ImageSearchingApplication;

/**
 * Created by lenovo on 12/9/2015.
 */
public class ImageSearchServerFactory implements SearchFactory {

    private final String BASE_URL = "https://en.wikipedia.org/w/api.php?";

    private interface REQUEST_PARAMETER{
        String ACTION = "action";
        String PROP = "prop";
        String Format = "format";
        String PIPROP = "piprop";
        String PITHUMBSIZE = "pithumbsize";
        String PILIMIT = "pilimit";
        String GENERATOR = "generator";
        String GPSSEARCH = "gpssearch"; // This is used for query string
    }

    enum REQUEST_PARAM{
        ACTION("action" , "query"),
        PROP("prop","pageimages"),
        Format("format","json"),
        PIPROP("piprop","thumbnail"),
        PITHUMBSIZE("pithumbsize","50"),
        PILIMIT("pilimit","50"),
        GENERATOR("generator","prefixsearch"),
        GPSSEARCH("gpssearch","");

        public String key, value;
        REQUEST_PARAM(String k, String v){
            key = k;
            value = v;
        }
    }

    private String generateQueryParameter(Map<String, String> mapToConvert){

        String mapJoined = Joiner.on("&").withKeyValueSeparator("=")
                .join(mapToConvert);
        return mapJoined;
    }

    @Override
    public void SearchedResult(String queryText, final SEARCH_RESULTS search_results , final ERROR_RESULT error_result) {

        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put(REQUEST_PARAM.ACTION.key, REQUEST_PARAM.ACTION.value);
        queryParams.put(REQUEST_PARAM.PROP.key, REQUEST_PARAM.PROP.value);
        queryParams.put(REQUEST_PARAM.Format.key, REQUEST_PARAM.Format.value);
        queryParams.put(REQUEST_PARAM.PIPROP.key, REQUEST_PARAM.PIPROP.value);
        queryParams.put(REQUEST_PARAM.PITHUMBSIZE.key, REQUEST_PARAM.PITHUMBSIZE.value);
        queryParams.put(REQUEST_PARAM.PILIMIT.key, REQUEST_PARAM.PILIMIT.value);
        queryParams.put(REQUEST_PARAM.GENERATOR.key, REQUEST_PARAM.GENERATOR.value);
        queryParams.put(REQUEST_PARAM.GPSSEARCH.key, queryText);

        String url = BASE_URL + generateQueryParameter(queryParams);

        ResponseObject<ImageResults> responseObject = new ResponseObject<ImageResults>() {
            @Override
            public void result(String response, ImageResults convertToThisClass) {
                Gson gson = new Gson();
                ImageResults results = gson.fromJson(response.toString(), ImageResults.class);
                search_results.result(results);
            }

            @Override
            public void sendErrorMessage(String error) {
                error_result.showError(error);
            }
        };

        RequestSearchImage jsObjRequest = new RequestSearchImage(Request.Method.GET, url, null,responseObject);




        ImageSearchingApplication.getInstance().addToRequestQueueCancelOld(jsObjRequest);
    }








}
