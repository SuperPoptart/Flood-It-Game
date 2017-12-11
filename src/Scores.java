public class Scores {
    private String name;
    private String score;

    public Scores(){
        this.name = "";
        this.score = "";
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Scores(String nam, String scu){
        this.name = nam;
        this.score = scu;
    }//Stores the scores so they can be set to the text file


}
