package com.empresa;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * CLASE: GestionEmpleados
 * ------------------------------------------------------------
 * PROPÓSITO:
 * Administra dos colecciones (listas): una de objetos
 * Empleado (activos) y otra de objetos ExEmpleado (bajas).
 * CONCEPTOS APLICADOS:
 *  Atributo de tipo colección (ArrayList<Empleado>)
 *  Constructor que inicializa la colección
 *  Métodos con retorno y parámetros
 *  Instanciación de objetos Empleado dentro de la clase
 *  Herencia: ExEmpleado ES UN Empleado, por eso darDeBaja()
 *  puede convertir uno en otro sin perder sus datos.
 * ============================================================
 */
public class GestionEmpleados {

    // ---------- ATRIBUTOS ----------
    /** Lista dinámica que almacena  los empleados activos */
    private List<Empleado> listaEmpleados;

    /** Lista dinámica que almacena los ex empleados */
    private List<ExEmpleado> listaExEmpleados;

    // ---------- CONSTRUCTOR ----------
    /**
     * Inicializa ambas listas cada vez que se crea un
     * gestor de empleados.
     */
    public GestionEmpleados() {
        this.listaEmpleados = new ArrayList<>();
        this.listaExEmpleados = new ArrayList<>();
    }

    // ---------- MÉTODOS DE GESTIÓN (EMPLEADOS ACTIVOS) ----------

    /**
     * Agrega un nuevo empleado.
     * @param empleado objeto Empleado a registrar
     */
    public void agregarEmpleado(Empleado empleado) {
        listaEmpleados.add(empleado);
        System.out.println("✔ Empleado agregado: " + empleado.getNombre());
    }

    /**
     * Busca empleado por su número.
     * @param numeroEmpleado número único
     * @return el empleado encontrado o null si no existe
     */
    public Empleado buscarPorNumero(int numeroEmpleado) {
        for (Empleado e : listaEmpleados) {
            if (e.getNumeroEmpleado() == numeroEmpleado) {
                return e;
            }
        }
        return null;
    }

    /**
     * Lista todos los empleados registrados.
     */
    public void listarEmpleados() {
        if (listaEmpleados.isEmpty()) {
            System.out.println(" No hay empleados registrados.");
            return;
        }
        System.out.println("\n========= LISTA DE EMPLEADOS =========");
        for (Empleado e : listaEmpleados) {
            e.mostrarInfo();
        }
    }

    /**
     * Elimina un empleado.
     * @return true si se eliminó, false si no existía
     */
    public boolean eliminarEmpleado(int numeroEmpleado) {
        Empleado e = buscarPorNumero(numeroEmpleado);
        if (e != null) {
            listaEmpleados.remove(e);
            System.out.println(" Empleado eliminado: " + e.getNombre());
            return true;
        }
        System.out.println(" Empleado no encontrado.");
        return false;
    }

    /** Devuelve la cantidad total de empleados registrados */
    public int totalEmpleados() {
        return listaEmpleados.size();
    }

    // ---------- MÉTODOS DE GESTIÓN (EX EMPLEADOS) ----------

    /**
     * Registra directamente un ex empleado ya creado
     * (por ejemplo, con el constructor con parámetros).
     */
    public void agregarExEmpleado(ExEmpleado exEmpleado) {
        listaExEmpleados.add(exEmpleado);
        System.out.println("✔ Ex empleado agregado: " + exEmpleado.getNombre());
    }

    /**
     * Da de baja a un empleado activo y lo convierte en
     * ex empleado, conservando todos sus datos.
     *
     * Cómo funciona: busca al empleado, crea un ExEmpleado
     * con el constructor de conversión (que copia los datos
     * heredados), lo agrega a la lista de ex empleados y
     * lo quita de la lista de activos.
     *
     * @param numeroEmpleado número único del empleado a dar de baja
     * @param fechaBaja fecha de la baja (ej: "2026-11-15")
     * @param motivoBaja motivo de la baja
     * @param tipoBaja "Renuncia", "Despido", "Fin de contrato" o "Jubilación"
     * @return el ExEmpleado creado, o null si el empleado no existía
     */
    public ExEmpleado darDeBaja(int numeroEmpleado, String fechaBaja,
                                String motivoBaja, String tipoBaja) {
        Empleado e = buscarPorNumero(numeroEmpleado);
        if (e == null) {
            System.out.println(" Empleado no encontrado, no se puede dar de baja.");
            return null;
        }
        ExEmpleado ex = new ExEmpleado(e, fechaBaja, motivoBaja, tipoBaja);
        listaEmpleados.remove(e);
        listaExEmpleados.add(ex);
        System.out.println("✔ Empleado dado de baja: " + ex.getNombre()
                           + " (" + tipoBaja + ")");
        return ex;
    }

