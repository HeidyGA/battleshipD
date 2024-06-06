package org.example.battleshipd.controller;

import org.example.battleshipd.view.GameStage;
import org.example.battleshipd.view.WelcomeStage;
import org.example.battleshipd.view.alert.AlertBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class WelcomeController {
    @FXML
    void buttonStart(ActionEvent event) throws IOException {
        GameStage.getInstance().getGameController().inicialize();
        WelcomeStage.deleteInstance();
    }

    @FXML
    void buttonRules(ActionEvent event) {
        String title = "¿Como jugar?";
        String header = "Rules";
        String content = " • Cada jugador tiene 2 tableros de 10x10: \n"+
                "\n•Tablero de jugador: En él se distribuye tu flota antes de comenzar la partida y sólo será de observación. \n"+
                "\n• Tablero del enemigo: Se desplega su flota. Será aquí donde se desarrollen tus movimientos tratando de hundir los barcos enemigos. Este tablero aparecerá en tu pantalla  una vez comience la partida y en él quedarán registrados todos sus movimientos realizados \n"+
                "\n•Contiene una flota de 9 barcos para cada jugador: \n"+
                "\n• 1 portaaviones (4 casillas) "+"\n• 2 submarinos (3 casillas cada uno)"+"\n• 3 destructores (2 casillas cada uno)"+"\n• 4 fragatas (1 casilla cada una)"+
                "\n\nMovimientos: \n\n"+
                "• Agua: Disparo en una casilla vacía. Se marca con una X y pasa el turno al enemigo. "+
                "\n• Tocado: Disparo en una parte de un barco que ocupa mas de 2 casilla. Se marca la parte tocada y vuelves a disparar. "+
                "\n• Hundido: Disparo en una fragata o un barco con todas sus partes tocadas. Se marca el barco completo y  vuelves a disparar. " +
                "\n•¡Gana quien hunda toda la flota enemiga,que comience la batalla!.";;

        new AlertBox().showMessage(title, header, content);
    }
}