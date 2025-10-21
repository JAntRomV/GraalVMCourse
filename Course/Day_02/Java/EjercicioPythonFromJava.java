import org.graalvm.polyglot.*;

public class EjercicioPythonFromJava {
    public static void main(String[] args) {
        double[] ventas = new double[]{100,150,200,175};

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

            System.out.println("Promedio = " + v.getMember("promedio").asDouble());
            System.out.println("Mediana  = " + v.getMember("mediana").asDouble());
        }
    }
}
