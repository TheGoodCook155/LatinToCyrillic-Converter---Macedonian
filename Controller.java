package sample;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Controller {
    @FXML
    private GridPane gridPane;
    @FXML
    private Button buttonChooseFile;
    @FXML
    private Button saveButton;
    @FXML
    private Label doneLabel;


    private String loadLocation = null;
    private String saveLocation = null;
    private String convertedLine = null;
    //    Map<Integer, Character> latinicniBukvi = new HashMap<>();
    Map<Integer, Character> kirilicniBukvi = new HashMap<>();
    ArrayList<Integer> indexesOfSpecialCases = new ArrayList<>();

    String loadString = null;

    public void initialize() {
        if(loadLocation == null){
            saveButton.setDisable(true);
        }

//        latin letters (for testing)
//        char bukva = 'a';
//
//
//
//        for (int i = 0; i <= 126; i++){
//             bukva = (char) i;
//            latinicniBukvi.put(i, Character.valueOf(bukva));
//        }

        //cyrillic letters

        kirilicniBukvi.put(125,'ѓ');
        kirilicniBukvi.put(125,'Ѓ');
        kirilicniBukvi.put(121,'ѕ');
        kirilicniBukvi.put(89,'Ѕ');
        kirilicniBukvi.put(106,'ј');
        kirilicniBukvi.put(74,'Ј');
        kirilicniBukvi.put(113,'љ');
        kirilicniBukvi.put(81,'Љ');
        kirilicniBukvi.put(113,'љ');
        kirilicniBukvi.put(81,'Љ');
        kirilicniBukvi.put(119,'њ');
        kirilicniBukvi.put(87,'Њ');
        kirilicniBukvi.put(39,'ќ');
        kirilicniBukvi.put(39,'Ќ');
        kirilicniBukvi.put(120,'џ');
        kirilicniBukvi.put(88,'Џ');
        kirilicniBukvi.put(97,'а');
        kirilicniBukvi.put(98,'б');
        kirilicniBukvi.put(66,'Б');
        kirilicniBukvi.put(99,'ц');
        kirilicniBukvi.put(67,'Ц');
        kirilicniBukvi.put(100,'д');
        kirilicniBukvi.put(68,'Д');
        kirilicniBukvi.put(101,'е');
        kirilicniBukvi.put(69,'Е');
        kirilicniBukvi.put(102,'ф');
        kirilicniBukvi.put(70,'Ф');
        kirilicniBukvi.put(103,'г');
        kirilicniBukvi.put(71,'Г');
        kirilicniBukvi.put(104,'х');
        kirilicniBukvi.put(72,'Х');
        kirilicniBukvi.put(105,'и');
        kirilicniBukvi.put(73,'И');
        kirilicniBukvi.put(107,'к');
        kirilicniBukvi.put(75,'К');
        kirilicniBukvi.put(108,'л');
        kirilicniBukvi.put(76,'Л');
        kirilicniBukvi.put(109,'м');
        kirilicniBukvi.put(77,'М');
        kirilicniBukvi.put(110,'н');
        kirilicniBukvi.put(78,'Н');
        kirilicniBukvi.put(111,'о');
        kirilicniBukvi.put(112,'п');
        kirilicniBukvi.put(80,'П');
        kirilicniBukvi.put(114,'р');
        kirilicniBukvi.put(82,'Р');
        kirilicniBukvi.put(115,'с');
        kirilicniBukvi.put(83,'С');
        kirilicniBukvi.put(116,'т');
        kirilicniBukvi.put(84,'Т');
        kirilicniBukvi.put(117,'у');
        kirilicniBukvi.put(85,'у');
        kirilicniBukvi.put(122,'з');
        kirilicniBukvi.put(90,'З');
        kirilicniBukvi.put(123,'ш');
        kirilicniBukvi.put(123,'Ш');
        kirilicniBukvi.put(121,'ѕ');
        kirilicniBukvi.put(89,'Ѕ');
        kirilicniBukvi.put(86,'В');
        kirilicniBukvi.put(118,'в');
        kirilicniBukvi.put(32,' ');
        //======================
//        kirilicniBukvi.put(39,'\'');
        kirilicniBukvi.put(47,'/');
//        kirilicniBukvi.put(123,'{');
        kirilicniBukvi.put(124,'|');
//        kirilicniBukvi.put(125,'}');
        kirilicniBukvi.put(126,'~');
        kirilicniBukvi.put(33,'!');
        kirilicniBukvi.put(34,'"');
        kirilicniBukvi.put(35,'#');
        kirilicniBukvi.put(36,'$');
        kirilicniBukvi.put(37,'%');
        kirilicniBukvi.put(38,'&');
        kirilicniBukvi.put(40,'(');
        kirilicniBukvi.put(41,')');
        kirilicniBukvi.put(42,'*');
        kirilicniBukvi.put(43,'+');
        kirilicniBukvi.put(44,',');
        kirilicniBukvi.put(45,'-');
        kirilicniBukvi.put(46,'.');
        kirilicniBukvi.put(58,':');
        kirilicniBukvi.put(59,';');
        kirilicniBukvi.put(60,'<');
        kirilicniBukvi.put(61,'=');
        kirilicniBukvi.put(62,'>');
        kirilicniBukvi.put(63,'?');
        kirilicniBukvi.put(64,'@');
        kirilicniBukvi.put(91,'[');
        kirilicniBukvi.put(92,'\\');
        kirilicniBukvi.put(93,']');
        kirilicniBukvi.put(94,'^');
        kirilicniBukvi.put(95,'_');
        kirilicniBukvi.put(96,'`');
        kirilicniBukvi.put(123,'{');
        kirilicniBukvi.put(125,'}');
        kirilicniBukvi.put(48,'0');
        kirilicniBukvi.put(49,'1');
        kirilicniBukvi.put(50,'2');
        kirilicniBukvi.put(51,'3');
        kirilicniBukvi.put(52,'4');
        kirilicniBukvi.put(53,'5');
        kirilicniBukvi.put(54,'6');
        kirilicniBukvi.put(55,'7');
        kirilicniBukvi.put(56,'8');
        kirilicniBukvi.put(57,'9');



    }



    public void clickButton() throws IOException {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Одбери фајл");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text", "*.txt"));

        File file = chooser.showOpenDialog(gridPane.getScene().getWindow());


        if (file != null) {

            this.loadLocation = file.getPath();
            saveButton.setDisable(false);
        } else {
            System.out.println("Cancelled");
        }


    }




    public void saveList(ActionEvent event) throws IOException {


        FileChooser chooser = new FileChooser();
        chooser.setTitle("Сними фајл");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text", "*.txt"));
        File file = chooser.showSaveDialog(gridPane.getScene().getWindow());
        saveLocation = file.getPath();
        saveButton.setDisable(true);
        buttonChooseFile.setDisable(true);


        //==========
        //background

        Background write = new Background();
        new Thread(write).start();
        doneLabel.setFont(Font.font(13));
        write.setOnRunning(event1 -> {
            doneLabel.setText("Конвертирам...");
        });
        write.setOnSucceeded(event1 -> {
            doneLabel.setText("Готово!");
        });
        Platform.exit();
    }


    private class Background<String> extends Task<Void> {

        @Override
        protected Void call() throws Exception {

            BufferedReader reader = new BufferedReader(new FileReader(loadLocation));
            BufferedWriter br = new BufferedWriter(new FileWriter(saveLocation));
            while ((loadString = reader.readLine()) != null) {
                convertedLine = convertToCyrillic(loadString);
                try {
                    br.write(convertedLine);
                    br.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    br.flush();
                }
            }
            return null;
        }

    }

    private String convertToCyrillic(String loadString) {

        String toReturn = "";
        String checkAgainstSpecialCases = loadString;


        char[] chars = checkAgainstSpecialCases.toCharArray();
        StringBuilder sb = new StringBuilder();
        String strToWrite = "";
        int intChar = 0;
        for (int i = 0; i < chars.length; i++){
            intChar = chars[i];
            if(!indexesOfSpecialCases.contains(intChar)){
                intChar = chars[i];
            }else{
                intChar = chars[i+2];
            }
            if(kirilicniBukvi.get(intChar) != null) {
                char writeChar = kirilicniBukvi.get(intChar);
                strToWrite = Character.toString(writeChar);
                sb.append(strToWrite);
            }
        }
        String workingString = sb.toString();
        toReturn = checkSpecialCases(workingString);
        System.out.println("String to return = " + toReturn);
        return toReturn;

    }

    public static String checkSpecialCases(String word){
        String toReturn = "";
        word = word;
        String changedString = "";

        if (word.contains("гј")) {
            changedString = word.replace("гј", "ѓ");
            word = changedString;
        }
        if (word.contains("Гј")) {
            changedString = word.replace("Гј", "Ѓ");
            word = changedString;
        }
        if (word.contains("зх")) {
            changedString = word.replace("зх", "ж");
            word = changedString;
        }
        if (word.contains("Зх")) {
            changedString = word.replace("Зх", "Ж");
            word = changedString;
        }
        if (word.contains("дз")) {
            changedString = word.replace("дз", "ѕ");
            word = changedString;
        }
        if (word.contains("Дз")) {
            changedString = word.replace("Дз", "Ѕ");
            word = changedString;
        }
        if (word.contains("зх")) {
            changedString = word.replace("зх", "ж");
            word = changedString;
        }
        if (word.contains("Зх")) {
            changedString = word.replace("Зх", "Ж");
            word = changedString;
        }
        if (word.contains("лј")) {

            changedString = word.replace("лј", "љ");
            word = changedString;
        }
        if (word.contains("Лј")) {

            changedString = word.replace("Лј", "Љ");
            word = changedString;
        }
        if (word.contains("нј")) {

            changedString = word.replace("нј", "њ");
            word = changedString;
        }
        if (word.contains("Нј")) {

            changedString = word.replace("Нј", "Њ");
            word = changedString;
        }
        if (word.contains("кј")) {

            changedString = word.replace("кј", "ќ");
            word = changedString;
        }
        if (word.contains("Кј")) {
            changedString = word.replace("Кј", "Ќ");
            word = changedString;
        }
        if (word.contains("дз")) {

            changedString = word.replace("дз", "ѕ");
            word = changedString;
        }
        if (word.contains("Дз")) {

            changedString = word.replace("Дз", "Ѕ");
            word = changedString;
        }
        if (word.contains("сх")) {
            changedString = word.replace("сх", "ш");
            word = changedString;
        }
        if (word.contains("Сх")) {
            changedString = word.replace("Сх", "Ш");
            word = changedString;
        }
        if (word.contains("цх")) {
            changedString = word.replace("цх", "ч");
            word = changedString;
        }
        if (word.contains("Цх")) {
            changedString = word.replace("Цх", "Ч");
            word = changedString;
        }

        toReturn = word;
        return toReturn;
    }

    //different approach
//    public int indexesNotToBeChecked(String word, Character letterA, Character letterB){
//        int indexA = 0;
//        int indexB = 0;
//        char[] wordArray = word.toCharArray();
//        for(int i = 0; i < wordArray.length-1; i++){
//           if(wordArray[i] == letterA && wordArray[i+1] == letterB){
//               indexA = i;
//               indexB = i + 1;
//               indexesOfSpecialCases.add(indexA);
//               indexesOfSpecialCases.add(indexB);
//           }
//        }
//
//
//
//        //test printing ok
//        for (int i = 0; i < indexesOfSpecialCases.size(); i++){
//            System.out.println("Indeksite se " + indexesOfSpecialCases.get(i));
//        }
//        return indexA;
//    }

}
