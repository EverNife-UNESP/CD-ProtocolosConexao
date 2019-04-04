package br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Aula2 {

    public static void clienteA(){
        try {
            // papel 1: client           (o Sever deve estar ativado)

            BufferedReader inFromUser =           // teclado
                    new BufferedReader(new InputStreamReader(System.in));

            DatagramSocket cs_Socket = new DatagramSocket(9991);

            InetAddress iPAddress = 	InetAddress.getByName("localhost");

            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];

            System.out.println("Papel Client ... digite a mensagem ");
            String sentence = inFromUser.readLine(); // leitura da mensagem do teclado
            java.util.Arrays.fill(sendData,(byte)0);
            sendData = sentence.getBytes();
            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, iPAddress, 	9992);  //Cliente B

            cs_Socket.send(sendPacket);


            byte[] receiveDataFromC = new byte[1024];
            System.out.println("Cliente A... esperando mensagem de C");
            java.util.Arrays.fill(receiveDataFromC,(byte)0);
            DatagramPacket receivePacket2 =
                    new DatagramPacket(receiveDataFromC, receiveDataFromC.length);
            cs_Socket.receive(receivePacket2);
            String sentence2 = new String(receivePacket2.getData());
            System.out.println("Mensagem final é:   " + sentence2);


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void clienteB(){
        try {
            DatagramSocket sc_Socket = new DatagramSocket(9992);

            byte[] receiveDataFromA = new byte[1024];

            System.out.println("Cliente B... esperando mensagem");
            java.util.Arrays.fill(receiveDataFromA,(byte)0);
            DatagramPacket receivePacket =
                    new DatagramPacket(receiveDataFromA, receiveDataFromA.length);
            sc_Socket.receive(receivePacket);
            String sentence = new String(receivePacket.getData());

            InetAddress iPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            System.out.println(iPAddress.getHostAddress() + " " + port);

            String capitalizedSentence = sentence.toUpperCase();
            // inversao de papel : client




            BufferedReader inFromUser =           // teclado
                    new BufferedReader(new InputStreamReader(System.in));

            InetAddress iPAddress2 = 	InetAddress.getByName("localhost");

            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];

            java.util.Arrays.fill(sendData,(byte)0);
            sendData = capitalizedSentence.getBytes();
            DatagramPacket sendPacket2 =
                    new DatagramPacket(sendData, sendData.length, iPAddress2, 	9993);  //Cliente B

            sc_Socket.send(sendPacket2);

            java.util.Arrays.fill(receiveData,(byte)0);
            DatagramPacket receivePacket2 =
                    new DatagramPacket(receiveData, receiveData.length);

            sc_Socket.receive(receivePacket2);

            String modifiedSentence =
                    new String(receivePacket2.getData());

            System.out.println("retorno do Server: " + modifiedSentence);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void clienteC(){
        try {
            DatagramSocket sc_Socket = new DatagramSocket(9993);

            byte[] receiveDataFromA = new byte[1024];
            byte[] sendDataToB  = new byte[1024];

            System.out.println("Cliente C... esperando mensagem");
            java.util.Arrays.fill(receiveDataFromA,(byte)0);
            DatagramPacket receivePacket =
                    new DatagramPacket(receiveDataFromA, receiveDataFromA.length);
            sc_Socket.receive(receivePacket);
            String sentence = new String(receivePacket.getData());

            InetAddress iPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            System.out.println(iPAddress.getHostAddress() + " " + port);

            java.util.Arrays.fill(sendDataToB,(byte)0);
            sendDataToB = "vlw aew parça, vo mandar lá para o A!".getBytes();
            System.out.println("Recebi a mensagem do B, vo mandar lá para o A!");

            DatagramPacket sendPacket =
                    new DatagramPacket(sendDataToB, sendDataToB.length, iPAddress,
                            port);

            sc_Socket.send(sendPacket);
            // inversao de papel : client




            BufferedReader inFromUser =           // teclado
                    new BufferedReader(new InputStreamReader(System.in));

            InetAddress iPAddress2 = 	InetAddress.getByName("localhost");

            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];

            java.util.Arrays.fill(sendData,(byte)0);
            sendData = sentence.getBytes();
            DatagramPacket sendPacket2 =
                    new DatagramPacket(sendData, sendData.length, iPAddress2, 	9991);  //Cliente B

            sc_Socket.send(sendPacket2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
