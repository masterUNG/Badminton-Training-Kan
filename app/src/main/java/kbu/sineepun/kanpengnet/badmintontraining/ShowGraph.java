package kbu.sineepun.kanpengnet.badmintontraining;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class ShowGraph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_graph);

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM testTABLE", null);
        cursor.moveToFirst();
        int[] valueInts = new int[5];
        valueInts[0] = Integer.parseInt(cursor.getString(1));
        valueInts[1] = Integer.parseInt(cursor.getString(2));
        valueInts[2] = Integer.parseInt(cursor.getString(3));
        valueInts[3] = Integer.parseInt(cursor.getString(4));
        valueInts[4] = Integer.parseInt(cursor.getString(5));


        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(1, valueInts[0]),
                new DataPoint(2, valueInts[1]),
                new DataPoint(3, valueInts[2]),
                new DataPoint(4, valueInts[3]),
                new DataPoint(5, valueInts[4])
        });
        graph.addSeries(series);


    }
}
