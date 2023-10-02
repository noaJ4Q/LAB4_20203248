package com.example.contactosysensores.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactosysensores.Objetos.Contacto;
import com.example.contactosysensores.Objetos.Root;
import com.example.contactosysensores.R;

import java.net.URL;
import java.util.List;

public class ContactoAdapter extends RecyclerView.Adapter<ContactoAdapter.RootViewHolder>{
    private List<Contacto> contactoList;
    private Context context;
    private OnItemClickListener listener;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnClickListener(OnItemClickListener clickListener){
        listener = clickListener;
    }

    @NonNull
    @Override
    public RootViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_contacto, parent, false);
        return new RootViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RootViewHolder holder, int position) {
        Contacto contacto = contactoList.get(position);
        ImageView imagen = holder.itemView.findViewById(R.id.contactoPicture);
        TextView nombre = holder.itemView.findViewById(R.id.contactoName);
        TextView genero = holder.itemView.findViewById(R.id.contactoGender);
        TextView ciudad = holder.itemView.findViewById(R.id.contactoCity);
        TextView pais = holder.itemView.findViewById(R.id.contactoCountry);
        TextView email = holder.itemView.findViewById(R.id.contactoEmail);
        TextView phone = holder.itemView.findViewById(R.id.contactoPhone);


        nombre.setText(contacto.getName().getTitle()+" "+contacto.getName().getFirst()+" "+contacto.getName().getLast());
        genero.setText("Género: "+contacto.getGender());
        ciudad.setText("Ciudad: "+contacto.getLocation().getCity());
        pais.setText("País: "+contacto.getLocation().getCountry());
        email.setText("Email: "+contacto.getEmail());
        phone.setText("Phone: "+contacto.getPhone());
    }

    @Override
    public int getItemCount() {
        return contactoList.size();
    }

    public class RootViewHolder extends RecyclerView.ViewHolder{
        Contacto contacto;
        ContactoAdapter contactoAdapter;
        public RootViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            Button buttonQuitar = itemView.findViewById(R.id.buttonQuitar);
            buttonQuitar.setOnClickListener(view -> {
                listener.onItemClick(getAdapterPosition());
            });
        }
    }

    public List<Contacto> getContactoList() {
        return contactoList;
    }

    public void setContactoList(List<Contacto> contactoList) {
        this.contactoList = contactoList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
