package br.com.finalcraft.unesp.cd.protocolosconexao.myown.tcp.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerSide  extends Thread{

    public static ServerSide serverSide;
    public static List<ServerSocketThread> serverSocketThreadList = new ArrayList<ServerSocketThread>();

    public static void initialize(){
        serverSide = new ServerSide();
        serverSide.start();
        System.out.println("Iniciando SERVER_SIDE");
    }

    @Override
    public void run() {
        try {
            // Instancia o ServerSocket ouvindo a porta 12345
            ServerSocket servidor = new ServerSocket(12345);
            System.out.println("----------------------------------\n\nServidor ouvindo a porta " + servidor.getLocalPort());

            while (true){
                Socket clientConnecting = servidor.accept();
                ServerSocketThread serverSocketThread = new ServerSocketThread(clientConnecting);

                if (serverSocketThread.isValid()){
                    serverSocketThread.start();
                    serverSocketThreadList.add(serverSocketThread);
                }
            }
        }
        catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
