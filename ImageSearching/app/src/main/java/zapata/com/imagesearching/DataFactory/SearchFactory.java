package zapata.com.imagesearching.DataFactory;

import zapata.com.imagesearching.DataFactory.pojo.ImageResults;

/**
 * Created by lenovo on 12/9/2015.
 */
public interface SearchFactory {

    public void SearchedResult(String queryText, SEARCH_RESULTS imageResult, ERROR_RESULT error_result);

    public interface SEARCH_RESULTS{
        public void result(ImageResults imageResults);
    }

    public interface ERROR_RESULT{
        public void showError(String message);
    }

}
