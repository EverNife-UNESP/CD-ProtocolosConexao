package br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula4.protocols.udp;

import br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula4.protocols.tcp.ServerSideTCP;
import br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula4.protocols.tcp.TCPThread;

import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerSideUDP extends Thread{

    public static ServerSideUDP serverSide;
    public static Thread serverHearingSide;
    public static DatagramSocket sc_Socket;
    public static List<TCPThread> TCPThreadList = new ArrayList<TCPThread>();


    public static Map<Integer,String> mapOfIPS = new HashMap<Integer, String>();

    public static void initialize(){

        try {
            sc_Socket = new DatagramSocket(12346);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }

        serverSide = new ServerSideUDP();
        serverSide.start();
        System.out.println("Iniciando SERVER_SIDE UDP");


        serverHearingSide = new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println("----------------------------------\n\nServidor ouvindo a porta " + sc_Socket.getLocalPort());
                    while (true){
                        byte[] receiveDataFromA = new byte[1024];
                        java.util.Arrays.fill(receiveDataFromA,(byte)0);

                        DatagramPacket receivePacket =
                                new DatagramPacket(receiveDataFromA, receiveDataFromA.length);

                        sc_Socket.receive(receivePacket);
                        String sentence = new String(receivePacket.getData());

                        if (!sentence.trim().equalsIgnoreCase("CONNECT")){
                            continue;
                        }

                        InetAddress iPAddress = receivePacket.getAddress();
                        String stringIP = iPAddress.getHostAddress();

                        int port = receivePacket.getPort();

                        mapOfIPS.put(port,stringIP);
                        System.out.println(stringIP + " " + port + " ---> message:  " + sentence);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        serverHearingSide.start();
    }

    @Override
    public void run() {
        try {
            while (true){
                Thread.sleep(50);

                if (ServerSideTCP.lastMessage.isEmpty()){
                    continue;
                }

                byte[] sendData = new byte[1024];
                java.util.Arrays.fill(sendData,(byte)0);
                sendData = ServerSideTCP.lastMessage.getBytes();


                final String lastMessage = ServerSideTCP.lastMessage;
                ServerSideTCP.lastMessage = "";

                for (Map.Entry<Integer, String> stringIntegerEntry : mapOfIPS.entrySet()) {
                    String targetIP = stringIntegerEntry.getValue();
                    int targetPort  = stringIntegerEntry.getKey();

                    System.out.println("TCP enviando [" + lastMessage + "] para porta: " + targetPort);
                    InetAddress iPAddress = InetAddress.getByName(targetIP);

                    DatagramPacket sendPacket =
                            new DatagramPacket(sendData, sendData.length, iPAddress, 	targetPort);  //Cliente X

                    sc_Socket.send(sendPacket);
                }
            }
        }
        catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
