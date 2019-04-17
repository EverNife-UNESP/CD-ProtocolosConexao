package br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula8.defaults;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;

public class MensageiroClient {

	public static void main( String args[] ) {
		try {
			Mensageiro mref = (Mensageiro) Naming.lookup( "rmi://localhost:1099/MensageiroService" );
			System.out.println( mref.lerMensagem() );
			mref.enviarMensagem( "Essa mensagem foi feito no lado do cliente" );
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
