package org.example.battleshipd.controller;

import javafx.scene.image.Image;

import org.example.battleshipd.view.Table;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Objects;
import java.util.Random;

public class GameController {

    @FXML
    private HBox hboxPanes;
    @FXML
    private AnchorPane anchorpaneOne;
    @FXML
    private AnchorPane anchorpaneTwo;
    @FXML
    private Label labelEnemy;

    private int[][] position1 = new int[11][11];
    private int[][] position2 = new int[11][11];
    Table tableOne = new Table();
    Table tableTwo = new Table();
    GridPane gridPane = new GridPane();
    GridPane gridPaneTwo = new GridPane();
    private static final int[] SHIP_SIZES = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
    private static final String[] list = new String[]{"portaAvion", "submarino", "destructor", "fragata"};
    private static final Random random = new Random();
    private static final int rows = 11;
    private static final int columns = 11;
    private static final int CELL_SIZE = 36;
    private int currentRow = 0;
    private int currentCol = 0;
    private boolean isHorizontal = true; // Estado inicial del barco
    private int currentShipIndex = 0; // Índice del barco actual

    private Rectangle ship;
    private boolean playerTurn = true; // Control de turno

    private final Image aguaImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/org/example/battleshipd/images/cross.png")));

    private final Image tocadoImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/org/example/battleshipd/images/fire.png")));
    private final Image hundidoImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/org/example/battleshipd/images/cross.png")));



