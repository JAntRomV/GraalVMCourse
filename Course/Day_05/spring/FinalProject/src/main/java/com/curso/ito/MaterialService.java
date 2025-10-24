package com.curso.ito;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import org.springframework.stereotype.Service;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class MaterialService {

    private final CopyOnWriteArrayList<Material> materiales = new CopyOnWriteArrayList<>();

    public MaterialService() {
        materiales.add(new Material("Acero", 12.5, 5000.75, 10));
        materiales.add(new Material("Cobre", 8.2, 7200.50, 5));
        materiales.add(new Material("Aluminio", 20.0, 3000.00, 15));
    }

    public List<Material> getAll() {
        return Collections.unmodifiableList(materiales);
    }

    public Material add(Material m) {
        materiales.add(m);
        return m;
    }
}
