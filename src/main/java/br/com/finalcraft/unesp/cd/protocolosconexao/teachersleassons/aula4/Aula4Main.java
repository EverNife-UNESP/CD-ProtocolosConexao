package br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula4;

import br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula4.protocols.tcp.ClientSideTCP;
import br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula4.protocols.tcp.ServerSideTCP;
import br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula4.protocols.udp.ClientSideUDP;
import br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula4.protocols.udp.ServerSideUDP;

import java.util.Scanner;

public class Aula4Main {

    public static void initialize() {

        System.out.println("Iniciando protocolo TCP\n\n\n");



        Scanner input = new Scanner(System.in);

        int method;
        while (true){
            System.out.println("Rodar o programa como?");
            System.out.println("[1] CleintTCP1");
            System.out.println("[2] ClienteUDP");
            System.out.println("[5] Servidor\n\n   --> ");

            method = input.nextInt();

            switch (method){
                case 5:
                    ServerSideUDP.initialize();
                    ServerSideTCP.initialize();
                    return;
                case 2:
                    ClientSideUDP.initialize();
                    return;
                case 1:
                    ClientSideTCP.initialize();
                    return;
            }
        }
    }



}
