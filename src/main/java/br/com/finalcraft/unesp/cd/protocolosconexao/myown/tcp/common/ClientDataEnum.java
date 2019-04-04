package br.com.finalcraft.unesp.cd.protocolosconexao.myown.tcp.common;

import java.io.Serializable;

public enum ClientDataEnum implements Serializable {
    CLIENTE_NAME,
    CLIENTE_ID,
    TO_UPPERCASE;

    String message;



    ClientDataEnum(String message) {
        this.message = message;
    }

    ClientDataEnum() {
        this.message = "none";
    }


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
