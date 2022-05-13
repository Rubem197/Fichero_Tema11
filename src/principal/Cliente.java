package principal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente implements Cartas {

    private int numCliente;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private int saldo;
    private int ingresosMedios;
    private int gastosMedios;
    private String direccion;
    private int codigoPostal;

    public static List<Cliente> listaCliente = new ArrayList<>();
    public static List<Cliente> listaClienteSaldo0 = new ArrayList<>();
    public static List<Cliente> listaClienteCredito = new ArrayList<>();
    public static List<Cliente> listaClienteDebito = new ArrayList<>();
    public static List<Cliente> listaClienteRobinson = new ArrayList<>();
    public static List<Cliente> listaClienteVips = new ArrayList<>();

    public static String ficheroClienteSaldo0 = "Clientesaldo0.txt";
    public static String ficheroClienteCredito = "ClienteCredito.txt";
    public static String ficheroClienteDebito = "ClienteDebito.txt";
    public static String ficheroClienteRobinson = "ClienteRobinson.txt";
    public static String ficheroClienteVips = "ClienteVips.txt";

    public Cliente() {
    }
    public Cliente(int numCliente, String nombre, String apellido1, String apellido2, int saldo,
                   int ingresosMedios, int gastosMedios, String direccion, int codigoPostal) {
        this.numCliente = numCliente;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.saldo = saldo;
        this.ingresosMedios = ingresosMedios;
        this.gastosMedios = gastosMedios;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
    }

    public void menu() {

        Scanner opcion = new Scanner(System.in);
        int opc;

        System.out.println("Introduzca la opción que desee:");
        System.out.println("1. Lista clientes con saldo 0");
        System.out.println("2. Lista clientes con saldo crédito");
        System.out.println("3. Lista clientes con saldo débito");
        System.out.println("4. Lista clientes Robinson");
        System.out.println("5. Lista clientes super Vips");
        System.out.println("6. Generar cartas a los clientes Robinson");
        System.out.println("7. Generar cartas a los clientes super Vips");
        System.out.println("0. Salir");
        opc = opcion.nextInt();
        generarLista();
        while (opc != 0) {
            switch (opc) {
                case 1:
                    generarListaClientesSaldo0();
                    insertarLista(listaClienteSaldo0, ficheroClienteSaldo0);
                    break;
                case 2:
                    generarListaClientesCredito();
                    insertarLista(listaClienteCredito, ficheroClienteCredito);
                    break;
                case 3:
                    generarListaClientesDebito();
                    insertarLista(listaClienteDebito, ficheroClienteDebito);
                    break;
                case 4:
                    generarListaClientesRobinson();
                    insertarLista(listaClienteRobinson, ficheroClienteRobinson);
                    break;
                case 5:
                    generarListaClientesVips();
                    insertarLista(listaClienteVips, ficheroClienteVips);
                    break;
                case 6:
                    generarListaClientesRobinson();
                    cartaRobinson(listaClienteRobinson);
                    break;
                case 7:
                    generarListaClientesVips();
                    cartaRobinson(listaClienteVips);
                    break;
                default:
                    System.out.println("Opción equivocada");
            }
            System.out.println("Seleccione otra opción");
            opc = opcion.nextInt();
        }
    }

    public static List<Cliente> generarLista() {
        String text = "Clientes.txt";
        String linea;
        Scanner sc;
        BufferedReader buffer1 = null;
        String codigo = "";
        String[] parte;

        try {

            buffer1 = new BufferedReader(new FileReader(text));

            while ((linea = buffer1.readLine()) != null) {

                sc = new Scanner(linea);

                codigo += sc.nextLine();
                parte = codigo.split(" ");

                listaCliente.add(new Cliente(Integer.parseInt(parte[0]), parte[1], parte[2], parte[3],
                        Integer.parseInt(parte[4]), Integer.parseInt(parte[5]), Integer.parseInt(parte[6]),
                        parte[7], Integer.parseInt(parte[8])));

                codigo = "";

            }

        } catch (Exception e) {

            System.out.println("Algó falló");

        } finally {
            try {
                buffer1.close();
            } catch (Exception fef) {
                System.out.println("error");
            }
        }

        return listaCliente;
    }

    public static List<Cliente> generarListaClientesSaldo0() {
        int contador = 0;

        while (contador < listaCliente.size()) {

            if ((listaCliente.get(contador).getSaldo()) == 0) {

                if (!listaClienteSaldo0.contains(listaCliente.get(contador))) {
                    listaClienteSaldo0.add(listaCliente.get(contador));
                }
            }
            contador++;
        }

        return listaClienteSaldo0;
    }


    public static List<Cliente> generarListaClientesCredito() {
        int contador = 0;

        while (contador < listaCliente.size()) {

            if (listaCliente.get(contador).getSaldo() < 0 && listaCliente.get(contador).getSaldo() > -3000) {

                if (!listaClienteCredito.contains(listaCliente.get(contador))) {
                    listaClienteCredito.add(listaCliente.get(contador));
                }
            }
            contador++;
        }

        return listaClienteCredito;
    }


    public static List<Cliente> generarListaClientesDebito() {
        int contador = 0;

        while (contador < listaCliente.size()) {

            if (listaCliente.get(contador).getSaldo() > 0 && listaCliente.get(contador).getSaldo() < 3000) {

                if (!listaClienteDebito.contains(listaCliente.get(contador))) {
                    listaClienteDebito.add(listaCliente.get(contador));
                }
            }

            contador++;
        }

        return listaClienteDebito;
    }

    public static List<Cliente> generarListaClientesRobinson() {
        int contador = 0;

        while (contador < listaCliente.size()) {

            if (listaCliente.get(contador).getSaldo() > 3000) {

                if (!listaClienteRobinson.contains(listaCliente.get(contador))) {
                    listaClienteRobinson.add(listaCliente.get(contador));
                }
            }

            contador++;
        }

        return listaClienteRobinson;
    }

    public static List<Cliente> generarListaClientesVips() {
        int contador = 0;

        while (contador < listaCliente.size()) {

            if (listaCliente.get(contador).getSaldo() < -3000) {

                if (!listaClienteVips.contains(listaCliente.get(contador))) {
                    listaClienteVips.add(listaCliente.get(contador));
                }
            }

            contador++;
        }

        return listaClienteVips;
    }

    private static void insertarLista(List<Cliente> listaCliente1, String fichero) {
        BufferedWriter buffW;
        try {

            buffW = new BufferedWriter(new FileWriter(fichero));

            for (int i = 0; i < listaCliente1.size(); i++) {

                buffW.append(listaCliente1.get(i).toString());
                buffW.write("\n");

            }
            buffW.close();

        } catch (Exception e) {

            System.out.println("Error");
        }
    }

    @Override
    public void cartaRobinson(List<Cliente> listaCliente1) {

        for (int i = 0; i < listaCliente1.size(); i++) {
            System.out.println("Se ha generado la carta de "+ listaCliente1.get(i).getNombre());
        }
    }

    @Override
    public void cartaVips(List<Cliente> listaCliente1) {

        for (int i = 0; i < listaCliente1.size(); i++) {
            System.out.println("Se ha generado la carta "+ listaCliente1.get(i).getNombre());
        }
    }

    @Override
    public String toString() {

        return "Código: " + numCliente + " Nombre: " + nombre + " PrimerApellido: " + apellido1 + " SegundoApellido: " +
                apellido2 + " Saldo: " + saldo + " IngresosMedio: " + ingresosMedios + " GastosMedios: " + gastosMedios;

    }
    public String getNombre() {
        return nombre;
    }

    public int getSaldo() {
        return saldo;
    }
}