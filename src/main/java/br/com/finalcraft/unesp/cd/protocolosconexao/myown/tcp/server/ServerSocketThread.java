package br.com.finalcraft.unesp.cd.protocolosconexao.myown.tcp.server;

import br.com.finalcraft.unesp.cd.protocolosconexao.myown.tcp.common.ClientDataEnum;
import br.com.finalcraft.unesp.cd.protocolosconexao.myown.tcp.common.ServerDataEnum;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSocketThread extends Thread{

    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    Socket clientConnecting;
    boolean valid = false;

    public ServerSocketThread(Socket clientConnecting) {
        try {
            this.clientConnecting = clientConnecting;
            this.objectInputStream = new ObjectInputStream(clientConnecting.getInputStream());
            this.objectOutputStream = new ObjectOutputStream(clientConnecting.getOutputStream());
            valid = true;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String requestFromClient(ClientDataEnum clientDataEnum){
        try {
            System.out.println("Requesting from Client [" + clientDataEnum + "]");
            objectOutputStream.flush();
            objectOutputStream.writeObject(clientDataEnum);

            String serverAnswer = (String) objectInputStream.readObject();
            return serverAnswer;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean isValid() {
        return valid;
    }

    public void processRequestFromServer(ServerDataEnum serverDataEnum){
        try {
            String serverAnswer = ServerInformations.getInformationOf(serverDataEnum);
            objectOutputStream.flush();
            objectOutputStream.writeObject(serverAnswer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void info(Object message){
        System.out.println("[Thread - " + clientConnecting.getInetAddress().getHostAddress() +":" + clientConnecting.getPort() + "]: " + message.toString());
    }

    @Override
    public void run() {
        try {
            while(true) {
                Object object = objectInputStream.readObject();
                info("Objeto lido: ");
                if (object == null){
                    info("VALO NULO\n");
                    continue;
                }
                if ( !(object instanceof ServerDataEnum)){
                    info("Objeto recebido não é válido!");
                    continue;
                }
                info(object);
                ServerDataEnum serverDataEnum = (ServerDataEnum) object;
                processRequestFromServer(serverDataEnum);
            }
        }
        catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
