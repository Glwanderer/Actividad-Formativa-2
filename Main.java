package com.empresa;

/**
 * ============================================================
 * CLASE: Main
 * ------------------------------------------------------------
 * PROPÓSITO:
 *  Aquí se instancian los
 * objetos y se prueban todos los métodos implementados.
 *
 * CONCEPTOS APLICADOS:
 *   Instanciación de objetos (operador new)
 *   Invocación de constructores (con y sin parámetros)
 *   Llamada a métodos y setters/getters
 *   Prueba funcional del sistema
 * ============================================================
 */
public class Main {

    public static void main(String[] args) {

        // 1️⃣ Instanciación del gestor (objeto de la clase GestionEmpleados)
        GestionEmpleados gestor = new GestionEmpleados();

        // 2️⃣ Instanciación de empleados usando el CONSTRUCTOR CON PARÁMETROS
        Empleado emp1 = new Empleado(
                101, "Mayra Guardiola", "IT Manager",
                "555-1234", "09:00 - 18:00", "Zelda Contreras", 30
        );

        Empleado emp2 = new Empleado(
                102, "Luis Contreras", "IT Support Senior",
                "555-5678", "08:00 - 17:00", "MAyra Guardiola", 8
        );

        // 3️⃣ Instanciación usando el CONSTRUCTOR VACÍO + SETTERS
        Empleado emp3 = new Empleado();
        emp3.setNumeroEmpleado(103);
        emp3.setNombre("María López");
        emp3.setPuesto("Desarrolladora Java");
        emp3.setTelefono("555-9012");
        emp3.setHorario("10:00 - 19:00");
        emp3.setJefeDirecto("Ana Torres");
        emp3.setMesesEnPuesto(14);

        // 4️⃣ Agregar empleados al gestor
        gestor.agregarEmpleado(emp1);
        gestor.agregarEmpleado(emp2);
        gestor.agregarEmpleado(emp3);

        // 5️⃣ Probar métodos de negocio
        System.out.println("\n--- Información individual ---");
        emp1.mostrarInfo();
        emp3.mostrarInfo();

        System.out.println("Antigüedad de " + emp3.getNombre()
                + ": " + emp3.calcularAniosEnPuesto() + " año(s)");

        // 6️⃣ Listar todos
        gestor.listarEmpleados();
        System.out.println("Total de empleados: " + gestor.totalEmpleados());

        // 7️⃣ Buscar por número
        System.out.println("\n--- Búsqueda del empleado 102 ---");
        Empleado encontrado = gestor.buscarPorNumero(102);
        if (encontrado != null) {
            encontrado.mostrarInfo();
        }

        // 8️⃣ Eliminar uno y verificar
        System.out.println("\n--- Eliminación del empleado 102 ---");
        gestor.eliminarEmpleado(102);
        System.out.println("Total ahora: " + gestor.totalEmpleados());
    }
}