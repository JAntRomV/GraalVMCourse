package com.ito.cni.curso;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    public List<Usuario> generarUsuariosEjemplo() {
        List<Usuario> usuarios = new ArrayList<>();

        usuarios.add(new Usuario("25010230", "Acevedo Benitez Porfirio Omar", "luis.gomez@example.com"));
        usuarios.add(new Usuario("22010094", "Baez Vázquez Citlali Itzel", "carlos.ruiz@example.com"));
        usuarios.add(new Usuario("22010097", "Cordoba Gutierrez Eber", "laura.diaz@example.com"));
        usuarios.add(new Usuario("C22010603", "Hernández Heredía Kevin", "keving.hernandez@example.com"));
        usuarios.add(new Usuario("25010240", "Hernández Ventura María Fernanda", "maria.lopez@example.com"));
        usuarios.add(new Usuario("22010108", "Quiñones Rios Sandra", "laura.diaz@example.com"));
        usuarios.add(new Usuario("21011062", "Ventura Gallardo Jośe Alfredo", "alfredo.ventura@example.com"));
        usuarios.add(new Usuario("23010573", "Xolio Ramos Herson Elías", "ana.perez@example.com"));

        return usuarios;
    }
}
