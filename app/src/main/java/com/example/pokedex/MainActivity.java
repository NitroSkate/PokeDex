package com.example.pokedex;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.pokedex.models.Pokemon;
import com.example.pokedex.utils.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final Object Tag = "Prueba";
    ArrayList<Pokemon> listaDatos = new ArrayList<>();
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecy();

        for(int i = 0; i < 5; i++){
            Pokemon pok = new Pokemon("Pokemon: " +i);
            listaDatos.add(i, pok);
        }
    }

    public void initRecy(){


        recycler = findViewById(R.id.recycler_id);
        recycler.setLayoutManager(new LinearLayoutManager(this));



        PokemonAdapter adapter = new PokemonAdapter(listaDatos);
        recycler.setAdapter(adapter);


    }

    private class FetchPokemonTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... pokemonNumbers) {

            if (pokemonNumbers.length == 0) {
                return null;
            }

            String ID = pokemonNumbers[0];

            URL pokeAPI = NetworkUtils.buildUrl(ID);

            try {
                String result = NetworkUtils.getResponseFromHttpUrl(pokeAPI);
                return result;
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }

        @Override
        protected void onPostExecute(String pokemonInfo) {
            if (pokemonInfo != null || !pokemonInfo.equals("")) {
                mResultText.setText(pokemonInfo);
                Log.d((String) Tag, pokemonInfo);
            } else {
                mResultText.setText("Lo que sea");
            }
        }
    }

}
