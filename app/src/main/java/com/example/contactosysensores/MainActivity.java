package com.example.contactosysensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.example.contactosysensores.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        binding.buttonIngresar.setOnClickListener(view -> {
            if (verificarInternet()){
                Intent intent = new Intent(MainActivity.this, AppActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Verifique su conexión a internet", Toast.LENGTH_SHORT).show();
            }
        });
        setContentView(binding.getRoot());
    }

    public boolean verificarInternet() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        boolean tieneInternet = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        return tieneInternet;
    }
}