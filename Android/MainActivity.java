package com.example.ardac.aar;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.toolbox.HttpResponse;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;


public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this ,android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        new Connection().execute();
    }



    class Connection extends AsyncTask <String, String, String>{


        @Override
        protected String doInBackground(String... params) {
            String result = "";
            String host="http://10.0.2.2/test2/test22.php";
            try{
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(host));
                org.apache.http.HttpResponse response = client.execute(request);
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                StringBuffer stringBuffer = new StringBuffer("");
                String line="";
                while((line=reader.readLine()) != null){
                    stringBuffer.append(line);
                    break;
                }
                reader.close();
                result = stringBuffer.toString();
            }
            catch (Exception e){
                return new String("There exception: "+e.getMessage());
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result){ //Json datası burada oarse edilecek.
            try {
                JSONObject jsonResult = new JSONObject(result);
                int success = jsonResult.getInt("success");
                if (success == 1){
                    JSONArray test22 = jsonResult.getJSONArray("test22");
                    for (int i = 0; i < test22.length(); i++) {
                        JSONObject haber = test22.getJSONObject(i);
                        int haberid = haber.getInt("haberid");
                        String haberturu = haber.getString("haberturu");
                        String haberbasligi = haber.getString("haberbasligi");
                        String habericerigi = haber.getString("habericerigi");
                        int yayinlanmatarihi = haber.getInt("yayinlanmatarihi");
                        String line = haberid + "-" + haberturu + "-" + haberbasligi + "-" + habericerigi + "-" + yayinlanmatarihi ;
                        adapter.add(line);
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Haber Yok",Toast.LENGTH_SHORT).show();

                }

            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

    }
}
