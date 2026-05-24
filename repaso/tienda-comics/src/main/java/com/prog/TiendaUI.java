package com.prog;

import java.util.Scanner;

public class TiendaUI {
    private Gestor gestor;
    private Scanner sc;

    TiendaUI() {
        this.gestor = new Gestor();
        this.sc = new Scanner(System.in);
    }

    /*----------Limpiar----------- */
    private void cls() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void esperaIntro() {
        System.out.println("\n[Pulse intro para continuar]");
        sc.nextLine();
    }

    /*------------Menú------------ */
    public void menu() {
        int opc = 0;

        do {
            System.out.println("\n--- TIENDA DE CÓMICS ---");
            System.out.println("1. Importar nuevos cómics");
            System.out.println("2. Registrar una venta");
            System.out.println("3. Eliminar un cómic");
            System.out.println("4. Lista de cómics");
            System.out.println("5. Salir");
            System.out.print("\tSelecciona una opción: ");

            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 1:
                    addComic();
                    esperaIntro();
                    break;
                case 2:
                    updateVenta();
                    esperaIntro();
                    break;
                case 3:
                    removeComics();
                    esperaIntro();
                    break;
                case 4:
                    showComics();
                    esperaIntro();
                    break;
                case 5:
                    System.out.println("\nFinalizado correctamente.");
                    break;

                default:
                    System.out.println("\nOpción no válida");
                    esperaIntro();
                    break;
            }
        } while (opc != 5);
    }

    private void addComic() {
        //cls();
        System.out.println("\n--- Añadir cómics a la bbdd ---");
        boolean exito = gestor.importarComicDesdeFichero("nuevos_comics.txt");

        if (exito) {
            System.out.println("¡Cómics importados con éxito!");
        } else {
            System.out.println("Hubo un error al importar los cómics.");
        }
    }

    public void updateVenta() {
        //cls();
        System.out.println("\n--- Registrar una venta ---");
        System.out.print("Nombre del cómic: ");
        String nombre = sc.nextLine();

        System.out.print("¿Cuántos ejemplares?: ");
        int cantidad = Integer.parseInt(sc.nextLine());

        boolean exito = gestor.anadirVenta(nombre, cantidad);

        if (exito) {
            System.out.println("¿Datos actualizados con éxito!");
        } else {
            System.out.println("Error en el registro, no hay suficiente stock de este cómic o no existe");
        }
    }

    public void removeComics() {
        System.out.println("\n--- Eliminar cómic ---");
        System.out.print("¿Qué cómic quieres borrar?: ");
        String nombre = sc.nextLine();

        boolean exito = gestor.eliminarComic(nombre);

        if (exito) {
            System.out.println("¡Cómic eliminado con éxito!");
        } else {
            System.out.println("Error al eliminar, no existe ese cómic");
        }
    }

    public void showComics() {
        System.out.println("\n--- Lista de cómics ---");

        String lista = gestor.listarComics();
        System.out.println(lista);

        System.out.println("-----------------------\n");

        System.out.println("\n--- ALERTA STOCK ---");

        String listaAlerta = gestor.agotados();
        System.out.println(listaAlerta);

        System.out.println("-----------------------");

    }
}
