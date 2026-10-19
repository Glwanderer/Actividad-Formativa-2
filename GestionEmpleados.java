package com.empresa;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * CLASE: GestionEmpleados
 * ------------------------------------------------------------
 * PROPÓSITO:
 * Administra una colección (lista) de objetos Empleado
 * CONCEPTOS APLICADOS:
 *  Atributo de tipo colección (ArrayList<Empleado>)
 *  Constructor que inicializa la colección
 *  Métodos con retorno y parámetros
 *  Instanciación de objetos Empleado dentro de la clase
 * ============================================================
 */
public class GestionEmpleados {

    // ---------- ATRIBUTO ----------
    /** Lista dinámica que almacena  los empleados */
    private List<Empleado> listaEmpleados;

    // ---------- CONSTRUCTOR ----------
    /**
     * Inicializa  cada vez que se crea un
     * gestor de empleados.
     */
    public GestionEmpleados() {
        this.listaEmpleados = new ArrayList<>();
    }

    // ---------- MÉTODOS DE GESTIÓN ----------

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
            System.out.println("✔ Empleado eliminado: " + e.getNombre());
            return true;
        }
        System.out.println("✘ Empleado no encontrado.");
        return false;
    }

    /** Devuelve la cantidad total de empleados registrados */
    public int totalEmpleados() {
        return listaEmpleados.size();
    }
}