package br.com.finalcraft.unesp.cd.protocolosconexao.myown.udp;

import br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula1.Aula1;

import java.util.Scanner;

public class UDPMain {

    public static void initialize() {

        System.out.println("Iniciando protocolo UDP\n\n\n");
        
        System.out.println("Rodar o programa como?");
        System.out.println("[1] Cliente A");
        System.out.println("[2] Cliente B");
        System.out.println("[3] Cliente C");

        Scanner input = new Scanner(System.in);

        int method;

        do {
            method = input.nextInt();
        }while (method != 1 && method != 2 && method != 3);


        switch (method){
            case 1:
                Aula1.clienteA();
                return;
            case 2:
                Aula1.clienteB();
                return;
            case 3:
                Aula1.clienteC();
                return;
        }
    }



}