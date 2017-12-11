import javafx.scene.Node;
import javafx.scene.control.Button;


public class Tiles {
    private String color;
    private boolean mainObject;
    private Button tile;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Button getTile() {
        return tile;
    }

    public void setTile(Button tile) {
        this.tile = tile;
    }

    public boolean isMainObject() {

        return mainObject;
    }//Super important for the game, checks if the tile is a part of your main object.

    public void setMainObject(boolean mainObject) {
        this.mainObject = mainObject;
    }

    //The tiles are essentially buttons with values

    public Tiles(){
        this.color = "";
        this.mainObject = false;
        this.tile = new Button();

        this.colorChanger(this.color, this.tile);
    }

    public Tiles(String c, boolean mO){
        this.color = c;
        this.mainObject = mO;
        this.tile = new Button();
        this.colorChanger(this.color, this.tile);
    }

    //This is the main method for changing the colors on screen

    public void colorChanger(String color, Button b){
        if(color.equals("Green")){
            b.setMinSize(28,28);
            b.setStyle("-fx-background-radius: 50;"+
                    "-fx-background-color:\n" +
                    "        linear-gradient(#f0ff35, #a9ff00),\n" +
                    "        radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%);\n" +
                    "    -fx-background-radius: 6, 5;\n" +
                    "    -fx-background-insets: 0, 1;\n" +
                    "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );\n" +
                    "    -fx-text-fill: #395306;");
        }
        if(color.equals("Red")) {
            b.setMinSize(30, 30);
            b.setStyle("-fx-background-radius: 50;" +
                    "-fx-background-color:\n" +
                    "        linear-gradient(#ff6359, #d60000),\n" +
                    "        radial-gradient(center 50% -40%, radius 200%, #d60000 45%, #a50000 50%);\n" +
                    "    -fx-background-radius: 6, 5;\n" +
                    "    -fx-background-insets: 0, 1;\n" +
                    "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );\n");
        }
            if(color.equals("Blue")){
            b.setMinSize(30,30);
                b.setStyle("-fx-background-radius: 50;" +
                        "-fx-background-color:\n" +
                        "        linear-gradient(#9397ff, #0008d6),\n" +
                        "        radial-gradient(center 50% -40%, radius 200%, #0008d6 45%, #00004c 50%);\n" +
                        "    -fx-background-radius: 6, 5;\n" +
                        "    -fx-background-insets: 0, 1;\n" +
                        "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );\n");        }
        if (color.equals("Yellow")){
            b.setMinSize(30,30);
            b.setStyle("-fx-background-radius: 50;" +
                    "-fx-background-color:\n" +
                    "        linear-gradient(#fff084, #d6c905),\n" +
                    "        radial-gradient(center 50% -40%, radius 200%, #d6c905 45%, #b08e0b 50%);\n" +
                    "    -fx-background-radius: 6, 5;\n" +
                    "    -fx-background-insets: 0, 1;\n" +
                    "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );\n");        }
        if(color.equals("Purple")){
            b.setMinSize(30,30);
            b.setStyle("-fx-background-radius: 50;" +
                    "-fx-background-color:\n" +
                    "        linear-gradient(#a457ff, #8600ff),\n" +
                    "        radial-gradient(center 50% -40%, radius 200%, #8600ff 45%, #49008b 50%);\n" +
                    "    -fx-background-radius: 6, 5;\n" +
                    "    -fx-background-insets: 0, 1;\n" +
                    "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );\n");        }
        if(color.equals("Pink")){
            b.setMinSize(30,30);
            b.setStyle("-fx-background-radius: 50;" +
                    "-fx-background-color:\n" +
                    "        linear-gradient(#ffac69, #d67819),\n" +
                    "        radial-gradient(center 50% -40%, radius 200%, #d67819 45%, #8b5112 50%);\n" +
                    "    -fx-background-radius: 6, 5;\n" +
                    "    -fx-background-insets: 0, 1;\n" +
                    "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );\n");        }
    }//Also makes them the gradient rounded squares

    public Node getButton() {
        return this.tile;
    }
}
