package com.example.node06.lathianretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<StackOverflowQuestions> {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        requestWindowFeature(Window.FEATURE_PROGRESS);

        ArrayAdapter<Question> arrayAdapter = new ArrayAdapter<Question>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                new ArrayList<Question>()
        );
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(arrayAdapter);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.stackexchange.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        StackOverflowAPI stackOverflowAPI = retrofit.create(StackOverflowAPI.class);
        Call<StackOverflowQuestions> call = stackOverflowAPI.loadQuestions("android");

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<StackOverflowQuestions> call, Response<StackOverflowQuestions> response) {
        
    }

    @Override
    public void onFailure(Call<StackOverflowQuestions> call, Throwable t) {

    }
}
