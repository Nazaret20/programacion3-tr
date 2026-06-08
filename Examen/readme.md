# 🎬 Videoclub — Sistema de Gestión de Películas y Alquileres

Un videoclub necesita un sistema para gestionar su catálogo de películas y los alquileres de sus clientes. El encargado necesita una interfaz gráfica para gestionar el catálogo y registrar alquileres. El sistema debe guardar todos los datos en una base de datos MariaDB (`videoclub`) cuya estructura ya ha sido creada mediante el script `videoclub.sql`.

El programa se organiza en **dos capas independientes**, implementadas cada una en su propio fichero Java:

- **`VideoclubBD.java` (backend):** gestiona toda la conexión JDBC, las consultas SQL y la lógica de negocio (cálculo de tarifas, generación de IDs, comprobación de disponibilidad). No debe importar ni usar ningún componente Swing.
- **`Main.java` (frontend):** construye la interfaz gráfica Swing y gestiona la interacción con el usuario. No debe contener ninguna sentencia SQL. Toda operación con la base de datos se delega en un objeto `VideoclubBD`.

---

## ⚙️ Especificaciones del Sistema

### Tarifas de Alquiler por Género

| Género | Precio por día |
|--------|---------------|
| `"comedia"` | 2,00 € |
| `"accion"` | 3,00 € |
| `"drama"` | 2,50 € |
| Cualquier otro género | 1,50 € |

### Estado de la Película

- `disponible = true` en la BD → la película puede alquilarse; se muestra como `"Disponible"` en la tabla.
- `disponible = false` en la BD → la película está alquilada; se muestra como `"Alquilada"` en la tabla.

---

## Parte 1 — Backend: Persistencia JDBC (`VideoclubBD.java`) — 35 puntos

Crea la clase `VideoclubBD` que gestiona toda la comunicación con la base de datos. Esta clase **no debe importar ningún paquete `javax.swing`**.

1. `public void conectarBD() throws SQLException` *(8 puntos)*
   > ⚠️ La excepción se relanza con `throws SQLException`; es el constructor de `Main` quien mostrará el error con `JOptionPane` y cerrará la ventana si la conexión falla.

2. `public void cerrarConexion()` *(2 puntos)*

3. `public void anyadirPelicula(String titulo, String genero, int anyo) throws SQLException` *(8 puntos)*

4. `public Object[][] obtenerPeliculas() throws SQLException` *(10 puntos)*

5. `public void registrarAlquiler(String cliente, String dni, int idPelicula, int dias) throws SQLException` *(5 puntos)*

6. `public void devolverPelicula(int idPelicula) throws SQLException` *(2 puntos)*

---

## Parte 2 — Backend: Lógica de Negocio (`VideoclubBD.java`) — 15 puntos

Añade a `VideoclubBD` los siguientes métodos de lógica de negocio. Pueden ser `private` si solo los usa la propia clase, o `public` si los llama `Main` directamente.

1. `private int siguienteId(String tabla) throws SQLException` *(3 puntos)*
   > ⚠️ Este método se usa internamente desde `anyadirPelicula()` y `registrarAlquiler()` para asignar IDs sin necesidad de listas en memoria.

2. `public boolean estaDisponible(int idPelicula) throws SQLException` *(3 puntos)*

3. `public double calcularCoste(int idPelicula, int dias) throws SQLException` *(5 puntos)*

4. `public Object[][] obtenerAlquileres() throws SQLException` *(4 puntos)*

---

## Parte 3 — Frontend: Interfaz Swing (`Main.java`) — 35 puntos

Crea la clase `Main` que extiende `JFrame`. Esta clase **no debe contener ninguna cadena SQL** ni ninguna referencia a `Connection`, `Statement`, `PreparedStatement` o `ResultSet`. Toda operación con datos se realiza a través del objeto `bd`.

### Componentes Swing requeridos

- `JMenuBar` con dos menús:
  - **"Películas"**: ítems *Añadir película* y *Ver catálogo*
  - **"Alquileres"**: ítems *Nuevo alquiler*, *Devolver* y *Ver alquileres*
- `JTable` central con `DefaultTableModel` y columnas: `ID · Título · Género · Año · Estado`
- `JScrollPane` que envuelve la `JTable`
- `JLabel` en la zona `SOUTH` del `BorderLayout` como barra de estado

### Métodos

