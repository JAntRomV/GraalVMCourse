package com.curso.ito;

import java.util.ArrayList;
import java.util.List;

public class MaterialService {

    public List<Material> seed() {
        List<Material> materiales = new ArrayList<>();

        materiales.add(new Material("Acero", 0, 21750,0));
        materiales.add(new Material("Grava", 0, 5500,0));
        materiales.add(new Material("Arena", 0, 1800,0));
        return materiales;
    }
}
