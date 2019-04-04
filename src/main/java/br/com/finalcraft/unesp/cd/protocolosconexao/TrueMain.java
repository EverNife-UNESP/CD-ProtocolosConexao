package br.com.finalcraft.unesp.cd.protocolosconexao;

import br.com.finalcraft.unesp.cd.protocolosconexao.myown.tcp.TCPMain;
import br.com.finalcraft.unesp.cd.protocolosconexao.myown.udp.UDPMain;
import br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula4.Aula4Main;

import java.util.Scanner;

public class TrueMain {

    public static void main(String[] args) {

        int method;


        Scanner input = new Scanner(System.in);

        while (true){

            System.out.println("Rodar qual programa?");
            System.out.println("[1] TCP");
            System.out.println("[2] UDP");
            System.out.println("[4] Aula4\n    --> ");


            method = input.nextInt();

            switch (method){
                case 1:
                    TCPMain.initialize();
                    return;
                case 2:
                    UDPMain.initialize();
                    return;
                case 4:
                    Aula4Main.initialize();
                    return;
            }

            System.out.println("Meu amigo... qual a dificuldade de digitar corretamnete?");
        }

    }
}