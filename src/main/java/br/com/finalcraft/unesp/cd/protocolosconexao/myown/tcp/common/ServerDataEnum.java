package br.com.finalcraft.unesp.cd.protocolosconexao.myown.tcp.common;

import java.io.Serializable;

public enum ServerDataEnum implements Serializable {

    SERVER_NAME,
    DAY_OF_TODAY,
    NOME_DO_BOLUDO_DO_JAO,
    MANDAR_MENSAGEM_PRO_B,
    MANDAR_MENSAGEM_PRO_A;

    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "[" + this.name() + " message: " + this.message + "]";
    }
}
