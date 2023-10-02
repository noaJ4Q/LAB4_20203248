package com.example.contactosysensores.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.contactosysensores.Objetos.Contacto;
import com.example.contactosysensores.Objetos.Root;
import com.example.contactosysensores.R;
import com.example.contactosysensores.RecyclerView.ContactoAdapter;
import com.example.contactosysensores.Services.ContactoService;
import com.example.contactosysensores.databinding.FragmentMagnetometroBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MagnetometroFragment extends Fragment {
    List<Contacto> magnetometroContactos = new ArrayList<>();
    ContactoService contactoService;
    FragmentMagnetometroBinding binding;
    ContactoAdapter contactoAdapter = new ContactoAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMagnetometroBinding.inflate(inflater, container, false);

        // nav
        NavController navController = NavHostFragment.findNavController(MagnetometroFragment.this);
        Button buttonIr = getActivity().findViewById(R.id.buttonIr);
        buttonIr.setOnClickListener(view -> {
            navController.navigate(R.id.action_magnetometroFragment_to_acelerometroFragment);
        });
        if (navController.getCurrentDestination().getId() == R.id.magnetometroFragment) {
            buttonIr.setText("Ir a acelerómetro");
        }

        // api
        crearRetrofit();
        Button buttonAnadir = getActivity().findViewById(R.id.buttonAnadir);
        buttonAnadir.setOnClickListener(view -> {
            buttonIr.setEnabled(false);
            buttonAnadir.setEnabled(false);
            cargarContacto();
        });

        contactoAdapter.setContext(getContext());
        contactoAdapter.setContactoList(magnetometroContactos);
        contactoAdapter.setOnClickListener(position -> {
            magnetometroContactos.remove(position);
            contactoAdapter.notifyItemRemoved(position);
        });

        binding.rvMagnetometro.setAdapter(contactoAdapter);
        binding.rvMagnetometro.setLayoutManager(new LinearLayoutManager(getActivity()));

        return binding.getRoot();
    }

    void crearRetrofit(){
        contactoService = new Retrofit.Builder()
                .baseUrl("https://randomuser.me")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ContactoService.class);
    }

    void cargarContacto() {
        contactoService.getRoot().enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.isSuccessful()) {
                    Root root = response.body();
                    Contacto contacto = root.getResults().get(0);
                    magnetometroContactos.add(contacto);
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