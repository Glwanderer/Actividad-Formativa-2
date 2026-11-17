package com.empresa;

/**
 * ============================================================
 * CLASE: Main
 * ------------------------------------------------------------
 * PROPÓSITO:
 *  Aquí se instancian los
 * objetos y se prueban todos los métodos implementados,
 * incluyendo la herencia: ExEmpleado hereda de Empleado.
 *
 * CONCEPTOS APLICADOS:
 *   Instanciación de objetos (operador new)
 *   Constructores (con y sin parámetros)
 *   Llamada a métodos Set y get
 *   Herencia y polimorfismo (ExEmpleado ES UN Empleado,
 *   por eso mostrarInfo() muestra también los datos de baja)
 *   Prueba funcional del sistema
 * ============================================================
 */
public class Main {

    public static void main(String[] args) {

        //  Instanciación del gestor (objeto de la clase GestionEmpleados)
        GestionEmpleados gestor = new GestionEmpleados();

        //  Instanciación de empleados usando el CONSTRUCTOR CON PARÁMETROS
        Empleado emp1 = new Empleado(
                101, "Mayra Guardiola", "IT Manager",
                "555-1234", "09:00 - 18:00", "Zelda Contreras", 30
        );

        Empleado emp2 = new Empleado(
                102, "Luis Contreras", "IT Support Senior",
                "555-5678", "08:00 - 17:00", "MAyra Guardiola", 8
        );

        //  Instanciación usando el CONSTRUCTOR VACÍO + SETTERS
        Empleado emp3 = new Empleado();
        emp3.setNumeroEmpleado(103);
        emp3.setNombre("María López");
        emp3.setPuesto("Desarrolladora Java");
        emp3.setTelefono("555-9012");
        emp3.setHorario("10:00 - 19:00");
        emp3.setJefeDirecto("Ana Torres");
        emp3.setMesesEnPuesto(14);

        //  Agregar empleados al gestor
        gestor.agregarEmpleado(emp1);
        gestor.agregarEmpleado(emp2);
        gestor.agregarEmpleado(emp3);

        //  Probar métodos de negocio
        System.out.println("\n--- Información individual ---");
        emp1.mostrarInfo();
        emp3.mostrarInfo();

        System.out.println("Antigüedad de " + emp3.getNombre()
                + ": " + emp3.calcularAniosEnPuesto() + " año(s)");

        // Listar todos
        gestor.listarEmpleados();
        System.out.println("Total de empleados: " + gestor.totalEmpleados());

        // Buscar por número
        System.out.println("\n--- Búsqueda del empleado 102 ---");
        Empleado encontrado = gestor.buscarPorNumero(102);
        if (encontrado != null) {
            encontrado.mostrarInfo();
        }

        // ============================================================
        //  HERENCIA: ExEmpleado
        // ============================================================

        //  Ex empleado creado directamente con el CONSTRUCTOR
        //  CON PARÁMETROS (los 7 primeros van a Empleado con super()).
        //  Fíjate: NO se vuelven a declarar numeroEmpleado, nombre,
        //  puesto, etc., se heredan.
        ExEmpleado ex1 = new ExEmpleado(
                104, "Carlos Ruiz", "Analista de Soporte",
                "555-3456", "08:00 - 17:00", "Mayra Guardiola", 24,
                "2026-10-31", "Renuncia por nuevo empleo", "Renuncia"
        );
        gestor.agregarExEmpleado(ex1);

        //  Ex empleado con el CONSTRUCTOR VACÍO + SETTERS.
        //  Los setters de nombre, puesto, etc. son los heredados
        //  de Empleado; solo fecha/motivo/tipo son propios.
        ExEmpleado ex2 = new ExEmpleado();
        ex2.setNumeroEmpleado(105);
        ex2.setNombre("Ana Torres");
        ex2.setPuesto("Gerente de Proyectos");
        ex2.setTelefono("555-7890");
        ex2.setHorario("09:00 - 18:00");
        ex2.setJefeDirecto("Zelda Contreras");
        ex2.setMesesEnPuesto(48);
        ex2.setFechaBaja("2026-09-15");
        ex2.setMotivoBaja("Jubilación por edad");
        ex2.setTipoBaja("Jubilación");
        gestor.agregarExEmpleado(ex2);

        //  Dar de baja a un empleado ACTIVO (el 102).
        //  darDeBaja() lo convierte en ExEmpleado conservando
        //  todos sus datos, lo quita de activos y lo pasa a
        //  la lista de ex empleados.
        System.out.println("\n--- Dar de baja al empleado 102 ---");
        ExEmpleado exDeBaja = gestor.darDeBaja(
                102, "2026-11-15", "Fin de contrato por proyecto", "Fin de contrato"
        );
        if (exDeBaja != null) {
            exDeBaja.mostrarInfo();
            System.out.println("¿Baja voluntaria? " + (exDeBaja.esBajaVoluntaria() ? "Sí" : "No"));
        }

        //  Listas finales: activos y ex empleados
        gestor.listarEmpleados();
        System.out.println("Total de empleados activos: " + gestor.totalEmpleados());

        gestor.listarExEmpleados();
        System.out.println("Total de ex empleados: " + gestor.totalExEmpleados());

        //  Buscar un ex empleado por número
        System.out.println("\n--- Búsqueda del ex empleado 104 ---");
        ExEmpleado exEncontrado = gestor.buscarExPorNumero(104);
        if (exEncontrado != null) {
            exEncontrado.mostrarInfo();
        }

        //  Polimorfismo: un ExEmpleado también ES UN Empleado,
        //  así que sus métodos heredados funcionan igual.
        System.out.println("Antigüedad de " + ex1.getNombre()
                + ": " + ex1.calcularAniosEnPuesto() + " año(s)");
        System.out.println(ex1.toString());

        // ============================================================
        //  FILTRO POR TIPO DE BAJA
        // ============================================================

        //  Solo los que renunciaron: aquí solo Carlos Ruiz (104).
        gestor.listarExPorTipoBaja("Renuncia");
        System.out.println("Ex empleados por renuncia: "
                + gestor.contarExPorTipoBaja("Renuncia"));

        //  Sin coincidencias: avisa que no hay ninguno.
        gestor.listarExPorTipoBaja("Despido");

        // ============================================================
        //  REINGRESO
        // ============================================================

        //  Carlos Ruiz (104) vuelve, ahora como Analista Senior.
        //  Su antigüedad se reinicia a 0 meses y sale de la
        //  lista de ex empleados.
        System.out.println("\n--- Reingreso del ex empleado 104 ---");
        Empleado reingresado = gestor.reingresarExEmpleado(
                104, "Analista de Soporte Senior", "08:00 - 17:00", "Mayra Guardiola"
        );
        if (reingresado != null) {
            reingresado.mostrarInfo();
        }

        //  Intentar reingresarlo otra vez ya no funciona:
        //  ya no está en la lista de ex empleados.
        System.out.println("\n--- Intentar reingresar de nuevo al 104 ---");
        gestor.reingresarExEmpleado(104);

        //  Después del reingreso ya no hay ex empleados por renuncia.
        gestor.listarExPorTipoBaja("Renuncia");

        //  Listas finales
        gestor.listarEmpleados();
        System.out.println("Total de empleados activos: " + gestor.totalEmpleados());

        gestor.listarExEmpleados();
        System.out.println("Total de ex empleados: " + gestor.totalExEmpleados());
    }
}
