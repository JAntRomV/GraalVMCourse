import org.graalvm.polyglot.*;

public class PythonFromJava {
    public static void main(String[] args) {
        try (Context context = Context.create("python")) {

            Value result = context.eval("python", 
                "def calcular(x, y):\n" +
                "    return x ** 2 + y ** 2\n" +
                "calcular(3, 4)"
            );

            System.out.println("Resultado: " + result.asInt());
        }
    }
}
