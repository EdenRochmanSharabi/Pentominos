import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
 
public class UI extends Application {

    private int[][][] state;
    public static Group root = new Group();
    public static Button buttonGA;
    public static Button buttonAlgX;
    public static RotateTransition myRT;
    public static int count = 0;
    public static boolean GA = false;

    public static void main(String[] args) { 
        launch(args);
        
    }
    
    @Override
    public void start(Stage primaryStage) {

     //Creates buttons to activate GA or Algorithm X

        buttonGA = new Button(" GA ");
        buttonAlgX = new Button("AlgX");
        buttonGA.setLayoutX(100);
        buttonGA.setLayoutY(750);
        buttonAlgX.setLayoutX(150);
        buttonAlgX.setLayoutY(750);

        Thread updateThread = new Thread(new Runnable(){
            @Override
            public void run(){
                Runnable updateContainer = new Runnable() {
                    @Override
                    public void run(){
                        setState();
                    }
                };
                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }
                    Platform.runLater(updateContainer);
                }
            }
        });

        updateThread.setDaemon(true);
        updateThread.start();
        
        PerspectiveCamera camera = new PerspectiveCamera();
        Scene scene = new Scene(root, 1000, 800);
        scene.setCamera(camera);
        scene.setFill(Color.web("#000000"));
        primaryStage.setTitle("KNAPSACK PROBLEM");
        primaryStage.setScene(scene);
        primaryStage.show(); 

        
    }
	/**
	 * Creates cube in differen colors depending on the cube code
	 * @param X position of cube
     * @param Y position of cube
	 * @param Z position of cube
	 * @return cube
	 */

    static Box myBox(int X, int Y, int Z, int Mycolor){
        Box myBox = new Box();
        myBox.setWidth(20); 
        myBox.setHeight(20);
        myBox.setDepth(20);
        myBox.setTranslateX(170 + X);
        myBox.setTranslateY(500 - Y);
        myBox.setTranslateZ(100 - Z);
        
        PhongMaterial phongMaterial = new PhongMaterial();
        myBox.setDrawMode(DrawMode.FILL);
        if (GA) {
            
        
        if(Mycolor > 6){
            phongMaterial.setDiffuseColor(Color.LIGHTSTEELBLUE);
        }
        else if (Mycolor > 2){
            phongMaterial.setDiffuseColor(Color.MIDNIGHTBLUE);
        }
        else{
            phongMaterial.setDiffuseColor(Color.CORNFLOWERBLUE);
        }
        }
        else{
        if (Mycolor >= 0 && Mycolor <= 7){
            phongMaterial.setDiffuseColor(Color.LIGHTCYAN);
        }
        else if (8 <= Mycolor && Mycolor <= 31){
            phongMaterial.setDiffuseColor(Color.SEAGREEN);
        }
        else {
            phongMaterial.setDiffuseColor(Color.LIGHTSEAGREEN);
        }
    }
        myBox.setMaterial(phongMaterial);
        
        return myBox;
    }

    	/**
	 * Creates transparent cube drwan with lines colored black
	 * @param X position of cube
     * @param Y position of cube
	 * @param Z position of cube
	 * @return cube
	 */

    static Box myBoxLine(int X, int Y, int Z){
        Box myBox = new Box();
        myBox.setWidth(20); 
        myBox.setHeight(20);
        myBox.setDepth(20);
        myBox.setTranslateX(170 + X);
        myBox.setTranslateY(500 - Y);
        myBox.setTranslateZ(100 - Z);
        PhongMaterial phongMaterial = new PhongMaterial();
        myBox.setDrawMode(DrawMode.LINE);
        phongMaterial.setDiffuseColor(Color.BLACK);
        myBox.setMaterial(phongMaterial);

        return myBox;
    }

        // Creates T shaped pentomino 

        static Box pent11(){
            Box pent11 = new Box();
            pent11.setWidth(20); 
            pent11.setHeight(20);
            pent11.setDepth(20);
            pent11.setTranslateX(130);
            pent11.setTranslateY(260);
            pent11.setTranslateZ(100);
            PhongMaterial phongMaterial = new PhongMaterial();
            phongMaterial.setSpecularColor(Color.BLACK);
            phongMaterial.setDiffuseColor(Color.LIGHTCYAN);
            pent11.setMaterial(phongMaterial);

            return pent11;
        }

        static Box pent12(){
            Box pent12 = new Box();
            pent12.setWidth(20); 
            pent12.setHeight(20);
            pent12.setDepth(20);
            pent12.setTranslateX(150);
            pent12.setTranslateY(260);
            pent12.setTranslateZ(100);
            PhongMaterial phongMaterial = new PhongMaterial();
            phongMaterial.setSpecularColor(Color.BLACK);
            phongMaterial.setDiffuseColor(Color.LIGHTCYAN);
            pent12.setMaterial(phongMaterial);

            return pent12;
        }

        static Box pent13(){
            Box pent13 = new Box();
            pent13.setWidth(20); 
            pent13.setHeight(20);
            pent13.setDepth(20);
            pent13.setTranslateX(170);
            pent13.setTranslateY(260);
            pent13.setTranslateZ(100);
            PhongMaterial phongMaterial = new PhongMaterial();
            phongMaterial.setSpecularColor(Color.BLACK);
            phongMaterial.setDiffuseColor(Color.LIGHTCYAN);
            pent13.setMaterial(phongMaterial);

            return pent13;
        }

        static Box pent14(){
            Box pent14 = new Box();
            pent14.setWidth(20); 
            pent14.setHeight(20);
            pent14.setDepth(20);
            pent14.setTranslateX(150);
            pent14.setTranslateY(280);
            pent14.setTranslateZ(100);
            PhongMaterial phongMaterial = new PhongMaterial();
            phongMaterial.setSpecularColor(Color.BLACK);
            phongMaterial.setDiffuseColor(Color.LIGHTCYAN);
            pent14.setMaterial(phongMaterial);
            return pent14;
        }

        static Box pent15(){
            Box pent15 = new Box();
            pent15.setWidth(20); 
            pent15.setHeight(20);
            pent15.setDepth(20);
            pent15.setTranslateX(150);
            pent15.setTranslateY(300);
            pent15.setTranslateZ(100);
            PhongMaterial phongMaterial = new PhongMaterial();
            phongMaterial.setSpecularColor(Color.BLACK);
            phongMaterial.setDiffuseColor(Color.LIGHTCYAN);
            pent15.setMaterial(phongMaterial);

            return pent15;
        }

        // Creates L shaped pentomino 

        static Box pent21(){
            Box pent21 = new Box();
            pent21.setWidth(20); 
            pent21.setHeight(20);
            pent21.setDepth(20);
            pent21.setTranslateX(450);
            pent21.setTranslateY(250);
            pent21.setTranslateZ(100);
            PhongMaterial phongMaterial = new PhongMaterial();
            phongMaterial.setSpecularColor(Color.BLACK);
            phongMaterial.setDiffuseColor(Color.SEAGREEN);
            pent21.setMaterial(phongMaterial);

            return pent21;
        }
        static Box pent22(){
            Box pent22 = new Box();
            pent22.setWidth(20); 
            pent22.setHeight(20);
            pent22.setDepth(20);
            pent22.setTranslateX(450);
            pent22.setTranslateY(270);
            pent22.setTranslateZ(100);
            PhongMaterial phongMaterial = new PhongMaterial();
            phongMaterial.setSpecularColor(Color.BLACK);
            phongMaterial.setDiffuseColor(Color.SEAGREEN);
            pent22.setMaterial(phongMaterial);

            return pent22;
        }

        static Box pent23(){
            Box pent13 = new Box();
            pent13.setWidth(20); 
            pent13.setHeight(20);
            pent13.setDepth(20);
            pent13.setTranslateX(450);
            pent13.setTranslateY(290);
            pent13.setTranslateZ(100);
            PhongMaterial phongMaterial = new PhongMaterial();
            phongMaterial.setSpecularColor(Color.BLACK);
            phongMaterial.setDiffuseColor(Color.SEAGREEN);
            pent13.setMaterial(phongMaterial);

            return pent13;
        }

        static Box pent24(){
            Box pent24 = new Box();
            pent24.setWidth(20); 
            pent24.setHeight(20);
            pent24.setDepth(20);
            pent24.setTranslateX(450);
            pent24.setTranslateY(310);
            pent24.setTranslateZ(100);
            PhongMaterial phongMaterial = new PhongMaterial();
            phongMaterial.setSpecularColor(Color.BLACK);
            phongMaterial.setDiffuseColor(Color.SEAGREEN);
            pent24.setMaterial(phongMaterial);

            return pent24;
        }

        static Box pent25(){
            Box pent24 = new Box();
            pent24.setWidth(20); 
            pent24.setHeight(20);
            pent24.setDepth(20);
            pent24.setTranslateX(470);
            pent24.setTranslateY(310);
            pent24.setTranslateZ(100);
            PhongMaterial phongMaterial = new PhongMaterial();
            phongMaterial.setSpecularColor(Color.BLACK);
            phongMaterial.setDiffuseColor(Color.SEAGREEN);
            pent24.setMaterial(phongMaterial);

            return pent24;
        }

        // Creates P shaped pentomino 

        static Box pent31(){
            Box pent31 = new Box();
            pent31.setWidth(20); 
            pent31.setHeight(20);
            pent31.setDepth(20);
            pent31.setTranslateX(750);
            pent31.setTranslateY(255);
            pent31.setTranslateZ(100);
            PhongMaterial phongMaterial = new PhongMaterial();
            phongMaterial.setSpecularColor(Color.BLACK);
            phongMaterial.setDiffuseColor(Color.LIGHTSEAGREEN);
            pent31.setMaterial(phongMaterial);

            return pent31;
        }
        static Box pent32(){
            Box pent32 = new Box();
            pent32.setWidth(20); 
            pent32.setHeight(20);
            pent32.setDepth(20);
            pent32.setTranslateX(770);
            pent32.setTranslateY(255);
            pent32.setTranslateZ(100);
            PhongMaterial phongMaterial = new PhongMaterial();
            phongMaterial.setSpecularColor(Color.BLACK);
            phongMaterial.setDiffuseColor(Color.LIGHTSEAGREEN);
            pent32.setMaterial(phongMaterial);

            return pent32;
        }
    
        static Box pent33(){
            Box pent33 = new Box();
            pent33.setWidth(20); 
            pent33.setHeight(20);
            pent33.setDepth(20);
            pent33.setTranslateX(750);
            pent33.setTranslateY(275);
            pent33.setTranslateZ(100);
            PhongMaterial phongMaterial = new PhongMaterial();
            phongMaterial.setSpecularColor(Color.BLACK);
            phongMaterial.setDiffuseColor(Color.LIGHTSEAGREEN);
            pent33.setMaterial(phongMaterial);

            return pent33;
        }
    
        static Box pent34(){
            Box pent34 = new Box();
            pent34.setWidth(20); 
            pent34.setHeight(20);
            pent34.setDepth(20);
            pent34.setTranslateX(770);
            pent34.setTranslateY(275);
            pent34.setTranslateZ(100);
            PhongMaterial phongMaterial = new PhongMaterial();
            phongMaterial.setSpecularColor(Color.BLACK);
            phongMaterial.setDiffuseColor(Color.LIGHTSEAGREEN);
            pent34.setMaterial(phongMaterial);

            return pent34;
        }
    
        static Box pent35(){
            Box pent35 = new Box();
            pent35.setWidth(20); 
            pent35.setHeight(20);
            pent35.setDepth(20);
            pent35.setTranslateX(750);
            pent35.setTranslateY(295);
            pent35.setTranslateZ(100);
            PhongMaterial phongMaterial = new PhongMaterial();
            phongMaterial.setSpecularColor(Color.BLACK);
            phongMaterial.setDiffuseColor(Color.LIGHTSEAGREEN);
            pent35.setMaterial(phongMaterial);

            return pent35;
        }

        static Box myCube(){
            Box myBox = new Box();
            myBox.setWidth(40); 
            myBox.setHeight(40);
            myBox.setDepth(80);
            myBox.setTranslateX(150);
            myBox.setTranslateY(150);
            myBox.setTranslateZ(100);
            PhongMaterial phongMaterial = new PhongMaterial();
            phongMaterial.setSpecularColor(Color.BLACK);
            phongMaterial.setDiffuseColor(Color.LIGHTSTEELBLUE);
            myBox.setMaterial(phongMaterial);

            return myBox;
        }

        static Box myCube2(){

            Box myBox2 = new Box();
            myBox2.setWidth(40); 
            myBox2.setHeight(60); 
            myBox2.setDepth(80);
            myBox2.setTranslateX(450);
            myBox2.setTranslateY(150);
            myBox2.setTranslateZ(100);
            PhongMaterial phongMaterial = new PhongMaterial();
            phongMaterial.setSpecularColor(Color.BLACK);
            phongMaterial.setDiffuseColor(Color.CORNFLOWERBLUE);
            myBox2.setMaterial(phongMaterial);
            
            return myBox2;
        }

        static Box myCube3(){

            Box myBox3 = new Box();
            myBox3.setWidth(60); 
            myBox3.setHeight(60); 
            myBox3.setDepth(60);
            myBox3.setTranslateX(750);
            myBox3.setTranslateY(150);
            myBox3.setTranslateZ(100);
            PhongMaterial phongMaterial = new PhongMaterial();
            phongMaterial.setSpecularColor(Color.BLACK);
            phongMaterial.setDiffuseColor(Color.MIDNIGHTBLUE);
            myBox3.setMaterial(phongMaterial);

            return myBox3;
        }

    public static void setState() {
        int[][][] _state = Main.ContainerBestFitness;
        root.getChildren().clear();
        //int count = 0;
        for (int i = 0; i < _state.length; i++) {
            for (int j = 0; j < _state[i].length; j++) {
                for (int k = 0; k < _state[i][j].length; k++) {
                    if (_state[i][j][k] != 0) {
                        root.getChildren().add(myBox(k*20,j*20,i*20,_state[i][j][k]));
                        root.getChildren().add(myBoxLine(k*20,j*20,i*20));
                       //count++;
                    }
                }
            }
        }

        //adds pentominoes shaped T, L and P in the UI

        root.getChildren().add(pent11());
        root.getChildren().add(pent12());
        root.getChildren().add(pent13());
        root.getChildren().add(pent14());
        root.getChildren().add(pent15());

        root.getChildren().add(pent21());
        root.getChildren().add(pent22());
        root.getChildren().add(pent23());
        root.getChildren().add(pent24());
        root.getChildren().add(pent25());

        root.getChildren().add(pent31());
        root.getChildren().add(pent32());
        root.getChildren().add(pent33());
        root.getChildren().add(pent34());
        root.getChildren().add(pent35());        

        root.getChildren().add(myCube());
        root.getChildren().add(myCube2());
        root.getChildren().add(myCube3());
        root.getChildren().add(buttonGA);
        root.getChildren().add(buttonAlgX);

        //creates button that activates Genetic Algorithm

        buttonGA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GA = true;
                count +=1;
                System.out.println(count);
                myRT = new RotateTransition(Duration.millis(10000), root);
                myRT.setCycleCount(20);
                myRT.setByAngle(75);
                myRT.setAxis(Rotate.Y_AXIS);
                myRT.setAutoReverse(true);
                myRT.play();
                buttonGA.setVisible(false);
                buttonAlgX.setVisible(false);
                Cargo.config();
                Cargo.config_pentominoes();
                Main.questionD(1000);


            }
        }); 

        //creates button that activates Algorithm X

        buttonAlgX.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GA = false;
                System.out.println("Clicked..");
               // AlgorithmX.configPentominoes();
                //Main.ContainerBestFitness = AlgorithmX.Algorithmx(0);
                myRT = new RotateTransition(Duration.millis(10000), root);
                myRT.setCycleCount(20);
                myRT.setByAngle(75);
                myRT.setAxis(Rotate.Y_AXIS);
                myRT.setAutoReverse(true);
                myRT.play();
                buttonGA.setVisible(false);
                buttonAlgX.setVisible(false);

            }
        });

    }
    


}
