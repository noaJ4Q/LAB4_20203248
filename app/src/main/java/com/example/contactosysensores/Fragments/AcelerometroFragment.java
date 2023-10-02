package com.example.contactosysensores.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.contactosysensores.Objetos.Contacto;
import com.example.contactosysensores.Objetos.Root;
import com.example.contactosysensores.R;
import com.example.contactosysensores.RecyclerView.ContactoAdapter;
import com.example.contactosysensores.Services.ContactoService;
import com.example.contactosysensores.databinding.FragmentAcelerometroBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AcelerometroFragment extends Fragment {
    List<Contacto> acelerometroContactos = new ArrayList<>();
    FragmentAcelerometroBinding binding;
    ContactoService contactoService;
    ContactoAdapter contactoAdapter = new ContactoAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAcelerometroBinding.inflate(inflater, container, false);
        NavController navController = NavHostFragment.findNavController(AcelerometroFragment.this);
        Button buttonIr = getActivity().findViewById(R.id.buttonIr);
        buttonIr.setOnClickListener(view -> {
            navController.navigate(R.id.action_acelerometroFragment_to_magnetometroFragment);
        });
        if (navController.getCurrentDestination().getId() == R.id.acelerometroFragment){
            buttonIr.setText("Ir a magnetómetro");
        }
        crearRetrofit();
        Button buttonAnadir = getActivity().findViewById(R.id.buttonAnadir);
        buttonAnadir.setOnClickListener(view -> {
            buttonAnadir.setEnabled(false);
            buttonIr.setEnabled(false);
            cargarContacto();
        });

        contactoAdapter.setContext(getContext());
        contactoAdapter.setContactoList(acelerometroContactos);

        binding.rvAcelerometro.setAdapter(contactoAdapter);
        binding.rvAcelerometro.setLayoutManager(new LinearLayoutManager(getActivity()));

        return binding.getRoot();
    }

    void crearRetrofit(){
        contactoService = new Retrofit.Builder()
                .baseUrl("https://randomuser.me")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ContactoService.class);
    }

    void cargarContacto(){
        contactoService.getRoot().enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.isSuccessful()) {
                    Root root = response.body();
                    Contacto contacto = root.getResults().get(0);
                    acelerometroContactos.add(contacto);
                    contactoAdapter.notifyDataSetChanged();

                    getActivity().findViewById(R.id.buttonIr).setEnabled(true);
                    getActivity().findViewById(R.id.buttonAnadir).setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {

            }
        });
    }
}