/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commentsFrontEnd;

import business.AnalyzedComment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.tone_analyzer.v3.model.ToneAnalysis;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
        //System.out.print(node.get("tone_id\\").asText());
        //Text title = new Text(node.get("tone_id\\").asText());
        
        
        Label comment = new Label("No Tones Detected");
        try {
            comment = new Label(mapper.writerWithDefaultPrettyPrinter().
                    writeValueAsString(content.getText()));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(AnalysisPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        comment.setWrapText(true);
        comment.setPrefWidth(500);
        VBox commentBox = new VBox();
        commentBox.getChildren().add(comment);
        commentBox.setSpacing(20);
        commentBox.getStyleClass().add("commentBorder");

        this.getChildren().add(commentBox);
        commentBox.setAlignment(Pos.CENTER_LEFT);

    }
    private void parseJson(){
        try {
            node = mapper.readTree(tone.toString());
            
        } catch (IOException ex) {
            Logger.getLogger(AnalysisPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    


    private final ToneAnalysis tone;
    private final ObjectMapper mapper = new ObjectMapper();
    private JsonNode node;
}
