package com.example.pokedex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pokedex.models.Pokemon;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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

}