    @FXML
    public void inicialize() {

        gridPaneTwo.setVisible(false);
        anchorpaneTwo.setVisible(false);
        labelEnemy.setVisible(false);
        gridPaneTwo.setOnMouseClicked(this::handleEnemyGridClick);
        BorderStroke borderStroke = new BorderStroke(
                Color.BLACK, // Color del borde
                BorderStrokeStyle.SOLID, // Estilo del borde
                CornerRadii.EMPTY, // Radios de las esquinas
                new BorderWidths(1) // Grosor del borde
        );

        Border border = new Border(borderStroke);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Pane panel = new Pane();
                panel.setStyle("-fx-padding: 17px; -fx-border-color: GHOSTWHITE; -fx-opacity:50%; -fx-border-width:17 px; ");
                gridPane.setAlignment(Pos.CENTER);
                gridPane.setBackground((new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, null))));
                gridPane.add(panel, col, row);

                if (row == 0 && col > 0) {
                    Label label = new Label(Integer.toString(col));
                    label.setStyle("-fx-padding:5px; -fx-font-size: 16 px; -fx-text-fill: black; -fx-background-color:#9b9898; -fx-alignment: center");
                    label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    GridPane.setHgrow(label, Priority.ALWAYS);
                    GridPane.setVgrow(label, Priority.ALWAYS);
                    GridPane.setHalignment(label, HPos.CENTER); // Alinear el texto al centro horizontalmente
                    GridPane.setValignment(label, VPos.CENTER);
                    gridPane.add(label, col, row); // Agregar el número a la celda
                }
                if (col == 0 && row > 0) {
                    Label label = new Label(Integer.toString(row));
                    label.setStyle("-fx-padding: 5 px; -fx-font-size: 16px; -fx-text-fill: black; -fx-background-color:#9b9898; -fx-alignment: center");
                    label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    GridPane.setHgrow(label, Priority.ALWAYS);
                    GridPane.setVgrow(label, Priority.ALWAYS);
                    GridPane.setHalignment(label, HPos.CENTER); // Alinear el texto al centro horizontalmente
                    GridPane.setValignment(label, VPos.CENTER);
                    gridPane.add(label, col, row);// Agregar el número a la celda
                } else if (row == 0 && col == 0) { // Si es la esquina superior izquierda
                    // No hacer nada, ya que aquí no queremos números ni barcos
                }
            }
        }
        anchorpaneOne.getChildren().add(gridPane);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Pane panelTwo = new Pane();
                panelTwo.setBorder(border);
                panelTwo.setStyle("-fx-padding: 17px; -fx-border-color: GHOSTWHITE; -fx-opacity:50%; -fx-border-width:12 px; "); // Padding interno del panel
                gridPaneTwo.setAlignment(Pos.CENTER);
                gridPaneTwo.setBackground((new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, null))));
                gridPaneTwo.add(panelTwo, col, row);

                if (row == 0 && col > 0) {
                    Label label = new Label(Integer.toString(col));
                    label.setStyle("-fx-padding: 5px; -fx-font-size: 16px; -fx-text-fill: black; -fx-background-color:#9b9898; -fx-alignment: center"); // Fondo blanco y tamaño de letra 16px
                    label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    GridPane.setHgrow(label, Priority.ALWAYS);
                    GridPane.setVgrow(label, Priority.ALWAYS);
                    GridPane.setHalignment(label, HPos.CENTER); // Alinear el texto al centro horizontalmente
                    GridPane.setValignment(label, VPos.CENTER);
                    gridPaneTwo.add(label, col, row); // Agregar el número a la celda
                }
                if (col == 0 && row > 0) {
                    Label label = new Label(Integer.toString(row));
                    label.setStyle("-fx-padding: 5px; -fx-font-size: 16px; -fx-text-fill: black; -fx-background-color:#9b9898; -fx-alignment: center");
                    label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    GridPane.setHgrow(label, Priority.ALWAYS);
                    GridPane.setVgrow(label, Priority.ALWAYS);
                    GridPane.setHalignment(label, HPos.CENTER); // Alinear el texto al centro horizontalmente
                    GridPane.setValignment(label, VPos.CENTER);
                    gridPaneTwo.add(label, col, row); // Agregar el número a la celda
                }
            }
        }

        anchorpaneTwo.getChildren().add(gridPaneTwo);
        //putBoatsEnemy();
        ship = new Rectangle(SHIP_SIZES[currentShipIndex] * CELL_SIZE, CELL_SIZE);
        ship.setFill(Color.GRAY);

        // Añadir un EventHandler para cambiar la orientación al hacer clic
        ship.setOnMouseClicked(this::handleShipClick);
        gridPane.setOnMouseMoved(this::handleMouseMoved);

        // Añadir el barco al grid
        gridPane.add(ship, 1, 1, SHIP_SIZES[currentShipIndex], 1);

        Scene scene = gridPane.getScene();
        if (scene != null) {
            scene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyPressed);
        } else {
            gridPane.sceneProperty().addListener((observable, oldScene, newScene) -> {
                if (newScene != null) {
                    newScene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyPressed);
                }
            });
        }
        gridPaneTwo.setOnMouseClicked(this::handleEnemyGridClick);
    }

    private void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case R: // Tecla para rotar
                rotateShip();
                break;
            default:
                break;
        }
    }

    private void placeShipOnBoard() {
        int shipSize = SHIP_SIZES[currentShipIndex];
        if (isHorizontal) {
            for (int i = 0; i < shipSize; i++) {
                // Actualiza la posición de la tabla del jugador aquí
                position1[currentRow][currentCol + i] = 1;
            }
        } else {
            for (int i = 0; i < shipSize; i++) {
                // Actualiza la posición de la tabla del jugador aquí
                position1[currentRow + i][currentCol] = 1;
            }
        }

        // Proceso de actualización visual aquí
        for (int i = 0; i < shipSize; i++) {
            int col = isHorizontal ? currentCol + i : currentCol;
            int row = isHorizontal ? currentRow : currentRow + i;
            Pane pane = getNodeFromGridPane(gridPane, col, row);
            if (pane != null) {
                pane.setStyle("-fx-background-color: gray;");
            }
        }
    }

    private Pane getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (javafx.scene.Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return (Pane) node;
            }
        }
        return null;
    }

    private void updateShipSize() {
        int shipSize = SHIP_SIZES[currentShipIndex];
        if (isHorizontal) {
            ship.setWidth(shipSize * CELL_SIZE);
            ship.setHeight(CELL_SIZE);
        } else {
            ship.setWidth(CELL_SIZE);
            ship.setHeight(shipSize * CELL_SIZE);
        }

        // Reposicionar el barco para que quede en su lugar actual
        GridPane.setColumnSpan(ship, isHorizontal ? shipSize : 1);
        GridPane.setRowSpan(ship, isHorizontal ? 1 : shipSize);
    }

    private void rotateShip() {
        int shipSize = SHIP_SIZES[currentShipIndex];
        if (isHorizontal) {
            if (currentRow + shipSize <= 11) {
                // Cambiar a vertical
                ship.setWidth(CELL_SIZE);
                ship.setHeight(shipSize * CELL_SIZE);
                isHorizontal = false;
            }
        } else {
            if (currentCol + shipSize <= 11) {
                // Cambiar a horizontal
                ship.setWidth(shipSize * CELL_SIZE);
                ship.setHeight(CELL_SIZE);
                isHorizontal = true;
            }
        }
        // Reposicionar el barco para que quede en su lugar actual
        GridPane.setColumnIndex(ship, currentCol);
        GridPane.setRowIndex(ship, currentRow);
        GridPane.setColumnSpan(ship, isHorizontal ? shipSize : 1);
        GridPane.setRowSpan(ship, isHorizontal ? 1 : shipSize);
    }

    private void handleShipClick(MouseEvent event) {
        int shipCol = GridPane.getColumnIndex(ship);
        int shipRow = GridPane.getRowIndex(ship);

        int shipSize = SHIP_SIZES[currentShipIndex];
        boolean canPlace = true;

        for (int i = 0; i < shipSize; i++) {
            int col = isHorizontal ? shipCol + i : shipCol;
            int row = isHorizontal ? shipRow : shipRow + i;
            if (!checkCell(row, col, tableOne.getTable())) {
                canPlace = false;
                break;
            }
        }
        if (canPlace) {
            placeShipOnBoard();
            currentShipIndex++;
            if (currentShipIndex < SHIP_SIZES.length) {
                updateShipSize();
            } else {
                System.out.println("All ships placed.");
                gridPane.getChildren().remove(ship);
            }
        } else {
            System.out.println("Cannot place ship here.");
        }

        GridPane.setColumnIndex(ship, currentCol);
        GridPane.setRowIndex(ship, currentRow);
        GridPane.setColumnSpan(ship, isHorizontal ? shipSize : 1);
        GridPane.setRowSpan(ship, isHorizontal ? 1 : shipSize);
    }

    private void handleMouseMoved(MouseEvent event) {
        int col = (int) (event.getX() / CELL_SIZE);
        int row = (int) (event.getY() / CELL_SIZE);

        // Verificar que el barco no se salga de los límites
        int shipSize = SHIP_SIZES[currentShipIndex];
        if (isHorizontal) {
            if (col + shipSize <= 11 && row < 11 && row > 0 && col > 0) {
                currentCol = col;
                currentRow = row;
                GridPane.setColumnIndex(ship, col);
                GridPane.setRowIndex(ship, row);
            }
        } else {
            if (row + shipSize <= 11 && col < 11 && row > 0 && col > 0) {
                currentCol = col;
                currentRow = row;
                GridPane.setColumnIndex(ship, col);
                GridPane.setRowIndex(ship, row);
            }
        }
    }

    @FXML
    void buttonPlayGame(ActionEvent event) {
        gridPaneTwo.setVisible(true);
        anchorpaneTwo.setVisible(true);
        labelEnemy.setVisible(true);
        System.out.println("Matriz del Jugador:");
        tableOne.printPlayerBoard();

        System.out.println("Matriz del Enemigo:");
        placeShips(tableTwo.getTable());
        tableTwo.printPlayerBoard();

        ship.setVisible(false);
    }

    public boolean checkCell(int row, int cols, String[][] table) {
        if (table[row][cols].equals(".")) {
            table[row][cols] = "X";
            return true;
        }
        return false;
    }

    private static void placeShips(String[][] table) {
        for (int size : SHIP_SIZES) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(10);
                int col = random.nextInt(10);
                boolean horizontal = random.nextBoolean();

                if (canPlaceShip(table, row, col, size, horizontal)) {
                    placeShip(table, row, col, size, horizontal);
                    placed = true;
                }
            }
        }
    }

    private static boolean canPlaceShip(String[][] table, int row, int col, int size, boolean horizontal) {
        if (horizontal) {
            if (col + size > 11) return false; // Check bounds
            for (int i = 0; i < size; i++) {
                if (!table[row][col + i].equals(".")) return false; // Check overlap
            }
        } else {
            if (row + size > 11) return false; // Check bounds
            for (int i = 0; i < size; i++) {
                if (!table[row + i][col].equals(".")) return false; // Check overlap
            }
        }
        return true;
    }

    private static void placeShip(String[][] table, int row, int col, int size, boolean horizontal) {
        if (horizontal) {
            for (int i = 0; i < size; i++) {
                table[row][col + i] = "X";
            }
        } else {
            for (int i = 0; i < size; i++) {
                table[row + i][col] = "X";
            }
        }
    }
    private void enemyMove() {
        boolean moveMade = false;
        while (!moveMade) {
            int row = random.nextInt(10) + 1;
            int col = random.nextInt(10) + 1;

            if (position1[row][col] == 0 || position1[row][col] == 1) {
                Pane pane = getNodeFromGridPane(gridPane, col, row);
                if (position1[row][col] == 1) {
                    pane.setStyle("-fx-background-color: yellow;");
                    position1[row][col] = 2; // Marcar como golpeado
                } else if (position1[row][col] == 0) {
                    pane.setStyle("-fx-background-color: red;");
                    position1[row][col] = 3; // Marcar como fallado
                    moveMade = true; // Movimiento hecho, terminar el turno del enemigo
                }
                playerTurn = true; // Cambiar turno al jugador
            }
        }
    }

    @FXML
    private void handleEnemyGridClick(MouseEvent event) {
        if (!playerTurn) return; // Si no es el turno del jugador, no hacer nada

        int col = (int) (event.getX() / CELL_SIZE);
        int row = (int) (event.getY() / CELL_SIZE);

        if (row >= 1 && row <= 10 && col >= 1 && col <= 10) {
            Pane pane = getNodeFromGridPane(gridPaneTwo, col, row);
            String[][] enemyTable = tableTwo.getTable();
            if (enemyTable[row][col].equals("X")) {
                pane.setStyle("-fx-background-color: yellow;");
                enemyTable[row][col] = "H"; // Marcar como golpeado
            } else if (enemyTable[row][col].equals(".")) {
                pane.setStyle("-fx-background-color: red;");
                enemyTable[row][col] = "M"; // Marcar como fallado
                playerTurn = false; // Cambiar turno al enemigo
                enemyMove(); // Ejecutar el movimiento del enemigo
            }
        }
    }
}
