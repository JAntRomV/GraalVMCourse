package com.example;

import org.graalvm.polyglot.*;

public class PolyglotMain {
    public static void main(String[] args) {
        // Enable Python and JavaScript in the same Context
        try (Context ctx = Context.newBuilder("python", "js")
                .allowAllAccess(true) // simplify demos; restrict in production
                .option("engine.WarnInterpreterOnly", "false")
                .build()) {

            // --- JavaScript: define and call a function ---
            ctx.eval("js", "function doble(x){ return x*2; }");
            Value jsDoble = ctx.getBindings("js").getMember("doble");
            int r1 = jsDoble.execute(21).asInt();
            System.out.println("JS doble(21) = " + r1);

            // --- Python: import stdlib and define a function ---
            ctx.eval("python",
                "import math\n" +
                "def hipotenusa(a,b):\n" +
                "    return math.hypot(a,b)\n"
            );
            Value pyHip = ctx.getBindings("python").getMember("hipotenusa");
            double r2 = pyHip.execute(3, 4).asDouble();
            System.out.println("Python hipotenusa(3,4) = " + r2);

            // --- Evaluate small snippets ---
            Value sum = ctx.eval("js", "1 + 2 + 3");
            System.out.println("JS 1+2+3 = " + sum.asInt());

            // --- Print from Python ---
            ctx.eval("python", "print('Hola desde GraalPy!')");

            // Optional: detect if another language is available at runtime
            if (isLanguageAvailable(ctx, "wasm")) {
                System.out.println("(Nota) Soporte WASM detectado, puedes cargar módulos WASI si los agregas al classpath.");
            } else {
                System.out.println("(Nota) WASM no detectado en esta instalación. Esto es solo informativo.");
            }
        }
    }

    private static boolean isLanguageAvailable(Context ctx, String id) {
        try {
            ctx.getEngine().getLanguages().get(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
