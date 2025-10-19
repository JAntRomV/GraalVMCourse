# Programa que determina si un número es par o impar

# Solicita un número al usuario
numero = int(input("Introduce un número: "))

# Verifica si el número es par o impar
if numero % 2 == 0:
    print(f"El número {numero} es par.")
else:
    print(f"El número {numero} es impar.")
