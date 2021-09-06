package com.example.streamnow;

import static android.content.ContentValues.TAG;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRVDog;
    Adapter mAdapter;
    URL url=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new AsyncLogin().execute();
    }

    private class AsyncLogin extends AsyncTask<String, String, List<String>> {
        ProgressDialog pdLoading = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected List<String> doInBackground(String... params) {
            List<String> reponses = new ArrayList<>();
            StringBuilder content = new StringBuilder();
            try {
                url = new URL("https://api.thedogapi.com/v1/images/search");

            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

            reponses.add(content.toString());

        return reponses;

        }

        @Override
        protected void onPostExecute(List<String> result) {

            pdLoading.dismiss();
            List<Data> data=new ArrayList<>();


            pdLoading.dismiss();
            try {
                  for(int i=0;i<result.size();i++){
                      JSONObject baseJsonResponse = new JSONObject(result.get(i));
                      String image = baseJsonResponse.getString("url");
                       data.add(new Data(image));
                }
                mRVDog = findViewById(R.id.List);
                mAdapter = new Adapter(getApplicationContext(), data);
                LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
                mRVDog.setLayoutManager(manager);
                mRVDog.setHasFixedSize(true);
                mAdapter = new Adapter(MainActivity.this, data);
                mRVDog.setAdapter(mAdapter);

            } catch (JSONException e) {
                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }
}