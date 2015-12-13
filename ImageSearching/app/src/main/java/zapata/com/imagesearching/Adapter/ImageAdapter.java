package zapata.com.imagesearching.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Collection;

import zapata.com.imagesearching.DataFactory.pojo.PageDetail;
import zapata.com.imagesearching.R;

/**
 * Created by lenovo on 12/9/2015.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private PageDetail[] mSource;
    // Constructor
    public ImageAdapter(Context c, Collection<PageDetail> list){
        mContext = c;
        mSource = new PageDetail[list.size()];
        list.toArray(mSource);
    }

    public int getCount() {
        return mSource.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        TextView textView;

        if (convertView == null) {

            convertView = View.inflate(mContext, R.layout.template_image_search_result, null);


        }

        imageView = (ImageView) convertView.findViewById(R.id.imageView);
        textView = (TextView) convertView.findViewById(R.id.textView);

        Glide.with(mContext)
                .load(mSource[position].thumbnail != null ? mSource[position].thumbnail.source : null)
                .placeholder(R.drawable.default_pic)
                .into(imageView);

        textView.setText(mSource[position].title);

        return convertView;
    }


}
