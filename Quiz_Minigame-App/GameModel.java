package com.example.quiz_minigame_app;



        import java.util.List;
        import java.util.Map;
        import java.util.TreeMap;


        import ca.roumani.i2c.Country;
        import ca.roumani.i2c.CountryDB;

public class GameModel {

    private CountryDB db;
    private String question;
    private String answer;
    private String randomAnswer;
    private String[] qandA = new String[]{question, answer, randomAnswer};



    public GameModel(){

        this.db = new CountryDB();

    }

    public String[] qa(){

        List<String> capitals = db.getCapitals();
        int n = capitals.size();
        double index =  Math.random() * (double) n;
        String c = capitals.get((int) index);
        Map<String, Country> data = db.getData();
        Country ref = data.get(c);

        String country = ref.getName();
        String capital = ref.getCapital();
        index =  Math.random() * (double) n;
        c = capitals.get((int) index);
        data = db.getData();
        ref = data.get(c);
        String randomCountry = ref.getName();
        String randomCapital = ref.getCapital();



        if(Math.random() < 0.5){
            question = "What is the capital of " + country + "?\n";
            answer = capital;
            randomAnswer = randomCapital;
            qandA[0] = question;
            qandA[1] = answer;
            qandA[2] = randomAnswer;


        }else{
            question = capital + " is the capital of?\n";
            answer = country;
            randomAnswer = randomCountry;
            qandA[0] = question + " ";
            qandA[1] = answer;
            qandA[2] = randomAnswer;

        }

        return qandA;
    }
    public static void main(String[] args) {
        GameModel wow = new GameModel();
        String[] biscuits = wow.qa();
        System.out.println(biscuits[0] + " " + biscuits[1]);
        int index = 0;
        String questionAndAnswer = biscuits[0] + "split" + biscuits[1] + "split" + biscuits[2];
        String[] split = questionAndAnswer.split("split");
        System.out.println(split[0] + "         " + split[1] + "      " + split[2]);
    }
}
