package br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula9.exercicio1.client;

import br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula9.exercicio1.common.Mensageiro;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MensageiroClient {

	public static void initialize() {
		try {
			Mensageiro mref = (Mensageiro) Naming.lookup( "rmi://localhost:1099/MensageiroService" );
			System.out.println( mref.lerMensagem() );
			mref.enviarMensagem( "Hello World!" );
		}
		catch( MalformedURLException e ) {
			System.out.println();
			System.out.println( "MalformedURLException: " + e.toString() );
		}
		catch( RemoteException e ) {
			System.out.println();
			System.out.println( "RemoteException: " + e.toString() );
		}
		catch( NotBoundException e ) {
			System.out.println();
			System.out.println( "NotBoundException: " + e.toString() );
		}
		catch( Exception e ) {
			System.out.println();
			System.out.println( "Exception: " + e.toString() );
		}
	}
}
