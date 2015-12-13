package zapata.com.imagesearching.DataFactory.Database;

import zapata.com.imagesearching.DataFactory.SearchFactory;

/**
 * Created by lenovo on 12/9/2015.
 */
public class ImageSearchDatabaseFactory implements SearchFactory {
    @Override
    public void SearchedResult(String queryText, SEARCH_RESULTS imageResult , ERROR_RESULT error_result) {
        throw new RuntimeException("Not implemented yet");
    }
}