1. `public Main()` *(8 puntos)*

2. `public void actualizarTabla()` *(4 puntos)*

3. `private void accionAnyadirPelicula()` *(5 puntos)*

4. `private void accionNuevoAlquiler()` *(8 puntos)*
   > ⚠️ Para mostrar el título en la barra de estado puedes buscarlo en los datos de `obtenerPeliculas()` o usar una consulta adicional en el backend.

5. `private void accionDevolver()` *(5 puntos)*

6. `private void accionVerAlquileres()` *(3 puntos)*

7. `private void verCatalogo()` *(2 puntos)* — llamado desde el ítem *"Ver catálogo"*

8. `public static void main(String[] args)` *(0 puntos)*

---

## Parte 4 — Arquitectura: Separación de Capas — 15 puntos

| Criterio | Puntos |
|----------|--------|
| `Main.java` no contiene ninguna cadena SQL ni importa `java.sql.*` | 5 |
| `VideoclubBD.java` no importa ni usa ningún componente `javax.swing.*` | 5 |
| El backend comunica resultados al frontend exclusivamente mediante `Object[][]` y tipos primitivos / `String` | 3 |
| `siguienteId()` se usa en `anyadirPelicula()` y en `registrarAlquiler()` para asignar IDs sin listas en memoria | 2 |

---

## 📊 Criterios de Evaluación

### Parte 1 — Backend: Persistencia JDBC (35 puntos)

| Criterio | Puntos |
|----------|--------|
| `conectarBD()` | 8 |
| `cerrarConexion()` | 2 |
| `anyadirPelicula()` | 8 |
| `obtenerPeliculas()` | 10 |
| `registrarAlquiler()` | 5 |
| `devolverPelicula()` | 2 |

### Parte 2 — Backend: Lógica de Negocio (15 puntos)

| Criterio | Puntos |
|----------|--------|
| `siguienteId()` | 3 |
| `estaDisponible()` | 3 |
| `calcularCoste()` | 5 |
| `obtenerAlquileres()` | 4 |

### Parte 3 — Frontend: Interfaz Swing (35 puntos)

| Criterio | Puntos |
|----------|--------|
| Constructor | 8 |
| `verCatalogo()` | 2 |
| `actualizarTabla()`: vacía el modelo y vuelca correctamente el `Object[][]` | 4 |
| `accionAnyadirPelicula()`: pide datos, delega en backend, actualiza tabla y barra de estado | 5 |
| `accionNuevoAlquiler()`: verifica disponibilidad, delega en backend, muestra coste en barra de estado | 8 |
| `accionDevolver()`: pide id, delega en backend y actualiza la interfaz | 5 |
| `accionVerAlquileres()`: muestra el resultado del backend en un diálogo GUI | 3 |
| `main()` | 0 |

---

## ❌ Penalizaciones Globales

| Error | Penalización |
|-------|-------------|
| El código no compila | −30 puntos |
| Errores graves de sintaxis | −10 puntos |
| `Main.java` contiene sentencias SQL directas o importa `java.sql.*` | −10 puntos |
| `VideoclubBD.java` importa o usa componentes `javax.swing` | −10 puntos |
| Se crean clases modelo (`Pelicula`, `Alquiler` u otras) — no son requeridas | −5 puntos |
| No se usa la base de datos (BD completamente ignorada) | −15 puntos |
| Salida de resultados por consola en lugar del GUI | −15 puntos |
| Código sin indentación | −5 puntos |
| Variables con nombres no descriptivos (`a`, `b`, `x`...) | −3 puntos |
| No se cierra la conexión JDBC al terminar la aplicación | −3 puntos |

---

## ⭐ Puntos Extra (Máximo +15 puntos)

| Característica | Bonus |
|----------------|-------|
| Validación del género al añadir película: solo se aceptan `"comedia"`, `"accion"` o `"drama"` (muestra error si es otro) | +3 |
| Validación del número de días mayor que 0 al registrar un alquiler | +2 |
| Cierre correcto de la conexión JDBC en el `WindowListener.windowClosing()` | +3 |
| Búsqueda de alquileres por DNI del cliente (nuevo `JMenuItem` en el menú "Alquileres") | +4 |

<img width="866" height="527" alt="imagen" src="https://github.com/user-attachments/assets/24e99691-a133-4100-8fbe-a1e986f2cd28" />
