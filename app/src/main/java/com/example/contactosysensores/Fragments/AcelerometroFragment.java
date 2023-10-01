package com.example.contactosysensores.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.contactosysensores.R;
import com.example.contactosysensores.databinding.FragmentAcelerometroBinding;

public class AcelerometroFragment extends Fragment {

    FragmentAcelerometroBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAcelerometroBinding.inflate(inflater, container, false);
        NavController navController = NavHostFragment.findNavController(AcelerometroFragment.this);
        Button button = getActivity().findViewById(R.id.buttonIr);
        button.setOnClickListener(view -> {
            navController.navigate(R.id.action_acelerometroFragment_to_magnetometroFragment);
        });
        if (navController.getCurrentDestination().getId() == R.id.acelerometroFragment){
            button.setText("Ir a magnetómetro");
        }

        getActivity().findViewById(R.id.buttonAnadir).setOnClickListener(view -> {
            Log.d("test-msg", "b");
        });
        return binding.getRoot();
    }


}