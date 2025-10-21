// Ejecuta con GraalVM Node:
//   node --polyglot index.js
// (en algunas versiones antiguas: node --jvm --polyglot index.js)

// --- Interop con Java (clases estándar)
const LocalDateTime = Java.type('java.time.LocalDateTime');
const Duration = Java.type('java.time.Duration');

const start = LocalDateTime.now();
console.log('[Java] Inicio:', start.toString());

// --- Políglota: evalúa Python desde Node (GraalJS)
const resultPy = Polyglot.eval('python', `
import math
def hipotenusa(a, b):
    return math.hypot(a, b)
hipotenusa(3, 4)
`);
console.log('[Python] hipotenusa(3,4) =', resultPy);

// --- JS normal (Node): ejemplo simple
function doble(x) { return x * 2; }
console.log('[JS] doble(21) =', doble(21));

// --- Pasar funciones/valores entre lenguajes
const py = Polyglot.eval('python', `
def elevar(a, b):
    return a ** b
elev = elevar
elev
`);
console.log('[Python->JS] elevar(2,10) =', py(2, 10));

// --- Usar tipos Java para medir duración
const end = LocalDateTime.now();
const millis = Duration.between(start, end).toMillis();
console.log(`[Java] Duración total: ${millis} ms`);
