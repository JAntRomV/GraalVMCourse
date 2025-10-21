#include <stdio.h>
#include <graalvm/llvm/polyglot.h>

int main() {
    polyglot_value py = polyglot_eval("python", "x = 40 + 2\nx");
    printf("Resultado desde Python: %d\n", polyglot_as_i32(py));
    return 0;
}
