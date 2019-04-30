/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commentsFrontEnd;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author mgabb2015
 */
    public class SentenceNode {
        private final SimpleStringProperty text;
        private final SimpleStringProperty analytical;
        private final SimpleStringProperty anger;
        private final SimpleStringProperty confident;
        private final SimpleStringProperty fear;
        private final SimpleStringProperty tentative;
        private final SimpleStringProperty joy;
        private final SimpleStringProperty sadness;
        

        public SentenceNode(String text,String analytical, String anger,
            String confident, String fear, String tentative, String joy, String sadness) {
            this.text = new SimpleStringProperty(text);
            this.analytical = new SimpleStringProperty(analytical);
            this.anger = new SimpleStringProperty(anger);
            this.confident = new SimpleStringProperty(confident);
            this.fear = new SimpleStringProperty(fear);
            this.tentative = new SimpleStringProperty(tentative);
            this.joy = new SimpleStringProperty(joy);
            this.sadness = new SimpleStringProperty(sadness);
        }

        public void setText(String input) {
            text.set(input);
        }
        public void setAnalytical(String input) {
            analytical.set(input);
        }
        public void setAnger(String input) {
            anger.set(input);
        }       
        public void setConfident(String input) {
            confident.set(input);
        }          
        public void setFear(String input) {
            fear.set(input);
        }       
        public void setTentative(String input) {
            tentative.set(input);
        }       
        public void setJoy(String input) {
            joy.set(input);
        }       
        public void setSadness(String input) {
            sadness.set(input);
        }   
        // do for rest of the json row data

        public String getText() {
            return text.get();
        }
        public String getAnalytical() {
            return analytical.get();
        }
        public String getAnger() {
            return anger.get();
        }       
        public String getConfident() {
            return confident.get();
        }          
        public String getFear() {
            return fear.get();
        }       
        public String getTentative() {
            return tentative.get();
        }      
        public String getJoy() {
            return joy.get();
        }       
        public String getSadness() {
            return sadness.get();
        }  
    }
