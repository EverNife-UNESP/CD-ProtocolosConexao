package br.com.finalcraft.unesp.cd.protocolosconexao.myown.tcp.cliente;

import br.com.finalcraft.unesp.cd.protocolosconexao.myown.tcp.common.ServerDataEnum;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSide extends Thread {

    public static ClientSide clientSide;
    public static int ID;
    public static void initialize(int id){
        ID = id;
        clientSide = new ClientSide();
        clientSide.start();

        System.out.println("Iniciando CLIENT_SIDE");
    }

    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;



    public String requestFromServer(ServerDataEnum serverDataEnum){
        try {
            System.out.println("Requesting from Server [" + serverDataEnum + "]");
            objectOutputStream.flush();
            objectOutputStream.writeObject(serverDataEnum);

            String serverAnswer = (String) objectInputStream.readObject();
            return serverAnswer;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void sendToServer(ServerDataEnum serverDataEnum){
        try {
            System.out.println("Requesting from Server [" + serverDataEnum + "]");
            objectOutputStream.flush();
            objectOutputStream.writeObject(serverDataEnum);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            // Inicia conexão com o servidor
            Socket serverSocket = new Socket("localhost",12345);
            objectOutputStream = new ObjectOutputStream(serverSocket.getOutputStream());
            objectInputStream = new ObjectInputStream(serverSocket.getInputStream());
            //objectInputStream = new ObjectInputStream(serverSocket.getInputStream());
            System.out.println("Conectando ao servidor [" + serverSocket.getInetAddress().getHostAddress() + "]");

            System.out.println("\n\n");

            if (ID == 1){
                ServerDataEnum serverDataEnum = ServerDataEnum.MANDAR_MENSAGEM_PRO_B;
                serverDataEnum.setMessage("Chove Chove chuvaaaaa! :D %client-IP%");

                String serverAnswer1 = requestFromServer(serverDataEnum);

                System.out.println("A respota do B é: " + serverAnswer1);
            }else {
                while(true) {
                    Object object = objectInputStream.readObject();
                    System.out.println("Objeto lido: ");
                    if (object == null){
                        System.out.println("VALO NULO\n");
                        continue;
                    }
                    if ( !(object instanceof ServerDataEnum)){
                        System.out.println("Objeto recebido não é válido!");
                        continue;
                    }
                    System.out.println(object);
                    ServerDataEnum serverDataEnum = (ServerDataEnum) object;
                    String message = serverDataEnum.getMessage().toUpperCase();
                    serverDataEnum = ServerDataEnum.MANDAR_MENSAGEM_PRO_A;
                    serverDataEnum.setMessage(message);
                    sendToServer(serverDataEnum);
                }
            }


            objectOutputStream.close();

        }catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        System.out.println("Forte abraço!");
    }

}
