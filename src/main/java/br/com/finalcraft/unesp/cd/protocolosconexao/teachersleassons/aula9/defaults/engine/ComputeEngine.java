/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

package br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula9.defaults.engine;

import java.net.SocketPermission;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.AccessController;
import java.security.AllPermission;
import java.security.PrivilegedAction;

import br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula9.defaults.compute.Compute;
import br.com.finalcraft.unesp.cd.protocolosconexao.teachersleassons.aula9.defaults.compute.Task;

public class ComputeEngine extends UnicastRemoteObject implements Compute {

    public ComputeEngine() throws RemoteException {
        super();
    }

    public <T> T executeTask(Task<T> t) {
        return t.execute();
    }

    public static void main(String[] args) {


        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "Compute";
            Compute engine = new ComputeEngine();
            Compute stub =engine;
            final Registry registry = LocateRegistry.getRegistry(1999);


            java.net.SocketPermission socketPermission = new SocketPermission("localhost:*","connect, resolve");
            java.net.SocketPermission socketPermission2 = new SocketPermission("localhost:*","accept");
            java.security.AllPermission allPermission = new AllPermission();
            allPermission.

            AccessController.doPrivileged(
                    new PrivilegedAction<Object>() {
                        @Override
                        public Object run() {
                            try {
                                registry.rebind(name, stub);
                                System.out.println("ComputeEngine bound");
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            return null;
                        }
                    }
            );
        } catch (Exception e) {
            System.err.println("ComputeEngine exception:");
            e.printStackTrace();
        }
    }
}
