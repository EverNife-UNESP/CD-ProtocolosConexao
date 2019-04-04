package br.com.finalcraft.unesp.cd.protocolosconexao.javafx.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class MyFXMLs {

    public static Parent main_screen;

    public static void initialize() throws IOException {
        main_screen = FXMLLoader.load(MyFXMLs.class.getResource("/assets/tc_maquina_norma.fxml"));
    }

}
