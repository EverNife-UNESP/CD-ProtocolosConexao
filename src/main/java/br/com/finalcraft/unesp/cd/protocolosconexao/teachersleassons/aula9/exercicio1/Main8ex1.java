package br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula9.exercicio1;

import br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula9.exercicio1.client.MensageiroClient;
import br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula9.exercicio1.server.MensageiroServer;

import java.util.Scanner;

public class Main8ex1 {

    public static void main(String[] args) {

        int method;
        Scanner input = new Scanner(System.in);
        while (true){

            while (true){
                System.out.println("Rodar qual programa?");
                System.out.println("[1] Cliente");
                System.out.print("[2] Servidor\n    --> ");
                method = input.nextInt();

                switch (method){
                    case 1:
                        MensageiroClient.initialize();
                        return;
                    case 2:
                        MensageiroServer.initialize();
                        return;
                    default:
                        System.out.println("Dados inseridos inválidos!");
                        continue;
                }
            }
        }

    }


}
