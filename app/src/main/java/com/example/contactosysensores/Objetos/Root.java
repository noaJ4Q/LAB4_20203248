package com.example.contactosysensores.Objetos;

import java.util.ArrayList;

public class Root {
    private ArrayList<Contacto> results;
    private Info info;

    public ArrayList<Contacto> getResults() {
        return results;
    }

    public void setResults(ArrayList<Contacto> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
