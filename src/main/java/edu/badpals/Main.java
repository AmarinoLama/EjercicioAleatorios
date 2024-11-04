package edu.badpals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;


public class Main {
    public static void main(String[] args) {

        try {
            Process hijo = new ProcessBuilder("src\\aleatorioshijo.exe").start();
            BufferedReader salidaHijo = new BufferedReader(new InputStreamReader(hijo.getInputStream()));
            PrintStream entradaHijo = new PrintStream(hijo.getOutputStream());
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

            Process hijo2 = new ProcessBuilder("src\\mayusculashijo.exe").start();
            BufferedReader salidaHijo2 = new BufferedReader(new InputStreamReader(hijo2.getInputStream()));
            PrintStream entradaHijo2 = new PrintStream(hijo2.getOutputStream());

            String texto = teclado.readLine();

            do {
                if (texto.length() == 1) {
                    entradaHijo.println("a");
                    entradaHijo.flush();
                    int n = Integer.parseInt(salidaHijo.readLine());
                    System.out.println(n);
                } else {
                    entradaHijo2.println(texto);
                    entradaHijo2.flush();
                    System.out.println(salidaHijo2.readLine());
                }
                texto = teclado.readLine();
            } while (!texto.equals("fin"));


            System.out.println("Proceso finalizado, a continuación se matará al hijo y hijo2");
            hijo.destroy();
            hijo2.destroy();

        } catch (Exception e) {
            System.out.println("Error ocurrió durante la ejecución.Descripción del error:" + e.getMessage());
        }
    }
}