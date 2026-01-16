# 💰 Conversor de Monedas - Java Console App

Este proyecto es un desafio propuesto por el **programa ONE.** Es un conversor de monedas interactivo desarrollado en Java, que utiliza la API **ExchangeRate-API** para obtener tasas de cambio en tiempo real. La aplicación opera desde la consola y permite a los usuarios realizar conversiones entre pares de divisas predefinidos, además de guardar un historial de todas las transacciones realizadas.

---

## ✨ Características Principales

* **Conversiones en Tiempo Real:** Obtiene tasas actualizadas utilizando la API.
* **Selección por Menú:** Ofrece un menú simple con 6 opciones de conversión predefinidas (USD, ARS, MXN, BRL).
* **Gestión Segura de Clave API:** Utiliza un archivo `.properties` y `.gitignore` para asegurar la clave API, evitando exponerla en el repositorio.
* **Historial de Transacciones:** Guarda todas las conversiones exitosas en una lista y las serializa en formato JSON (`Historial.json`) al salir del programa.
* **Manejo de Errores:** Incluye manejo de excepciones (`try-catch`) para fallas de red (`IOException`) o interrupciones, garantizando una salida controlada del programa.

---

## 🛠️ Tecnologías y Dependencias

* **Lenguaje:** Java 17+
* **Librería HTTP:** `java.net.http.HttpClient` (Cliente nativo de Java)
* **Serialización JSON:** Google Gson

### Dependencias (Maven/Gradle)

Necesitarás incluir la librería Gson en tu archivo de configuración

---

## ⚙️ Configuración e Instalación

Para ejecutar el proyecto, debes obtener tu propia clave de la API y configurarla localmente:

1.  **Obtener API Key:** Regístrate en [ExchangeRate-API](https://www.exchangerate-api.com/) para obtener tu clave personal.
2.  **Crear `config.properties`:** En la raíz del proyecto (junto a la carpeta `src`), crea un archivo llamado **`config.properties`**.
3.  **Añadir la Clave:** Dentro de `config.properties`, pega tu clave con el siguiente formato:
    ```properties
    api.key=TU-CLAVE-DE-API-SECRETA
    ```

**Nota Importante:** El archivo `config.properties` está excluido de GitHub por el archivo `.gitignore`, garantizando la seguridad de la clave API.

---

## ▶️ Uso

1.  Compila y ejecuta el proyecto.
2.  El menú te pedirá que selecciones una opción (1-6) y la cantidad a convertir.
3.  Al seleccionar **7. Salir**, el programa generará el archivo `Historial.json` en la raíz del proyecto con el registro de todas tus conversiones.

---

## 🛑 Estado del Proyecto

El proyecto está completo y es funcional. Pero **disponible para mejoras.**
