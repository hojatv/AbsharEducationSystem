package ir.abshareducaion.abshareducationsystem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

import ir.abshareducaion.abshareducationsystem.domain.Course;
import ir.abshareducaion.abshareducationsystem.domain.DataProvider;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1001;
    public static final String COURSE_TITLE = "courseTitle";;
    public static final String COURSE_DESC = "courseDesc";;
    public static final String COURSE_NUMBER = "courseNumber";;
    public static final String COURSE_CREDITS = "courseCredits ";

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
        ArrayAdapter<Course> courseArrayAdapter = new CourseArrayAdapter(this,0, data);
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
        intent.putExtra(COURSE_NUMBER,course.getCourseNumber());
        intent.putExtra(COURSE_CREDITS,course.getCredits());
        startActivityForResult(intent,REQUEST_CODE);
    }

   /* public void btnClickHandler(View view) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        Course course = new Course();

        detailIntent.putExtra(COURSE_TITLE, "CourseTitle");
        startActivityForResult(detailIntent, REQUEST_CODE);
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK){
                String msg = data.getStringExtra("resultMessage");
                Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
            }
        }
    }
    class CourseArrayAdapter extends ArrayAdapter<Course> {
        Context context;
        List<Course> objects;
        public CourseArrayAdapter(Context context, int resource, List<Course> objects) {
            super(context, resource, objects);
            this.context = context;
            this.objects = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Course course = objects.get(position);
            LayoutInflater inflater  = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.course_item,null);
            TextView textView = (TextView) view.findViewById(R.id.tvTitle);
            textView.setText(course.getTitle());
            ImageView imageView = (ImageView) view.findViewById(R.id.imageCourse);
            int res = context.getResources().getIdentifier("image_"+course.getCourseNumber(),"drawable",context.getPackageName());
            imageView.setImageResource(res);
            return view;
        }
    }
}
