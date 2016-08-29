package ir.abshareducaion.abshareducationsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1001;
    public static final String COURSE_TITLE = "courseTitle";;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClickHandler(View view) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        /*Course course = new Course();*/

        detailIntent.putExtra(COURSE_TITLE, "CourseTitle");
        startActivityForResult(detailIntent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK){
                String msg = data.getStringExtra("resultMessage");
                Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
            }
        }
    }
}
