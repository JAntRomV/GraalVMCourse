import org.graalvm.polyglot.*;

public class PolyglotMain {
    public static void main(String[] args) {
        String GRAALPY_HOME = "/home/jarv/.pyenv/versions/graalpy-24.1.0";

        try (Context ctx = Context.newBuilder()
                .allowAllAccess(true) // para intercambio de objetos simples
                .build()) {

            Value pyResult = ctx.eval("python", "sum([i*i for i in range(1, 6)])"); // 1^2+...+5^2
            Value jsFunc = ctx.eval("js", "(function(x){ return x*2; })");
            int doubled = jsFunc.execute(pyResult.asInt()).asInt();

            System.out.println("[Python] sum of squares 1..5 = " + pyResult.asInt());
            System.out.println("[JS] doubled = " + doubled);

            // Compartir variables entre lenguajes
            ctx.getBindings("python").putMember("base", 10);
            Value pyCode = ctx.eval("python", "base ** 3");
            System.out.println("[Python] base^3 = " + pyCode.asInt());
        }
    }
}
