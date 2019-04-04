package br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula4.protocols.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientSideUDP extends Thread{

    public static ClientSideUDP clientSide;

    public static int ID;

    public static void initialize(){
        clientSide = new ClientSideUDP();
        clientSide.start();
        System.out.println("Iniciando CLIENT_SIDE");
    }

    @Override
    public void run() {
        try {
            DatagramSocket sc_Socket = new DatagramSocket();    //Porta aleatória
            ID = sc_Socket.getLocalPort();  //Porta aleatória


            System.out.println("Cliente [" + ID + "]... enviando requisição de conexão...");
            //Enviando requisição de conexão
            byte[] sendData = new byte[1024];
            java.util.Arrays.fill(sendData,(byte)0);
            sendData = "CONNECT".getBytes();

            InetAddress serverIPAddress = InetAddress.getByName("localhost");
            int serverPort  = 12346;

            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, serverIPAddress, 	serverPort);  //Cliente X

            sc_Socket.send(sendPacket);


            System.out.println("Cliente [" + ID + "]... esperando mensagens...");
            while(true){
                byte[] receiveDataFromA = new byte[1024];
                java.util.Arrays.fill(receiveDataFromA,(byte)0);

                DatagramPacket receivePacket =
                        new DatagramPacket(receiveDataFromA, receiveDataFromA.length);

                sc_Socket.receive(receivePacket);
                String sentence = new String(receivePacket.getData());

                if (sentence.trim().equalsIgnoreCase("SHUTDOWN")){
                    System.out.println("Shutdown was executed on TCPServer... by my friends!");
                    return;
                }

                InetAddress iPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();

                System.out.println(iPAddress.getHostAddress() + " " + port + " ---> message:  " + sentence);
            }
        }
        catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
