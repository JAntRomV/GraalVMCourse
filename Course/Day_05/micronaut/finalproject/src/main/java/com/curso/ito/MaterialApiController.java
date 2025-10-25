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
import jakarta.inject.Inject;

@Controller("/api/materiales")
public class MaterialApiController{

    @Inject
    MaterialService materialService;

    @Get(produces = MediaType.APPLICATION_JSON)
    public List<Material> listar() {
        List<Material> materials = materialService.getAll();
        return materials;
    }

    // Agregar nuevo material (recibe JSON)
    /*@Post(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Material> agregar(@Body Material material) {
        materialService.addMaterial(material);
        return HttpResponse.created(material);
    }*/

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

    @Post
    public Material agrega(@Body Material mate) {
        for (Material mat : materialService.getAll()) {
            if (mate.getTipo().equalsIgnoreCase(mat.getTipo()) && mate.getTipo().equalsIgnoreCase("Acero")){
                mat.setToneladas(mat.getToneladas() + mate.getToneladas());
                mat = calculaJava(mat);
                return mat;
            } else if (mate.getTipo().equalsIgnoreCase(mat.getTipo()) && mate.getTipo().equalsIgnoreCase("Grava")){
                mat.setToneladas(mat.getToneladas() + mate.getToneladas());
                mat = calculaPython(mat);
                return mat;
            } else if (mate.getTipo().equalsIgnoreCase(mat.getTipo()) && mate.getTipo().equalsIgnoreCase("Arena")){
                mat = calculaJS(mat);
                return mat;
            } else if (mate.getTipo().equalsIgnoreCase(mat.getTipo())){
                mat = calculaOtro(mat);
                return mat;
            }
        }
        mate = calculaOtro(mate);
        materialService.addMaterial(mate);
        return mate;
    }

    public Material calculaJava(Material mate){
        if(mate.getToneladas() >= 5){
            mate.setDescuento(10);
            mate.setPrecioSinDes(mate.getPrecio()*mate.getToneladas());
            mate.setPrecioTotal(mate.getPrecioSinDes()*0.90);
        } else {
            mate.setDescuento(0);
            mate.setPrecioSinDes(mate.getPrecio()*mate.getToneladas());
            mate.setPrecioTotal(mate.getPrecioSinDes());
        }
        return mate;
    }

    public Material calculaPython(Material mate){
        try (Context ctx = Context.newBuilder("python")
                            .allowAllAccess(true)
                            .build()) {
            ctx.getBindings("python").putMember("material", mate);
            Value v = ctx.eval("python",
                "if material.getToneladas() >= 10:\n"
                + "    material.setDescuento(5)\n"
                + "    material.setPrecioSinDes(material.getPrecio()*material.getToneladas())\n"
                + "    material.setPrecioTotal(material.getPrecioSinDes()*0.95)\n"
                + "else:\n"
                + "    material.setDescuento(0)\n"
                + "    material.setPrecioSinDes(material.getPrecio()*material.getToneladas())\n"
                + "    material.setPrecioTotal(material.getPrecioSinDes())\n"
            );
            mate = v.getMember("material").as(Material.class);
        }
        return mate;
    }

    public Material calculaJS(Material mate){
        
        return mate;
    }

    public Material calculaOtro(Material mate){
        
        return mate;
    }
}