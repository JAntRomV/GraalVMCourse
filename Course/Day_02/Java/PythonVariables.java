import org.graalvm.polyglot.*;

public class PythonVariables {
    public static void main(String[] args) {
        Context ctx = Context.create("python");
        ctx.getBindings("python").putMember("javaVar", 100);
        Value result = ctx.eval("python", "javaVar * 2");
        System.out.println(result.asInt()); // 200
    }
}