    /**
     * Busca un ex empleado por su número.
     * @return el ex empleado encontrado o null si no existe
     */
    public ExEmpleado buscarExPorNumero(int numeroEmpleado) {
        for (ExEmpleado e : listaExEmpleados) {
            if (e.getNumeroEmpleado() == numeroEmpleado) {
                return e;
            }
        }
        return null;
    }

    /**
     * Lista todos los ex empleados registrados.
     * Funciona igual que listarEmpleados(), porque
     * ExEmpleado hereda mostrarInfo() (sobrescrito).
     */
    public void listarExEmpleados() {
        if (listaExEmpleados.isEmpty()) {
            System.out.println(" No hay ex empleados registrados.");
            return;
        }
        System.out.println("\n========= LISTA DE EX EMPLEADOS =========");
        for (ExEmpleado e : listaExEmpleados) {
            e.mostrarInfo();
        }
    }

    /** Devuelve la cantidad total de ex empleados registrados */
    public int totalExEmpleados() {
        return listaExEmpleados.size();
    }

    // ---------- FILTRO POR TIPO DE BAJA ----------

    /**
     * Devuelve una lista nueva con solo los ex empleados
     * cuyo tipo de baja coincide (sin importar mayúsculas).
     * La lista original no se modifica.
     * @param tipoBaja "Renuncia", "Despido", "Fin de contrato" o "Jubilación"
     * @return lista con los ex empleados de ese tipo (puede estar vacía)
     */
    public List<ExEmpleado> filtrarExPorTipoBaja(String tipoBaja) {
        List<ExEmpleado> resultado = new ArrayList<>();
        for (ExEmpleado e : listaExEmpleados) {
            if (e.getTipoBaja().equalsIgnoreCase(tipoBaja.trim())) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    /** Devuelve cuántos ex empleados hay de un tipo de baja */
    public int contarExPorTipoBaja(String tipoBaja) {
        return filtrarExPorTipoBaja(tipoBaja).size();
    }

    /**
     * Muestra solo los ex empleados de un tipo de baja.
     * Si no hay ninguno de ese tipo, lo avisa en consola.
     */
    public void listarExPorTipoBaja(String tipoBaja) {
        List<ExEmpleado> filtrados = filtrarExPorTipoBaja(tipoBaja);
        if (filtrados.isEmpty()) {
            System.out.println(" No hay ex empleados con baja de tipo: " + tipoBaja);
            return;
        }
        System.out.println("\n========= EX EMPLEADOS: " + tipoBaja.toUpperCase()
                           + " (" + filtrados.size() + ") =========");
        for (ExEmpleado e : filtrados) {
            e.mostrarInfo();
        }
    }

    // ---------- REINGRESO ----------

    /**
     * Reingresa a un ex empleado como empleado activo,
     * con su mismo puesto, horario y jefe.
     *
     * Cómo funciona: busca al ex empleado, lo convierte
     * en Empleado con ex.toEmpleado() (meses en puesto
     * en 0, porque empieza un periodo nuevo), lo quita
     * de la lista de ex empleados y lo agrega a activos.
     *
     * @param numeroEmpleado número único del ex empleado
     * @return el Empleado reingresado, o null si no existe
     *         como ex empleado o si ya está activo
     */
    public Empleado reingresarExEmpleado(int numeroEmpleado) {
        ExEmpleado ex = buscarExPorNumero(numeroEmpleado);
        if (ex == null) {
            System.out.println(" Ex empleado no encontrado, no se puede reingresar.");
            return null;
        }
        if (buscarPorNumero(numeroEmpleado) != null) {
            System.out.println(" El empleado " + ex.getNombre() + " ya está activo.");
            return null;
        }
        Empleado reingresado = ex.toEmpleado();
        listaExEmpleados.remove(ex);
        listaEmpleados.add(reingresado);
        System.out.println("✔ Empleado reingresado: " + reingresado.getNombre());
        return reingresado;
    }

    /**
     * Igual que reingresarExEmpleado(int), pero permite
     * cambiar el puesto, el horario y el jefe directo
     * al reingresar (por ejemplo, si vuelve en otro puesto).
     */
    public Empleado reingresarExEmpleado(int numeroEmpleado, String nuevoPuesto,
                                         String nuevoHorario, String nuevoJefeDirecto) {
        ExEmpleado ex = buscarExPorNumero(numeroEmpleado);
        if (ex == null) {
            System.out.println(" Ex empleado no encontrado, no se puede reingresar.");
            return null;
        }
        if (buscarPorNumero(numeroEmpleado) != null) {
            System.out.println(" El empleado " + ex.getNombre() + " ya está activo.");
            return null;
        }
        Empleado reingresado = ex.toEmpleado(nuevoPuesto, nuevoHorario, nuevoJefeDirecto);
        listaExEmpleados.remove(ex);
        listaEmpleados.add(reingresado);
        System.out.println("✔ Empleado reingresado: " + reingresado.getNombre()
                           + " como " + nuevoPuesto);
        return reingresado;
    }
}
