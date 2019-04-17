package br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula9.defaults;

import br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula8.exercicio1.client.MensageiroClient;
import br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula8.exercicio1.server.MensageiroServer;
import br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula9.defaults.client.ComputeFat;
import br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula9.defaults.client.ComputePi;
import br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula9.defaults.serverengine.ComputeEngine;

import java.util.Scanner;

public class Main9 {


    public static void main(String[] args) {
        int method;
        Scanner input = new Scanner(System.in);
        while (true){

            while (true){
                System.out.println("Rodar qual programa?");
                System.out.println("[1] Cliente - PI");
                System.out.println("[2] Cliente - FAT");
                System.out.print("[3] Servidor\n    --> ");
                method = input.nextInt();

                switch (method){
                    case 1:
                        ComputePi.main(args);
                        return;
                    case 2:
                        ComputeFat.main(args);
                        return;
                    case 3:
                        ComputeEngine.main(args);
                        return;
                    default:
                        System.out.println("Dados inseridos inválidos!");
                        continue;
                }
            }
        }

    }
}
