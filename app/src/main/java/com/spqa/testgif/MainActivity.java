package com.spqa.testgif;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    Button btn;

    GifImageView gifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gifImageView = (GifImageView) findViewById(R.id.gifView);

        // because we implement OnClickListener we only have to pass "this"
        // (much easier)


        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    String sampleString = "https://upload.wikimedia.org/wikipedia/commons/2/2c/Rotating_earth_%28large%29.gif";
                    URL url = new URL(sampleString);
                    URLConnection ucon = url.openConnection();
                    InputStream is = ucon.getInputStream();

                    byte[] rawGifBytes = IOUtils.toByteArray(is);

                    GifDrawable gifFromBytes = new GifDrawable(rawGifBytes);
                    gifImageView.setImageDrawable(gifFromBytes);

                } catch (Exception e) {
//                    Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();

                }
            }
        });

        thread.start();


    }
}
//    public void onClick(View view) {
//        // detect the view that was "clicked"
//        switch (view.getId()) {
//            case R.id.button1:
//                Toast.makeText(this, "button clicked", Toast.LENGTH_SHORT).show();
//                new LongOperation().execute("");
//                break;
//        }
//    }
//
//    private class LongOperation extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            for (int i = 0; i < 5; i++) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    Thread.interrupted();
//                }
//            }
//            return "aaaaaa";
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            TextView txt = (TextView) findViewById(R.id.output);
//            txt.setText(result); // txt.setText(result);
//            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
//            // might want to change "executed" for the returned string passed
//            // into onPostExecute() but that is upto you
//        }
//
//        @Override
//        protected void onPreExecute() {}
//
//        @Override
//        protected void onProgressUpdate(Void... values) {}
//    }
