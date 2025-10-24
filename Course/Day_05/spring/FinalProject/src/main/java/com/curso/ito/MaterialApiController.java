package com.curso.ito;

import org.graalvm.polyglot.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/materiales")
public class MaterialApiController {

    private final MaterialService service;

    public MaterialApiController(MaterialService service) {
        this.service = service;
    }

    @GetMapping
    public List<Material> listar() {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<Material> agregar(@RequestBody Material material) {
        Material creado = service.add(material);
        return ResponseEntity.created(URI.create("/api/materiales")).body(creado);
    }

    @GetMapping("/python")
    public String pythonTest() {
        double[] ventas = new double[]{100,150,200,175};
        String result = "No result";
        try (Context ctx = Context.newBuilder("python")
                            .allowAllAccess(true)
                            .build()) {
            ctx.getBindings("python").putMember("datos", ventas);
            Value v = ctx.eval("python",
                "import statistics, types\n"
                + "res = types.SimpleNamespace()\n"
                + "res.promedio = float(statistics.mean(datos))\n"
                + "res.mediana  = float(statistics.median(datos))\n"
                + "res"
            );

            result += "Promedio = " + v.getMember("promedio").asDouble() + "\n";
            result += "Mediana  = " + v.getMember("mediana").asDouble() + "\n";
        }
        return result;
    }
    
    @GetMapping("/js")
    public String jsTest() {
        String res = "No result";
        
        try (Context context = Context.create("js")) {

            // Procesar JSON con JavaScript​
            String jsonData = "{\"nombre\":\"Juan\",\"edad\":30}";
            context.getBindings("js").putMember("jsonStr", jsonData);
            Value result = context.eval("js",
                "let obj = JSON.parse(jsonStr);" +
                "obj.edad += 1;" +
                "JSON.stringify(obj)"
            );
            
            res = result.asString(); 
        }
        return res;
    }
}