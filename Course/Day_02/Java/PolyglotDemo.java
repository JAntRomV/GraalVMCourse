import org.graalvm.polyglot.*;

public class PolyglotDemo {
    public static void main(String[] args) {
        try (Context context = Context.create()) {
            // Ejecutar código Python desde Java​

            Value result = context.eval("python",
                "def suma(a, b): return a + b\n" +
                "suma(10, 20)"
            );

            System.out.println("Resultado: " + result.asInt());
        }
    }
}
