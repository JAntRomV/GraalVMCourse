package com.curso.ito;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/materiales")
    public String materiales() {
        // No pasamos modelo: la vista hace fetch a la API
        return "materiales";
    }
}