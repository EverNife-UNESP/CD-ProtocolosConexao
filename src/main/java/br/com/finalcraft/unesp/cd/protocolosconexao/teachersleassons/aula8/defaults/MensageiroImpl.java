package br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula8.defaults;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MensageiroImpl extends UnicastRemoteObject implements Mensageiro {

	public MensageiroImpl() throws RemoteException {
		super();
	}

	public void enviarMensagem( String msg ) throws RemoteException {
		System.out.println( msg );
	}

	public String lerMensagem() throws RemoteException {
		return "This message was writtent ";
	}
}
