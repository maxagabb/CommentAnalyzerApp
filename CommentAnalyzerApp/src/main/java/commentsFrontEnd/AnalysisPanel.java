/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commentsFrontEnd;

import business.AnalyzedComment;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.ibm.watson.tone_analyzer.v3.model.ToneAnalysis;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author mgabb2015
 */
public class AnalysisPanel extends CommentPanel {

    public AnalysisPanel(AnalyzedComment comment) {
        super(comment);
        this.tone = comment.getTone();
    }

    @Override
    public void setPanel() {
        System.out.print(parseJson());
        VBox commentBox = new VBox();
        Text title = new Text("Comment Tones:");
        title.setId("welcome-text");
        commentBox.getChildren().add(title);
        if(getTones()!=null){
            for(ToneNode node: getTones()){
                Text toneInfo = new Text(node.getName()+": "+ node.getScore());
                commentBox.getChildren().add(toneInfo);
            }
        }
        else{
            Text toneInfo = new Text("No tones detected");
            commentBox.getChildren().add(toneInfo);
        }
        commentBox.getChildren().add(setSentenceTable());
        commentBox.setSpacing(20);
        commentBox.getStyleClass().add("commentBorder");

        this.getChildren().add(commentBox);
        commentBox.setAlignment(Pos.CENTER_LEFT);
    }
    
    private List<ToneNode> getTones() {
        JSONArray topArray;
        List<ToneNode> nodeList = new ArrayList();
        try {
            String jsonString = tone.getDocumentTone().getTones().toString();
            topArray = new JSONArray(jsonString);

            for (int i = 0; i < topArray.length(); i++) {
                JSONObject c = topArray.getJSONObject(i);

                String name = c.getString("tone_name");
                float score = c.getFloat("score");
                ToneNode aTone = new ToneNode(name, score);

                nodeList.add(aTone);
            }
            return nodeList;
        } 
        catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    private String parseJson(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(tone.toString());
        return gson.toJson(je);
    }
    private TableView setSentenceTable() {
        TableView<SentenceNode> table = new TableView();
        table.setEditable(true);
        TableColumn<SentenceNode, String> sentence = new TableColumn("Sentence");
        sentence.setCellValueFactory(new PropertyValueFactory<>("text"));
        sentence.setMaxWidth(315);
        sentence.setCellFactory(param -> {
        return new TableCell<SentenceNode, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    this.setText(null);
                    this.setStyle("");
                } else {
                    Text text = new Text(item);
                    text.setStyle("-fx-padding: 5px 30px 5px 5px;"
                            + "-fx-text-alignment:justify;");
                    text.setWrappingWidth(300);
                    this.setPrefHeight(text.getLayoutBounds().getHeight()+10);
                    this.setGraphic(text);
                }
            }
        };
    });
        
        TableColumn<SentenceNode, String> analytical = new TableColumn("Analytical");
        analytical.setCellValueFactory(new PropertyValueFactory<>("analytical"));
        
        TableColumn<SentenceNode, String> anger = new TableColumn("Anger");
        anger.setCellValueFactory(new PropertyValueFactory<>("anger"));
        
        TableColumn<SentenceNode, String> confident = new TableColumn("Confident");
        confident.setCellValueFactory(new PropertyValueFactory<>("confident"));
        
        TableColumn<SentenceNode, String> fear = new TableColumn("Fear");
        fear.setCellValueFactory(new PropertyValueFactory<>("fear"));
        
        TableColumn<SentenceNode, String> tentative = new TableColumn("Tentative");
        tentative.setCellValueFactory(new PropertyValueFactory<>("tentative"));
        
        TableColumn<SentenceNode, String> joy = new TableColumn("Joy");
        joy.setCellValueFactory(new PropertyValueFactory<>("joy"));
        
        TableColumn<SentenceNode, String> sadness = new TableColumn("Sadness");
        sadness.setCellValueFactory(new PropertyValueFactory<>("sadness"));

        JSONArray topArray = new JSONArray();
        List<ToneNode> nodeList;
        List<SentenceNode> sentenceNodes = new ArrayList();
        try {
            String jsonString = null;
            if (tone.getSentencesTone() != null) {
                jsonString = tone.getSentencesTone().toString();
                topArray = new JSONArray(jsonString);
            }

            JSONObject aTone;
            for (int i = 0; i < topArray.length(); i++) {
                JSONObject wholeSentence = new JSONObject(topArray.get(i).toString());
                JSONArray tonesOfSentence = new JSONArray(wholeSentence.get("tones").toString());
                nodeList = new ArrayList();

                for (int j = 0; j < tonesOfSentence.length(); j++) {
                    aTone = tonesOfSentence.getJSONObject(j);
                    String name = aTone.getString("tone_name");
                    float score = aTone.getFloat("score");

                    ToneNode pojo = new ToneNode(name, score);
                    nodeList.add(pojo);

                }
                
                String anaScore = "0";String angerScore = "0";String confScore = "0";
                String fearScore = "0";String tentScore = "0";String joyScore = "0";
                String sadScore = "0";
                String text = wholeSentence.getString("text");
                if (nodeList.contains(new ToneNode("Analytical"))){
                    anaScore = nodeList.get(nodeList.indexOf(new ToneNode("Analytical"))).getScore();
                }
                if (nodeList.contains(new ToneNode("Anger"))){
                    angerScore = nodeList.get(nodeList.indexOf(new ToneNode("Anger"))).getScore();
                }
                if (nodeList.contains(new ToneNode("Confident"))){
                    confScore = nodeList.get(nodeList.indexOf(new ToneNode("Confident"))).getScore();
                }
                if (nodeList.contains(new ToneNode("Fear"))){
                    fearScore = nodeList.get(nodeList.indexOf(new ToneNode("Fear"))).getScore();
                }
                if (nodeList.contains(new ToneNode("Tentative"))){
                    tentScore = nodeList.get(nodeList.indexOf(new ToneNode("Tentative"))).getScore();
                }
                if (nodeList.contains(new ToneNode("Joy"))){
                    joyScore = nodeList.get(nodeList.indexOf(new ToneNode("Joy"))).getScore();
                }
                if (nodeList.contains(new ToneNode("Sadness"))){
                    sadScore = nodeList.get(nodeList.indexOf(new ToneNode("Sadness"))).getScore();
                }
                SentenceNode sentenceTones = new SentenceNode(text,anaScore,angerScore,confScore,
                fearScore,tentScore,joyScore,sadScore);
                sentenceNodes.add(sentenceTones);

            }
            ObservableList<SentenceNode> data = FXCollections.observableList(sentenceNodes);
            table.getColumns().addAll(sentence, analytical,anger,confident,fear,tentative, joy, sadness);
            table.setItems(data);
            
            return table;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public class ToneNode {
        private final SimpleStringProperty  name;
        private final SimpleStringProperty score;
        public ToneNode(String name, float score){
            this.name = new SimpleStringProperty(name);
            this.score = new SimpleStringProperty(String.valueOf(score));
        }
        public ToneNode(String name){
            this.name = new SimpleStringProperty(name);
            this.score = new SimpleStringProperty(String.valueOf(0));
            
        }
        public void setName(String name1) {
            name.set(name1);
        }
        public void setScore(String score1) {
            score.set(score1);
        }
        public String getName() {
            return this.name.get();
        }
        public String getScore() {
            return this.score.get();
        }
        @Override
        public boolean equals(Object other){
            return name.get().equals(((ToneNode)other).getName());
        }
    }
    private final ToneAnalysis tone;
    private final ObjectMapper mapper = new ObjectMapper();
}
