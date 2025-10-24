import org.graalvm.polyglot.*;

public class PolyglotMain {
    public static void main(String[] args) {
        // String GRAALPY_HOME = "/home/vboxuser/.sdkman/candidates/java/22.3.r17-grl/languages/python/lib-graalpython";

        // Engine eng = Engine.newBuilder()
        //     .option("python.SysPrefix", GRAALPY_HOME)
        //     .option("python.CoreHome", GRAALPY_HOME)
        //     .option("python.StdLibHome", GRAALPY_HOME+"/lib/graalpy-38-native-x86_64-linux")
        //     .option("python.Executable", GRAALPY_HOME)
        //     .option("python.CAPI",  GRAALPY_HOME)
        //     .option("log.python.level", "SEVERE")
        //     .build();

        try (Context ctx = Context.newBuilder()
                .allowAllAccess(true) // para intercambio de objetos simples
                //.engine(eng)
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
