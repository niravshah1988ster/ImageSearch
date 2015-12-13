package zapata.com.imagesearching;

import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Collection;
import java.util.List;

import zapata.com.imagesearching.Adapter.ImageAdapter;
import zapata.com.imagesearching.DataFactory.ImageSearchFactory;
import zapata.com.imagesearching.DataFactory.SearchFactory;
import zapata.com.imagesearching.DataFactory.pojo.ImageResults;
import zapata.com.imagesearching.DataFactory.pojo.PageDetail;

public class ImageSearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    GridView gridView;
    SearchView searchView;
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_search);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        gridView = (GridView) findViewById(R.id.gridView);
        searchView = (SearchView) findViewById(R.id.searchView);

        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        ImageSearchingApplication.logMessage("onQueryTextChange : "+newText, null);
        searchQuery(newText);
        return false;
    }

    private void searchQuery(String query){
        ImageSearchFactory.getFactory(ImageSearchFactory.FactoryType.ServerFactory).SearchedResult(query, new SearchFactory.SEARCH_RESULTS() {
            @Override
            public void result(ImageResults imageResults) {
                try {
                    Collection<PageDetail> list = imageResults.query.pages.values();
                    gridView.setAdapter(new ImageAdapter(ImageSearchActivity.this, list));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new SearchFactory.ERROR_RESULT() {
            @Override
            public void showError(String message) {
                showErrorInView(message);
            }
        });
    }


    private void showErrorInView(String message){
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, message, Snackbar.LENGTH_INDEFINITE)
                .setAction("Re-search", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });

        // Changing message text color
        snackbar.setActionTextColor(Color.RED);

        // Changing action button text color
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);

        snackbar.show();
    }

}
