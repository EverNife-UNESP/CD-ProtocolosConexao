package br.com.finalcraft.unesp.cd.protocolosconexao.myown.tcp.server;

import br.com.finalcraft.unesp.cd.protocolosconexao.myown.tcp.common.ClientDataEnum;
import br.com.finalcraft.unesp.cd.protocolosconexao.myown.tcp.common.ServerDataEnum;

import java.util.Date;

public class ServerInformations {

    public static String getInformationOf(ServerDataEnum serverDataEnum){

        switch (serverDataEnum){
            case SERVER_NAME:
                return "O nome do servidor é Xamina mina Ehee!";
            case DAY_OF_TODAY:
                return "Hoje é: " + new Date() ;
            case NOME_DO_BOLUDO_DO_JAO:
                return "Boludito de Mierda!";
            case MANDAR_MENSAGEM_PRO_B:
                if (ServerSide.serverSocketThreadList.size() > 1){
                    ServerSocketThread serverSocketThread = ServerSide.serverSocketThreadList.get(1);
                    String message = serverDataEnum.getMessage();

                    ClientDataEnum clientDataEnum = ClientDataEnum.TO_UPPERCASE;
                    clientDataEnum.setMessage(message);

                    return serverSocketThread.requestFromClient(clientDataEnum);
                }
                return "No Client B Found!";
        }

        return "Error requesting serverDataEnum, maybe it is an old version running on the Server!";
    }


}
