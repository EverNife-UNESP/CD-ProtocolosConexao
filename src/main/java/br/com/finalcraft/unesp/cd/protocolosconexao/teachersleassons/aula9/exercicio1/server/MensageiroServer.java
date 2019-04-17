package br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula9.exercicio1.server;

import br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula9.exercicio1.common.Mensageiro;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class MensageiroServer {


	public static MensageiroServer mensageiroServer;

	public static void initialize(){
		mensageiroServer = new MensageiroServer();
	}

	public MensageiroServer() {
		try {
			LocateRegistry.createRegistry(1099);
			// Da uma lida nesse paranaue aqui meu confederado 
			// --> https://docs.oracle.com/javase/7/docs/technotes/tools/solaris/rmiregistry.html
			
			Mensageiro ms = new MensageiroImpl();
			Naming.rebind("rmi://localhost:1099/MensageiroService", ms);
		}
		catch( Exception e ) {
			System.out.println( "Trouble: " + e );
		}
	}

	public static void main(String[] args) {
		new MensageiroServer();
	}
}