package ir.abshareducaion.abshareducationsystem;

import android.os.AsyncTask;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.CommonsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.text.NumberFormat;
import java.util.Arrays;

import ir.abshareducaion.abshareducationsystem.domain.Deployment;
import ir.abshareducaion.abshareducationsystem.domain.Greeting;

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

        int courseNumber = getIntent().getIntExtra(MainActivity.COURSE_NUMBER, 0);
        TextView courseNumberView = (TextView) findViewById(R.id.tvCourseNumber);
        //courseNumberView.setText("courseNumber #: " + courseNumber);
        courseNumberView.setText("لطفا شکیبا باشید");

        double courseCredits = getIntent().getDoubleExtra(MainActivity.COURSE_CREDITS, 0);
        TextView courseCreditsView = (TextView) findViewById(R.id.tvCredits);
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMinimumFractionDigits(1);
        //courseCreditsView.setText("Credits: " + numberFormat.format(courseCredits));
        courseCreditsView.setText("");


        ImageView iv = (ImageView) findViewById(R.id.imageCourse);
        int res = getResources().getIdentifier("image_" + courseNumber, "drawable", getPackageName());
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        } else if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void registerBtnClickHandler(View view) {
        setResult(RESULT_OK, getIntent());
        new HttpRequestTask().execute();

    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Deployment> {
        @Override
        protected Deployment doInBackground(Void... params) {
            try {
                final String url = "http://10.0.2.2:8080/activiti-rest/service/repository/deployments/23";

                HttpAuthentication authHeader = new HttpBasicAuthentication("kermit", "kermit");
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.setAuthorization(authHeader);
                requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

                // Create a new RestTemplate instance
                RestTemplate restTemplate = new RestTemplate();


                // Add the String message converter
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                try {
                    // Make the HTTP GET request to the Basic Auth protected URL
                    ResponseEntity<Deployment> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Deployment.class);
                    Log.e("",response.getBody().toString());
                    getIntent().putExtra("resultMessage", "Deployment: " + response.getBody().toString());
                    return response.getBody();
                } catch (HttpClientErrorException e) {
                    Log.e("", e.getLocalizedMessage(), e);
                    // Handle 401 Unauthorized response
                }

            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Deployment deployment) {

            /*TextView greetingIdText = (TextView) findViewById(R.id.id_value);
            TextView greetingContentText = (TextView) findViewById(R.id.content_value);
            greetingIdText.setText(greeting.getId());
            greetingContentText.setText(greeting.getContent());*/
            getIntent().putExtra("resultMessage", "Greeting : " + deployment.toString());
            setResult(RESULT_OK, getIntent());
            // new HttpRequestTask().execute();
            finish();
        }

    }
}
