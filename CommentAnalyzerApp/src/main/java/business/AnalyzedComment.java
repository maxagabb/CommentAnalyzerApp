/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import com.ibm.watson.tone_analyzer.v3.model.ToneAnalysis;

/**
 *
 * @author mgabb2015
 */
public class AnalyzedComment extends Content{
    public AnalyzedComment(ToneAnalysis tone){
        this.tone = tone;
        this.text = tone.toString();
    }
    public ToneAnalysis getTone(){
        return tone;
    }
    
    private final ToneAnalysis tone;
}
