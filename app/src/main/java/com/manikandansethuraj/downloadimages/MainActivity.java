package com.manikandansethuraj.downloadimages;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private EditText editText;
    private ListView listView;
    private String[] listOfImages;
    private ProgressBar progressBar;
    private LinearLayout loadingSection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        editText = (EditText) findViewById(R.id.downLoadURL);
        listView = (ListView) findViewById(R.id.urllist);
        listView.setOnItemClickListener(this);
        listOfImages = getResources().getStringArray(R.array.imageUrls);
        progressBar = (ProgressBar)findViewById(R.id.downloadProgress);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void downLoadImage(View view) {
       Thread myThread = new Thread(new DownloadImageThread());
        myThread.start();
    }

    public boolean downloadImageUsingThreads(String url){

//        1. create the url object that represents the url
//        2. open connection using the url object
//        3. read data using input stream into a byte array
//        4. open a file output stream to save data on sd card
//        5. write data to the fileoutputstream
//        6. close the connection

        boolean successful = false;
        URL downloadURL=null;
        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {
            downloadURL = new URL(url);
            connection = (HttpURLConnection)downloadURL.openConnection();
            inputStream = connection.getInputStream();
            int read=-1;
            byte[] buffer = new byte[2048];
            while ((read=inputStream.read(buffer))!=-1){
                L.m(""+read);

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (connection!=null)
            {
                connection.disconnect();
            }
            if (inputStream!=null)
            {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    return successful;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
     editText.setText(listOfImages[position]);
    }

    private class DownloadImageThread implements Runnable{

        @Override
        public void run() {
            downloadImageUsingThreads(listOfImages[0]);
        }
    }
}
