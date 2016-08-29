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

public class DetailActivity extends AppCompatActivity {

    private static final String LOG_CAT = "DetailActivity";
    private String courseTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        courseTitle = getIntent().getStringExtra(MainActivity.COURSE_TITLE);
        TextView textView = (TextView) findViewById(R.id.appTitle);
        textView.setText(courseTitle);
    }

    public void resourceBtnOnClickHandler(View view) {
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
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
