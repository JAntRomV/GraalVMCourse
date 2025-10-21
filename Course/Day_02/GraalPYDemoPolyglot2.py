# Ejecuta con:
#   graalpy main.py
# Si vas a usar tus propias clases Java (JARs), agrega el classpath:
#   graalpy --vm.cp=./lib/misclases.jar main.py

import java  # módulo de interop con Java (disponible en la distro JVM de GraalPy)

# 1) Cargar clases estándar del JDK
System = java.type("java.lang.System")
ArrayList = java.type("java.util.ArrayList")
# LocalDateTime = java.type("java.time.LocalDateTime")
Duration = java.type("java.time.Duration")

print("[Java] version:", System.getProperty("java.version"))

# 2) Instanciar y usar objetos Java
al = ArrayList()
al.add("uno")
al.add("dos")
al.add("tres")
print("[Java] ArrayList size =", al.size(), "; contenido =", list(al))

# 3) Usar tipos java.time
# t0 = LocalDateTime.now()
# # ... cualquier trabajo ...
# t1 = LocalDateTime.now()
# millis = Duration.between(t0, t1).toMillis()
# print(f"[Java] Duración medida: {millis} ms")

# 4) Llamar métodos cuyo nombre es palabra reservada en Python (usa getattr)
BigInteger = java.type("java.math.BigInteger")
x = BigInteger.valueOf(42)
# ejemplo artificial: acceder a un método con nombre “not” vía getattr
_ = getattr(x, "not")()   # sólo como demo de sintaxis

# 5) (Opcional) Usar TUS clases Java (colócalas en el classpath)
#    Supón que tienes com.example.Calc con método estático sum(int,int)
#    y/o constructor Calc() con método mul(int,int).
# try:
#     Calc = java.type("com.example.Calc")
#     print("[Java] Calc.sum(3,4) =", Calc.sum(3, 4))
#     calc = Calc()
#     print("[Java] calc.mul(6,7) =", calc.mul(6, 7))
# except KeyError:
#     print("[Aviso] com.example.Calc no está en el classpath; omitiendo demo propia.")
