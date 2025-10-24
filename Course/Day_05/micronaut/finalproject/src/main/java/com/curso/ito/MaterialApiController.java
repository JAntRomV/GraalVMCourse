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

@Controller("/api/materiales")
public class MaterialApiController{

    private final MaterialService materialService = new MaterialService();
    private List<Material> materials = materialService.seed();

    @Get(produces = MediaType.APPLICATION_JSON)
    public List<Material> listar() {
        return materials;
    }

    // Agregar nuevo material (recibe JSON)
    @Post(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Material> agregar(@Body Material material) {
        materials.add(material);
        return HttpResponse.created(material);
    }

    @Get("/python")
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
    
    @Get("/js")
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