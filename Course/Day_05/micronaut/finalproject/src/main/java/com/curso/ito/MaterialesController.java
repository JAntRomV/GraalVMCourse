package com.curso.ito;

import org.graalvm.polyglot.*;
import io.micronaut.http.*;
import io.micronaut.http.annotation.*;
import io.micronaut.http.MediaType;
import io.micronaut.views.View;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.net.URI;


@Controller("/materiales")
public class MaterialesController {

    private final MaterialService materialService = new MaterialService();
    private List<Material> materials = materialService.seed();

    @Get
    @View("materiales")
    public void vista() {
        // no necesita modelo: todo se carga vía JavaScript (fetch)
    }

}