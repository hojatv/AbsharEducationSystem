package ir.abshareducaion.abshareducationsystem;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;

public class DetailActivity extends AppCompatActivity {

    private static final String LOG_CAT = "DetailActivity";
    private String courseTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        courseTitle = getIntent().getStringExtra(MainActivity.COURSE_TITLE);
        TextView textView = (TextView) findViewById(R.id.tvTitle);
        textView.setText(courseTitle);

        String courseDescription = getIntent().getStringExtra(MainActivity.COURSE_DESC);
        TextView descTextView = (TextView) findViewById(R.id.tvDescription);
        descTextView.setText(courseDescription);

        int courseNumber = getIntent().getIntExtra(MainActivity.COURSE_NUMBER,0);
        TextView courseNumberView = (TextView) findViewById(R.id.tvCourseNumber);
        //courseNumberView.setText("courseNumber #: " + courseNumber);
        courseNumberView.setText("لطفا شکیبا باشید");

        double courseCredits = getIntent().getDoubleExtra(MainActivity.COURSE_CREDITS,0);
        TextView courseCreditsView = (TextView) findViewById(R.id.tvCredits);
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMinimumFractionDigits(1);
        //courseCreditsView.setText("Credits: " + numberFormat.format(courseCredits));
        courseCreditsView.setText("");


        ImageView iv = (ImageView)findViewById(R.id.imageCourse);
        int res = getResources().getIdentifier("image_"+courseNumber,"drawable",getPackageName());
        iv.setImageResource(res);
    }

   /* public void resourceBtnOnClickHandler(View view) {
        String imageName = "books";
        int res = getResources().getIdentifier(imageName,"drawable", getPackageName());
        ImageView iv = (ImageView) findViewById(R.id.imageCourse);
        iv.setImageResource(res);
    }

    public void assetBtnOnClickHandler(View view) {
        String asset1Id = "asset1.jpg";
        ImageView iv = (ImageView) findViewById(R.id.imageCourse);

        try {
            InputStream ip = getAssets().open(asset1Id );
            Drawable drawable = Drawable.createFromStream(ip,null);
            iv.setImageDrawable(drawable);
        } catch (IOException e) {
            Log.e( LOG_CAT , e.getMessage());
        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu)  ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_settings){
            return true;
        }
        else if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void registerBtnClickHandler(View view) {
        getIntent().putExtra("resultMessage" ,"You're registered for" + courseTitle);
        setResult(RESULT_OK,getIntent());
        finish();
    }
}
