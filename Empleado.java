package com.empresa;

/**
 * ============================================================
 * CLASE: Empleado
 * ------------------------------------------------------------
 * PROPÓSITO:
 * Un empleado dentro de la empresa. Encapsula
 * todos los datos personales y laborales del mismo.
 *
 * CONCEPTOS APLICADOS :
 *   Declaración de clase
 *   Definición de atributos (encapsulados - private)
 *   Constructor con parámetros (sobrecarga de constructores)
 *   Métodos getters/setters (encapsulamiento)
 *   Método de negocio (calcularAntiguedad, mostrarInfo)
 * ============================================================
 */
public class Empleado {

    // ---------- ATRIBUTOS (encapsulados con private) ----------
    /** Número único que identifica al empleado */
    private int numeroEmpleado;

    /** Nombre  del empleado */
    private String nombre;

    /** Puesto  */
    private String puesto;

    /** Teléfono de  */
    private String telefono;

    /** Horario de trabajo (ej: "08:00 - 17:00") */
    private String horario;

    /** Nombre del jefe directo */
    private String jefeDirecto;

    /** Tiempo en el puesto medido en meses */
    private int mesesEnPuesto;

    // ---------- CONSTRUCTORES ----------

    /**
     *  VACÍO (sin parámetros).
     * Permite crear un empleado y asignarle los valores
     * posteriormente mediante los setters.
     */
    public Empleado() {
        this.nombre = "Sin nombre";
        this.puesto = "Sin puesto";
    }

    /**
     *   PARÁMETROS (sobrecarga).
     * Inicializa los atributos al instanciar.
     */
    public Empleado(int numeroEmpleado, String nombre, String puesto,
                    String telefono, String horario, String jefeDirecto,
                    int mesesEnPuesto) {
        this.numeroEmpleado = numeroEmpleado;
        this.nombre = nombre;
        this.puesto = puesto;
        this.telefono = telefono;
        this.horario = horario;
        this.jefeDirecto = jefeDirecto;
        this.mesesEnPuesto = mesesEnPuesto;
    }

    // ---------- MÉTODOS GETTERS Y SETTERS ----------
    // (Encapsulamiento: acceso controlado a los atributos)

    public int getNumeroEmpleado() { return numeroEmpleado; }
    public void setNumeroEmpleado(int numeroEmpleado) { this.numeroEmpleado = numeroEmpleado; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPuesto() { return puesto; }
    public void setPuesto(String puesto) { this.puesto = puesto; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public String getJefeDirecto() { return jefeDirecto; }
    public void setJefeDirecto(String jefeDirecto) { this.jefeDirecto = jefeDirecto; }

    public int getMesesEnPuesto() { return mesesEnPuesto; }
    public void setMesesEnPuesto(int mesesEnPuesto) { this.mesesEnPuesto = mesesEnPuesto; }

    // ---------- MÉTODOS DE NEGOCIO ----------

    /**
     * Calcula los años de antigüedad.
     * @return años completos trabajados
     */
    public int calcularAniosEnPuesto() {
        return this.mesesEnPuesto / 12;
    }

    /**
     * Devuelve "Sí" o "No" si el empleado lleva más de 1 año.
     */
    public boolean esEmpleadoAntiguo() {
        return this.mesesEnPuesto >= 12;
    }

    /**
     * Imprime en consola toda la información del empleado.
     * Método reutilizable por cualquier instancia.
     */
    public void mostrarInfo() {
        System.out.println("─────────── DATOS DEL EMPLEADO ───────────");
        System.out.println("Número de empleado : " + numeroEmpleado);
        System.out.println("Nombre             : " + nombre);
        System.out.println("Puesto             : " + puesto);
        System.out.println("Teléfono           : " + telefono);
        System.out.println("Horario            : " + horario);
        System.out.println("Jefe directo       : " + jefeDirecto);
        System.out.println("Tiempo en puesto   : " + mesesEnPuesto + " meses ("
                           + calcularAniosEnPuesto() + " años)");
        System.out.println("¿Empleado antiguo? : " + (esEmpleadoAntiguo() ? "Sí" : "No"));
        System.out.println("─────────────────────────────────────────");
    }

    /**
     * Representación textual del objeto (útil para depuración).
     */
    @Override
    public String toString() {
        return "Empleado[" + numeroEmpleado + ", " + nombre + ", " + puesto + "]";
    }
}