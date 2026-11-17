package com.empresa;

/**
 * ============================================================
 * CLASE: ExEmpleado
 * ------------------------------------------------------------
 * PROPÓSITO:
 * Un ex empleado dentro de la empresa. Hereda de Empleado
 * la mayoría de sus atributos y métodos, y agrega solo
 * los datos propios de la baja.
 *
 * CONCEPTOS APLICADOS:
 *   Herencia (extends Empleado)
 *   Llamada al constructor de la superclase (super(...))
 *   Sobrescritura de métodos (@Override mostrarInfo/toString)
 *   Reutilización: no se redeclaran numeroEmpleado, nombre,
 *   puesto, telefono, horario, jefeDirecto ni mesesEnPuesto,
 *   porque ya existen en Empleado (son private, así que se
 *   accede a ellos con los getters/setters heredados).
 * ============================================================
 */
public class ExEmpleado extends Empleado {

    // ---------- ATRIBUTOS PROPIOS (solo de un ex empleado) ----------
    /** Fecha en que causó baja (ej: "2026-11-15") */
    private String fechaBaja;

    /** Motivo de la baja (ej: "Renuncia por nuevo empleo") */
    private String motivoBaja;

    /**
     * Tipo de baja: "Renuncia", "Despido",
     * "Fin de contrato" o "Jubilación"
     */
    private String tipoBaja;

    // ---------- CONSTRUCTORES ----------

    /**
     * VACÍO (sin parámetros).
     * Llama al constructor vacío de Empleado con super().
     */
    public ExEmpleado() {
        super(); // nombre = "Sin nombre", puesto = "Sin puesto"
        this.fechaBaja = "Sin fecha";
        this.motivoBaja = "Sin motivo";
        this.tipoBaja = "Sin especificar";
    }

    /**
     * CON PARÁMETROS (sobrecarga).
     * Los 7 primeros parámetros se pasan tal cual al
     * constructor de Empleado con super(...); los 3
     * últimos son los propios de ExEmpleado.
     */
    public ExEmpleado(int numeroEmpleado, String nombre, String puesto,
                      String telefono, String horario, String jefeDirecto,
                      int mesesEnPuesto,
                      String fechaBaja, String motivoBaja, String tipoBaja) {
        super(numeroEmpleado, nombre, puesto, telefono, horario,
              jefeDirecto, mesesEnPuesto);
        this.fechaBaja = fechaBaja;
        this.motivoBaja = motivoBaja;
        this.tipoBaja = tipoBaja;
    }

    /**
     * CONSTRUCTOR DE CONVERSIÓN.
     * Crea un ExEmpleado a partir de un Empleado activo.
     * Es el que usa GestionEmpleados.darDeBaja(...): copia
     * todos los datos heredados con los getters de Empleado,
     * porque sus atributos son private y no se pueden
     * leer directamente desde aquí.
     */
    public ExEmpleado(Empleado empleado,
                      String fechaBaja, String motivoBaja, String tipoBaja) {
        super(empleado.getNumeroEmpleado(), empleado.getNombre(),
              empleado.getPuesto(), empleado.getTelefono(),
              empleado.getHorario(), empleado.getJefeDirecto(),
              empleado.getMesesEnPuesto());
        this.fechaBaja = fechaBaja;
        this.motivoBaja = motivoBaja;
        this.tipoBaja = tipoBaja;
    }

    // ---------- MÉTODOS GETTERS Y SETTERS (solo los nuevos) ----------
    // Los de numeroEmpleado, nombre, puesto, etc. ya se heredan
    // de Empleado, no hace falta volver a escribirlos.

    public String getFechaBaja() { return fechaBaja; }
    public void setFechaBaja(String fechaBaja) { this.fechaBaja = fechaBaja; }

    public String getMotivoBaja() { return motivoBaja; }
    public void setMotivoBaja(String motivoBaja) { this.motivoBaja = motivoBaja; }

    public String getTipoBaja() { return tipoBaja; }
    public void setTipoBaja(String tipoBaja) { this.tipoBaja = tipoBaja; }

    // ---------- MÉTODOS DE NEGOCIO ----------

    /**
     * Devuelve true si la baja fue voluntaria
     * (el empleado renunció).
     */
    public boolean esBajaVoluntaria() {
        return "Renuncia".equalsIgnoreCase(tipoBaja);
    }

    /**
     * Convierte este ex empleado en un Empleado activo.
     * Crea un Empleado nuevo con los mismos datos heredados
     * (número, nombre, puesto, teléfono, horario, jefe),
     * pero con mesesEnPuesto en 0, porque al reingresar
     * empieza un periodo nuevo. Los datos de la baja
     * (fecha, motivo, tipo) se pierden, porque ya no
     * es un ex empleado; por eso GestionEmpleados lo
     * quita de la lista de ex empleados al reingresar.
     * @return un Empleado listo para agregarse a activos
     */
    public Empleado toEmpleado() {
        return new Empleado(getNumeroEmpleado(), getNombre(), getPuesto(),
                getTelefono(), getHorario(), getJefeDirecto(), 0);
    }

    /**
     * Igual que toEmpleado(), pero permite cambiar el puesto,
     * el horario y el jefe directo al reingresar.
     */
    public Empleado toEmpleado(String nuevoPuesto, String nuevoHorario,
                               String nuevoJefeDirecto) {
        return new Empleado(getNumeroEmpleado(), getNombre(), nuevoPuesto,
                getTelefono(), nuevoHorario, nuevoJefeDirecto, 0);
    }

    /**
     * Sobrescritura de mostrarInfo().
     * Primero reutiliza la versión de Empleado con
     * super.mostrarInfo() y luego agrega los datos
     * propios de la baja.
     */
    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("─────────── DATOS DE LA BAJA ───────────");
        System.out.println("Fecha de baja        : " + fechaBaja);
        System.out.println("Tipo de baja         : " + tipoBaja);
        System.out.println("Motivo de la baja    : " + motivoBaja);
        System.out.println("¿Baja voluntaria?    : " + (esBajaVoluntaria() ? "Sí" : "No"));
        System.out.println("─────────────────────────────────────────");
    }

    /**
     * Representación textual del objeto.
     */
    @Override
    public String toString() {
        return "ExEmpleado[" + getNumeroEmpleado() + ", " + getNombre()
               + ", " + getPuesto() + ", baja: " + fechaBaja
               + " (" + tipoBaja + ")]";
    }
}
