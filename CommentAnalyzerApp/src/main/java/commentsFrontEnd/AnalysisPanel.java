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
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

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
        Label comment = new Label(parseJson());

        comment.setWrapText(true);
        comment.setPrefWidth(500);
        VBox commentBox = new VBox();
        commentBox.getChildren().add(comment);
        commentBox.setSpacing(20);
        commentBox.getStyleClass().add("commentBorder");

        this.getChildren().add(commentBox);
        commentBox.setAlignment(Pos.CENTER_LEFT);

    }
    private String parseJson(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(tone.toString());
        return gson.toJson(je);
    }
    private final ToneAnalysis tone;
    private final ObjectMapper mapper = new ObjectMapper();
}
