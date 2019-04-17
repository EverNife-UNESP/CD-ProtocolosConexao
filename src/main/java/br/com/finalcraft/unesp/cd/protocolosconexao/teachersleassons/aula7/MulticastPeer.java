package br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula7;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.Scanner;

public class MulticastPeer {

    /*
     * Ele manda uma mensagem e fica esperando mais uma mensagem, a primeira mensagem que elçe recebe é dele próprio, a segunda mensagem é de quem mandar!
     */

    public static void main(String args[]){
        // args give message contents & destination multicast group (e.g. "228.5.6.7")]

        String message;
        if (args.length > 1){
            message = args[0];
        }else {
            System.out.println("Insira a mensagem meu friend!");
            Scanner input = new Scanner(System.in);
            message =  input.nextLine();
        }


        MulticastSocket s =null;
        try {
            InetAddress group = InetAddress.getByName("228.5.6.7");//(args[1]);
            s = new MulticastSocket();
            System.out.println("My local port is: " + s.getLocalPort());
            System.out.println("My port is: " + s.getPort());

            System.out.println("Time to Live: " + s.getTimeToLive()); // TTL
            s.setTimeToLive(0);  // restringindo ao host
            s.joinGroup(group);
            byte [] m = message.getBytes(); // mens passada em linha de comando
            DatagramPacket messageOut =
                    new DatagramPacket(m, m.length, group, 6789);
            s.send(messageOut);

            // get messages from others in group
            for(int i=0; i< 2; i++) {
                byte[] buffer = new byte[1000];

                DatagramPacket messageIn =
                        new DatagramPacket(buffer, buffer.length);
                s.receive(messageIn);
                System.out.println("Received:" + new String(messageIn.getData()));
                System.out.println("TTL: " + s.getTimeToLive()); // TTL
            }
            s.leaveGroup(group);
        } catch (SocketException e){System.out.println("Socket: " + e.getMessage());
        } catch (IOException e){System.out.println("IO: " + e.getMessage());
        } finally {if(s != null) s.close();}
    }



}
