import java

# Importar clases Java​
ArrayList = java.type('java.util.ArrayList')
HashMap = java.type('java.util.HashMap')


# Usar clases Java​
lista = ArrayList()
lista.add("Elemento 1")
lista.add("Elemento 2")
print(lista.size())  # 2​
for i in lista:
    print(i)

# Crear HashMap​
mapa = HashMap()
mapa.put("clave", "valor")
print(mapa.get("clave"))  # valor


