import com.sun.javafx.font.freetype.HBGlyphLayout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class Startup{

    private static String fileNames = "C:\\Fun\\HighScores.txt";
    private static BufferedWriter bufferedWriter = null;
    private static FileWriter fileWriter = null;
    private static boolean getWin;
    private static Button mainMenu;
    private static GridPane gridPane;
    private static BorderPane borderPane;
    private static Player p = new Player();
    private static int gSize = 0;
    private static double mTurns=0;
    private static Tiles[][] tiles;
    private static Button startButton = new Button("Start Game!");
    private static Button exitButton = new Button("Exit");
    private static Text textField;
    private static TableColumn<Scores, String> nameCol;
    private static TableColumn<Scores, String> scoreCol;

    public static Scene Startup(){
        VBox startMenu = new VBox(5);
        startButton.setStyle("-fx-background-color:\n"
                + "        rgba(0,0,0,0.08),\n"
                + "        linear-gradient(#9a9a9a, #909090),\n"
                + "        linear-gradient(white 0%, #f3f3f3 50%, #ececec 51%, #f2f2f2 100%);\n"
                + "    -fx-background-insets: 0 0 -1 0,0,1;\n"
                + "    -fx-background-radius: 5,5,4;\n"
                + "    -fx-padding: 10 26 10 26;\n"
                + "    -fx-text-fill: #242d35;\n"
                + "    -fx-font-size: 14px;");
//THESE ARE GRADIENT CODE
        exitButton.setStyle("-fx-background-color:\n"
                + "        rgba(0,0,0,0.08),\n"
                + "        linear-gradient(#9a9a9a, #909090),\n"
                + "        linear-gradient(white 0%, #f3f3f3 50%, #ececec 51%, #f2f2f2 100%);\n"
                + "    -fx-background-insets: 0 0 -1 0,0,1;\n"
                + "    -fx-background-radius: 5,5,4;\n"
                + "    -fx-padding: 10 26 10 26;\n"
                + "    -fx-text-fill: #242d35;\n"
                + "    -fx-font-size: 14px;");

        Image logo = new Image("/Assets/FloodIt.jpg");
        ImageView iv1 = new ImageView();
        iv1.setImage(logo);


        startMenu.getChildren().addAll(iv1, startButton, exitButton);

        startMenu.setAlignment(Pos.CENTER);

        Scene startup = new Scene(startMenu, 300, 300);
        return startup;
    }//The opening to the game and title

    public static Scene inBetween(){
        TextField name = new TextField();
        name.setPromptText("Player Name");
        TextField gSize = new TextField();
        gSize.setPromptText("Grid Size (6-25)");
        Button confirm = new Button("Confirm");
        
        confirm.setOnAction(e -> {
            isInt(gSize, gSize.getText());
            p.setName(name.getText());
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(name, gSize, confirm);

        return new Scene(layout, 300, 300);
    }//This happens after you start the game, you can set grid size and your name here

    private static boolean isInt(TextField name, String gSize) {
        try{
            int size = Integer.parseInt(gSize);
            if (size >= 6 && size <= 25){
                Main.setWindow(Game(size));
            return true;
            }else{
                return false;
            }
        }catch (NumberFormatException e){
            return false;
        }
    }//Checks if you input a number between 6-25, even checks empty space

    public static Scene Game(int gSize){
        System.out.println(getContent());
        //SETTING THE GRID
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(0);
        gridPane.setHgap(0);
        gridPane.setAlignment(Pos.CENTER);

        //WORKING ON TURNS
        double a = 7, b = 4, c = 1, d = 2;

        createGrid(gridPane, gSize);//This creates the grid

        mTurns = ((a/b)*(gSize))-(c/d);
        int realTurns = (int)mTurns;
        p.setMaxTurns(realTurns);

        //DISPLAYING THE TURNS
        textField = new Text("Good Luck!");

        textField.setStyle("-fx-font: 15 Ariel");

        tiles[0][0].setMainObject(true);
        for (int i = 0; i < gSize; i++) {
            for (int t = 0; t < gSize; t++) {
                mainChecker(i, t);
            }}

        mainMenu = new Button("Main Menu");
        mainMenu.setStyle("-fx-background-color:\n"
                + "        rgba(0,0,0,0.08),\n"
                + "        linear-gradient(#9a9a9a, #909090),\n"
                + "        linear-gradient(white 0%, #f3f3f3 50%, #ececec 51%, #f2f2f2 100%);\n"
                + "    -fx-background-insets: 0 0 -1 0,0,1;\n"
                + "    -fx-background-radius: 5,5,4;\n"
                + "    -fx-padding: 10 26 10 26;\n"
                + "    -fx-text-fill: #242d35;\n"
                + "    -fx-font-size: 14px;");
        mainMenu.setOnAction(e ->{
            p.resetPlayer(p);
            Main.getWindow().close();
            Stage p = new Stage();
            Main main = new Main();
            try{
                main.start(p);
            }catch (Exception l){

            }
        });

        HBox bottombox = new HBox();
        Region space = new Region();
        HBox.setHgrow(space, Priority.ALWAYS);
        bottombox.getChildren().addAll(space,textField);

        //Table
        TableView<Scores> highScores = new TableView();
        highScores.setPrefHeight(135+(28*gSize));
        highScores.setPrefWidth(300);
        nameCol = new TableColumn("Player");
        nameCol.setPrefWidth(225);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        scoreCol = new TableColumn("Score");
        scoreCol.setPrefWidth(75);
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
        highScores.setItems(setHighScores());
        highScores.getColumns().addAll(nameCol,scoreCol);




        borderPane = new BorderPane();
        borderPane.setTop(mainMenu);
        BorderPane.setAlignment(mainMenu, Pos.TOP_LEFT);
        borderPane.setCenter(gridPane);
        BorderPane.setAlignment(gridPane, Pos.CENTER_RIGHT);
        borderPane.setBottom(bottombox);
        BorderPane.setAlignment(bottombox,Pos.CENTER);
        borderPane.setLeft(highScores);

        borderPane.setStyle("-fx-background-color: #c0c0c0");



        return new Scene(borderPane, 300+(33*gSize), 135+(28*gSize));
    }//This is where the main game code happens

    public void setScores(){



    }//creats score board

    public static void createGrid(GridPane gridPane, int size) {
        tiles = new Tiles[size][size];

        for (int i = 0; i < size; i++) {
            for (int t = 0; t < size; t++) {
                Random random = new Random();
                int o = random.nextInt(6) + 1;
                if (o == 1) {
                    tiles[i][t] = new Tiles("Green", false);
                    tiles[i][t].colorChanger(tiles[i][t].getColor(), (Button) tiles[i][t].getButton());
                    gridPane.add(tiles[i][t].getButton(), t, i);
                    clickAble(tiles[i][t], size);
                }
                if (o == 2) {
                    tiles[i][t] = new Tiles("Red", false);
                    tiles[i][t].colorChanger(tiles[i][t].getColor(), (Button) tiles[i][t].getButton());
                    gridPane.add(tiles[i][t].getButton(), t, i);
                    clickAble(tiles[i][t], size);
                }
                if (o == 3) {
                    tiles[i][t] = new Tiles("Blue", false);
                    tiles[i][t].colorChanger(tiles[i][t].getColor(), (Button) tiles[i][t].getButton());
                    gridPane.add(tiles[i][t].getButton(), t, i);
                    clickAble(tiles[i][t], size);
                }
                if (o == 4) {
                    tiles[i][t] = new Tiles("Yellow", false);
                    tiles[i][t].colorChanger(tiles[i][t].getColor(), (Button) tiles[i][t].getButton());
                    gridPane.add(tiles[i][t].getButton(), t, i);
                    clickAble(tiles[i][t], size);
                }
                if (o == 5) {
                    tiles[i][t] = new Tiles("Purple", false);
                    tiles[i][t].colorChanger(tiles[i][t].getColor(), (Button) tiles[i][t].getButton());
                    gridPane.add(tiles[i][t].getButton(), t, i);
                    clickAble(tiles[i][t], size);
                }
                if (o == 6) {
                    tiles[i][t] = new Tiles("Pink", false);
                    tiles[i][t].colorChanger(tiles[i][t].getColor(), (Button) tiles[i][t].getButton());
                    gridPane.add(tiles[i][t].getButton(), t, i);
                    clickAble(tiles[i][t], size);
                }
            }
        }
    }//This Method simply creates the grid with a given size! Calls on clickable to give tiles functions.

    public static void clickAble(Tiles g, int size) {

        g.getTile().setOnAction((ActionEvent e) -> {

            if (!tiles[0][0].getColor().equals(g.getColor())) {

                p.setTurns(p.getTurns() + 1);
                textField.setText(p.getName() + " has used " + p.getTurns() + "/" + p.getMaxTurns() + " turns!");
                gridPane.setAlignment(Pos.CENTER);


                for (int i = 0; i < size; i++) {
                            for (int t = 0; t < size; t++) {
//                                mainChecker(i, t);
                                if (tiles[i][t].isMainObject()) {
                            try {
                                if (tiles[i][t].getColor().equals(tiles[i - 1][t].getColor()) && !tiles[i - 1][t].isMainObject()) {
                                    tiles[i - 1][t].setMainObject(true);
                                    mainChecker((i - 1), t);
                                }
                            } catch (ArrayIndexOutOfBoundsException ignored) {
                            }
                            try {
                                if (tiles[i][t].getColor().equals(tiles[i + 1][t].getColor()) && !tiles[i + 1][t].isMainObject()) {
                                    tiles[i + 1][t].setMainObject(true);
                                    mainChecker((i+1), t);
                                }
                            } catch (ArrayIndexOutOfBoundsException ignored) {
                            }
                            try {
                                if (tiles[i][t].getColor().equals(tiles[i][t + 1].getColor()) && !tiles[i][t + 1].isMainObject()) {
                                    tiles[i][t + 1].setMainObject(true);
                                    mainChecker(i, (t+1));
                                }
                            } catch (ArrayIndexOutOfBoundsException ignored) {
                            }
                            try {
                                if (tiles[i][t].getColor().equals(tiles[i][t - 1].getColor()) && !tiles[i][t-1].isMainObject()) {
                                    tiles[i][t - 1].setMainObject(true);
                                    mainChecker(i, (t-1));
                                }
                            } catch (ArrayIndexOutOfBoundsException ignored) {
                            }


                                    for (int o = 0; o < size; o++) {
                                        for (int r = 0; r < size; r++) {
                                            if (tiles[o][r].isMainObject()) {
                                                tiles[o][r].setColor(g.getColor());
                                                tiles[o][r].colorChanger(g.getColor(), tiles[i][t].getTile());
                                            }
                                        }
                            }
                                }
                    }
                }
            }
            if (p.getTurns() <= (p.getMaxTurns())) {
                if (checkWin(tiles[0][0], size)) {
                    double hi, bye, why;
                    int total;
                    hi = p.getMaxTurns();
                    bye = p.getTurns();
                    why = ((bye / hi) * 100);
                    total = (int) why;
                    p.setScore(total);
                    System.out.println(p.getScore());

                    FileReader fr = null;
                    BufferedReader br = null;
                    BufferedWriter  bufferedWriter = null;
                    String save = "";



                    try{
                        fr = new FileReader("C:\\Fun\\HighScores.txt");
                        br = new BufferedReader(fr);

                        String sCurrentLine = br.readLine();
                        save = sCurrentLine;
                        while ((sCurrentLine = br.readLine()) != null) {
                            save = (save + "\n" + sCurrentLine);
                        }
                    } catch (Exception h){
                        System.out.println(h);
                    }

                    try {

                        FileWriter fe = new FileWriter("C:\\Fun\\HighScores.txt");
                        bufferedWriter = new BufferedWriter(new FileWriter("C:\\Fun\\HighScores.txt"));

                        bufferedWriter.write(save + "\n" + p.getName() + "\n" + p.getScore());
                    }catch (IOException p){
                        p.printStackTrace();
                    }finally{
                        try{
                            if(bufferedWriter != null){
                                bufferedWriter.close();
                            }

                        }catch (Exception q){
                            System.out.println(q);
                        }
                    }
                    boolean wins = ConfirmBox.display("Win", "Congratulations " + p.getName() + ", you won! Want to play again?");
                    if (wins) {
                        getWin = false;
                        p.resetPlayer(p);
                        Main.getWindow().close();
                        Stage p = new Stage();
                        Main main = new Main();
                        try {
                            main.start(p);
                        } catch (Exception l) {

                        }
                    } else if (!wins) {
                        Main.getWindow().close();
                    }
                }
            }else {
                boolean exits = ConfirmBox.display("Loss", "Sorry you lost, want  to try again?");
                if (exits) {
                    p.resetPlayer(p);
                    Main.getWindow().close();
                    Stage p = new Stage();
                    Main main = new Main();
                    try {
                        main.start(p);
                    } catch (Exception l) {

                    }
                } else if (!exits) {
                    Main.getWindow().close();
                }
            }
        });

    }//This gives all the buttons functionality and adds turns, also win/loss

    private static void mainChecker(int i, int t) {
        if (tiles[i][t].isMainObject()) {
            try {
                if (tiles[i][t].getColor().equals(tiles[i - 1][t].getColor()) && !tiles[i - 1][t].isMainObject()) {
                    tiles[i - 1][t].setMainObject(true);
                    if(i-1 >= gSize){
                    mainChecker((i-1), t);
                }}
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                if (tiles[i][t].getColor().equals(tiles[i + 1][t].getColor()) && !tiles[i + 1][t].isMainObject()) {
                    tiles[i + 1][t].setMainObject(true);
                    if(i+1 <= gSize){
                    mainChecker((i+1), t);
                }}
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                if (tiles[i][t].getColor().equals(tiles[i][t + 1].getColor()) && !tiles[i][t + 1].isMainObject()) {
                    tiles[i][t + 1].setMainObject(true);
                    if(t+1 <= gSize){
                    mainChecker(i, (t+1));
                }}
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                if (tiles[i][t].getColor().equals(tiles[i][t - 1].getColor()) && !tiles[i][t-1].isMainObject()) {
                    tiles[i][t - 1].setMainObject(true);
                    if(t - 1 >= gSize){
                    mainChecker(i, (t-1));
                }}
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }


            for (int o = 0; o < gSize; o++) {
                for (int r = 0; r < gSize; r++) {
                    if (tiles[o][r].isMainObject()) {
                        tiles[o][r].setColor(tiles[i][t].getColor());
                        tiles[o][r].colorChanger(tiles[i][t].getColor(), tiles[i][t].getTile());
                    }
                }
            }
        }
    }//This allows the program to check multiple times so that no square is left behind

    public static boolean checkWin(Tiles e, int sizes){

            int x = 0;
            boolean getWin = false;
            for (int i = 0; i < sizes; i++) {
                for (int t = 0; t < sizes; t++) {
                    if (tiles[i][t].getColor().equals(tiles[0][0].getColor())) {
                        x++;
                    }

                }
          }
          if(x == (sizes * sizes)){
                getWin = true;
          }
        System.out.println(x);

        return getWin;
    }//Checks to see if the player has won

    //Getters and setters for the button

    public static Button getExitButton() {
        return exitButton;
    }

    public static void setExitButton(Button exitButton) {
        Startup.exitButton = exitButton;
    }

    public static Button getStartButton() {
        return startButton;
    }

    public static void setStartButton(Button startButton) {
        Startup.startButton = startButton;
    }

    //Reader and writers in here

    public static ArrayList getContent(){//Grabs the content from the document

        ArrayList<String> content = new ArrayList<String>();
        String filename = "C:\\Fun\\HighScores.txt";

        while(true) {

            try {
                 content = getFile(filename);
            }catch (FileNotFoundException e){
                System.out.println("Can't find file!");
            }catch (IOException o){
                o.printStackTrace();
            }

            return content;
        }
    }

    private static ArrayList getFile(String filename) throws FileNotFoundException, IOException{

        BufferedReader bufferedReader;
        FileReader fileReader;
        ArrayList content = new ArrayList();

        fileReader = new FileReader(filename);
        bufferedReader = new BufferedReader(fileReader);
        String string = bufferedReader.readLine();

        while (string != null){
            content.add(string);
            string = bufferedReader.readLine();
        }
        return content;
    }

    public static ObservableList<Scores> setHighScores(){
        boolean player = true;
        boolean add = false;
        ObservableList<Scores> scores = FXCollections.observableArrayList();
        String play = "";
        String sco = "";
        try {
            FileReader in = new FileReader("C:\\Fun\\HighScores.txt");
            BufferedReader br = new BufferedReader(in);
            String line = br.readLine();
            while (line != null){
                if(player){
                    play = line;
                    System.out.println(play);
                    player = false;
                } else {
                    sco = line;
                    System.out.println(play);
                    player = true;
                    add = true;
                }
                if (add){
                    scores.add(new Scores(play,sco));
                    add = false;
                }
                System.out.println(line);
                line = br.readLine();
            }
        } catch (Exception e){
            System.out.println(e);
        }
        System.out.println(scores);
        return scores;
    }//Code for reading the files for the table

}