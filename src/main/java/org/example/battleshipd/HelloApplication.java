package org.example.battleshipd;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch();
    }

    private void drawBoat1(Stage stage) {
        // Create the hull of the boat using a rectangle
        Rectangle hull = new Rectangle(100, 200, 200, 50);
        hull.setFill(Color.SADDLEBROWN);

        // Create the boat cabin using a rectangle
        Rectangle cabin = new Rectangle(160, 160, 80, 40);
        cabin.setFill(Color.DARKBLUE);

        // Create a ship window using a circle
        Circle window = new Circle(200, 180, 10);
        window.setFill(Color.LIGHTBLUE);
        window.setStroke(Color.BLACK);

        Pane root = new Pane();
        root.getChildren().addAll(hull, cabin, window);

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Fragatas");
        stage.show();
    }

    private void drawBoat2(Stage stage) {
        // Create the hull of the boat using a rectangle
        Rectangle hull = new Rectangle(100, 200, 200, 50);
        hull.setFill(Color.SADDLEBROWN);

        // Create the ship's mast using a rectangle
        Rectangle mast = new Rectangle(195, 100, 10, 100);
        mast.setFill(Color.DARKGRAY);

        // Create the ship's sail using a triangle (polygon)
        Polygon sail = new Polygon();
        sail.getPoints().addAll(
                200.0, 100.0,
                200.0, 200.0,
                150.0, 200.0
        );
        sail.setFill(Color.DARKGRAY);

        Pane root = new Pane();
        root.getChildren().addAll(hull, mast, sail);

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.setTitle("destructores");
        stage.show();
    }


    private void drawBoat3(Stage stage) {
        // Create the hull of the ship using a polygon
        Polygon hull = new Polygon();
        hull.getPoints().addAll(
                50.0, 200.0,  // Punto superior izquierdo
                350.0, 200.0, // Punto superior derecho
                300.0, 250.0, // Punto inferior derecho
                100.0, 250.0  // Punto inferior izquierdo
        );
        hull.setFill(Color.SADDLEBROWN);

        // Create the ship's mast using a rectangle
        Rectangle mast = new Rectangle(195, 50, 10, 150);
        mast.setFill(Color.DARKGRAY);

        // Create the ship's sail using a polygon
        Polygon sail = new Polygon();
        sail.getPoints().addAll(
                200.0, 50.0,
                200.0, 200.0,
                120.0, 200.0
        );
        sail.setFill(Color.DARKGREY);

        // Create a ship window using a circle
        Circle window = new Circle(220, 220, 10);
        window.setFill(Color.LIGHTBLUE);
        window.setStroke(Color.BLACK);

        Pane root = new Pane();
        root.getChildren().addAll(hull, mast, sail, window);

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.setTitle("submarinos");
        stage.show();
    }

    private void drawBoat4(Stage stage) {
        // Create the hull of the ship using a polygon
        Polygon hull = new Polygon();
        hull.getPoints().addAll(
                100.0, 400.0,
                600.0, 400.0,
                500.0, 500.0,
                200.0, 500.0
        );
        hull.setFill(Color.SADDLEBROWN);

        // Create the deck of the boat using a rectangle
        Rectangle deck = new Rectangle(150, 350, 400, 50);
        deck.setFill(Color.BURLYWOOD);

        // Create the ship's mast using a rectangle
        Rectangle mast = new Rectangle(350, 100, 10, 250);
        mast.setFill(Color.DARKGRAY);

        // create the left sail of the ship using a polygon
        Polygon leftSail = new Polygon();
        leftSail.getPoints().addAll(
                355.0, 100.0,  // Punto superior
                355.0, 350.0, // Punto inferior derecho
                255.0, 350.0  // Punto inferior izquierdo
        );
        leftSail.setFill(Color.DARKGREY);

        // Create the right sail of the ship using a polygon
        Polygon rightSail = new Polygon();
        rightSail.getPoints().addAll(
                355.0, 100.0,
                355.0, 350.0,
                455.0, 350.0
        );
        rightSail.setFill(Color.DARKGREY);

        // Create ship windows using circles
        Circle window1 = new Circle(250, 430, 20);
        window1.setFill(Color.LIGHTBLUE);
        window1.setStroke(Color.BLACK);

        Circle window2 = new Circle(350, 430, 20);
        window2.setFill(Color.LIGHTBLUE);
        window2.setStroke(Color.BLACK);

        Circle window3 = new Circle(450, 430, 20);
        window3.setFill(Color.LIGHTBLUE);
        window3.setStroke(Color.BLACK);

        Pane root = new Pane();
        root.getChildren().addAll(hull, deck, mast, leftSail, rightSail, window1, window2, window3);


        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("portaaviones");
        stage.show();
    }


    @Override
    public void start(Stage stage) {
        //drawBoat1(stage);
        //drawBoat2(stage);
        //drawBoat3(stage);
        //drawBoat4(stage);
    }
}