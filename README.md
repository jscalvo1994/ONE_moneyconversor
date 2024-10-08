# ONE_moneyconversor
This is a challenge from Alura LataM, focused on demonstrating basic API skills from the Backend course


Conversor de Monedas
Descripción
Este programa es un conversor de monedas que permite a los usuarios convertir un monto de una moneda a otra 
utilizando tasas de cambio en tiempo real. 
El conversor obtiene las tasas de cambio de una API y proporciona una interfaz simple en la consola para que los 
usuarios ingresen montos y seleccionen las monedas de origen y destino.

Características Principales
Interfaz de Usuario Interactiva: El programa guía al usuario a través de un menú simple, 
donde puede elegir entre ingresar un monto para convertir o cerrar el programa.
Validación de Entradas: Se implementa una validación robusta para asegurar que las entradas del usuario 

sean correctas, incluyendo la validación de montos y monedas.
Tasas de Conversión en Tiempo Real: Las tasas de cambio son obtenidas de una API, lo que permite a los usuarios
realizar conversiones precisas basadas en los valores actuales del mercado.
Historial de Conversiones: El programa puede ser extendido para incluir un historial de conversiones,
permitiendo al usuario revisar conversiones anteriores.
Funcionamiento
Inicio del Programa:

El programa inicia mostrando un saludo de bienvenida y una breve descripción de su funcionalidad.
Selección de Opciones:

Se presenta un menú donde el usuario puede elegir entre ingresar un monto para convertir o cerrar el programa.
Las opciones se manejan mediante un bucle que continuará hasta que el usuario decida cerrar el programa.
Ingreso de Monto:

Al seleccionar la opción de ingresar un monto, se solicita al usuario que ingrese un monto en la consola.
El programa valida que la entrada sea un número válido. Si no lo es, se ofrece la opción de corregir 
el monto o cerrar el programa.
Selección de Monedas:

El usuario selecciona la moneda base del monto ingresado y la moneda a la que desea convertir.
Se muestra una lista de monedas disponibles, y el usuario debe ingresar el código de tres letras correspondiente
a la moneda seleccionada.

La validación asegura que solo se acepten monedas válidas.
Confirmación de Selección:

Después de que el usuario ha ingresado el monto y seleccionado las monedas, se le solicita
que confirme si la información es correcta.
El usuario puede optar por corregir el monto, corregir la moneda o confirmar que todo es correcto.
Conversión de Moneda:

Si todas las selecciones son correctas, el programa realiza la conversión utilizando las tasas de cambio
obtenidas de la API y muestra el resultado en la consola.
Cierre del Programa:

El usuario puede cerrar el programa en cualquier momento seleccionando la opción correspondiente 
en el menú principal.
Requisitos
Java Development Kit (JDK): Asegúrate de tener instalado JDK para compilar y ejecutar el programa.
Librería Gson: Este programa utiliza la librería Gson para manejar la conversión de JSON a objetos Java. 
Asegúrate de incluirla en tu proyecto. Puedes añadir Gson a tu proyecto descargando el JAR.
Ejemplo de Uso
Inicia el programa.
Selecciona la opción "1" para ingresar un monto.
Ingresa un monto válido (por ejemplo, "100").
Selecciona la moneda base (por ejemplo, "USD").
Confirma la selección.
Selecciona la moneda a la que deseas convertir (por ejemplo, "EUR").
Confirma la selección.
El programa muestra el monto convertido