package br.com.finalcraft.unesp.cd.protocolosconexao.myown.tcp.cliente;

import br.com.finalcraft.unesp.cd.protocolosconexao.myown.tcp.common.ClientDataEnum;

public class ClientInformations {


    public static String getInformationOf(ClientDataEnum clientDataEnum){

        switch (clientDataEnum){
            case CLIENTE_ID:
                return "dd63c-4466-5599-7799-6622";
            case CLIENTE_NAME:
                return "JucaPirama";
        }

        return "Error requesting serverDataEnum, maybe it is an old version running on the Server!";
    }



}
