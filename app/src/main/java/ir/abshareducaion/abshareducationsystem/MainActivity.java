package ir.abshareducaion.abshareducationsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1001;
    public static final String COURSE_TITLE = "courseTitle";;
    public static final String COURSE_DESC = "courseDesc";;
    private static final int DETAIL_REQUEST_CODE = 100;
    protected List<Course> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = DataProvider.getData();
        Iterator<Course> iterator = data.iterator();
        StringBuilder builder = new StringBuilder();
        while (iterator.hasNext()){
            Course course = iterator.next();
            builder.append(course.getTitle()).append("\n");
        }
        ArrayAdapter<Course> courseArrayAdapter = new ArrayAdapter<Course>(this,android.R.layout.simple_expandable_list_item_1, data);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(courseArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Course course = data.get(position);
                displayDetail(course);


            }
        });
    }

    private void displayDetail(Course course) {
        /*Log.d("MainActivity","Displaying Course:" + course.getTitle());*/
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(COURSE_TITLE,course.getTitle());
        intent.putExtra(COURSE_DESC,course.getDescription());
        startActivityForResult(intent,DETAIL_REQUEST_CODE);
    }

    /*public void btnClickHandler(View view) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        Course course = new Course();

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
    }*/
}
