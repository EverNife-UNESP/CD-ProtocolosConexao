package br.com.finalcraft.unesp.cd.protocolosconexao.myown.tcp;

import br.com.finalcraft.unesp.cd.protocolosconexao.myown.tcp.cliente.ClientSide;
import br.com.finalcraft.unesp.cd.protocolosconexao.myown.tcp.server.ServerSide;

import java.util.Scanner;

public class TCPMain {

    public static void initialize() {

        System.out.println("Iniciando protocolo TCP\n\n\n");

        System.out.println("Rodar o programa como?");
        System.out.println("[1] Cliente1");
        System.out.println("[2] Cliente2");
        System.out.println("[3] Servidor\n\n   --> ");

        Scanner input = new Scanner(System.in);

        int method;

        do {
            method = input.nextInt();
        }while (method != 1 && method != 2 && method != 3);

        if (method == 3){
            ServerSide.initialize();
        }else if (method == 2){
            ClientSide.initialize(2);
        }else {
            ClientSide.initialize(1);
        }
    }
}