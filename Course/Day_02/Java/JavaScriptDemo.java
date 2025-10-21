import org.graalvm.polyglot.*;

public class JavaScriptDemo {
    public static void main(String[] args) {

        try (Context context = Context.create("js")) {

            // Procesar JSON con JavaScript​
            String jsonData = "{\"nombre\":\"Juan\",\"edad\":30}";
            context.getBindings("js").putMember("jsonStr", jsonData);
            Value result = context.eval("js",
                "let obj = JSON.parse(jsonStr);" +
                "obj.edad += 1;" +
                "JSON.stringify(obj)"
            );
            
            System.out.println(result.asString()); //OUTPUT: {"nombre":"Juan","edad":31}​
        }
    }
}
