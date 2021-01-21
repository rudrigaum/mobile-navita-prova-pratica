package com.navita.themoviedbapi;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.navita.themoviedbapi.api.TMDBService;
import com.navita.themoviedbapi.model.TMDB;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button botaoRecuperar;
    private TextView textoResultado;
    private Retrofit retrofit;
    private List<TMDB> listaTMDB = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoRecuperar = findViewById(R.id.buttonRecuperar);
        textoResultado = findViewById(R.id.textResultado);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        botaoRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               recuperarListaTMDB();

            }
        });

    }

    private void recuperarListaTMDB(){
        TMDBService service = retrofit.create(TMDBService.class);
        Call<List<TMDB>> call = service.recuperarLista();

        call.enqueue(new Callback<List<TMDB>>() {
            @Override
            public void onResponse(Call<List<TMDB>> call, Response<List<TMDB>> response) {
               if(response.isSuccessful()){
                    listaTMDB = response.body();

                    for(int i=0; i<listaTMDB.size(); i++){
                        TMDB tmdb = listaTMDB.get(i);
                        textoResultado.setText(tmdb.getId() + " - " + tmdb.getOriginal_title() );
                    }

               }
            }

            @Override
            public void onFailure(Call<List<TMDB>> call, Throwable t) {

            }
        });
    }




}
