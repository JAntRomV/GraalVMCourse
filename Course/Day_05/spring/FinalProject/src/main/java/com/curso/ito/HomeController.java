package com.curso.ito;

import org.graalvm.polyglot.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {

    private final MaterialService materialService = new MaterialService();
    private List<Material> materials = materialService.seed();

    @GetMapping
    public List<Material> Get() {
        return materials;
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