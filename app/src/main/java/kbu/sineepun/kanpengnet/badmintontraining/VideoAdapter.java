package kbu.sineepun.kanpengnet.badmintontraining;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Admin on 2/8/2016.
 */
public class VideoAdapter extends BaseAdapter{

    //explicit
    private Context context;
    private String[]iconStrings, titleStrings, detailStrings;

    public VideoAdapter(Context context,
                        String[] iconStrings,
                        String[] titleStrings,
                        String[] detailStrings) {
        this.context = context;
        this.iconStrings = iconStrings;
        this.titleStrings = titleStrings;
        this.detailStrings = detailStrings;
    }

    @Override
    public int getCount() {
        return iconStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.video_listview, viewGroup, false);

        //bind widget
        ImageView imageView = (ImageView) view1.findViewById(R.id.imageView11);
        TextView title = (TextView) view1.findViewById(R.id.textView16);
        TextView detailTextView = (TextView) view1.findViewById(R.id.textView17);

    //show View
        Picasso.with(context).load(iconStrings[i]).resize(100, 100).into(imageView);
        title.setText(titleStrings[i]);
        detailTextView.setText(detailStrings[i]);

        return view1;
    }
}//main class
