package com.curso.ito;

import jakarta.inject.Singleton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Singleton
public class MaterialService {

   private final List<Material> materiales = new ArrayList<>();

    public MaterialService() {
        materiales.add(new Material("Acero", 12.5, 5000.75));
        materiales.add(new Material("Cobre", 8.2, 7200.50));
        materiales.add(new Material("Aluminio", 20.0, 3000.00));
    }

    public List<Material> getAll() {
        return Collections.unmodifiableList(materiales);
    }

    public void addMaterial(Material material) {
        materiales.add(material);
    }
}
