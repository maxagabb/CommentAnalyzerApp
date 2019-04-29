/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibm.watson.tone_analyzer.v3;

import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.tone_analyzer.v3.model.ToneOptions;
import java.util.ArrayList;

/**
 *
 * @author mgabb2015
 */
public class Analyzer {

    public ArrayList<ToneAnalysis> analyze(ArrayList<String> comments) {
        this.authorize();
        ArrayList<ToneAnalysis> result = new ArrayList();
        ArrayList<ToneOptions> options = new ArrayList();
        comments.stream().map((comment) -> new ToneOptions.Builder()
                .text(comment)
                .build()).forEachOrdered((toneOptions) -> {
            options.add(toneOptions);
        });
        options.stream().map((option) -> service.tone(option).execute().
                getResult()).forEachOrdered((tone) -> {
            result.add(tone);
        });
        return result;
       /* // Call the service and get the tone
        ToneOptions toneOptions = new ToneOptions.Builder()
                .text(text)
                .build();
        ToneAnalysis tone = service.tone(toneOptions).execute().getResult();
        System.out.println(tone);
        return tone;*/
        
        
        /*// Call the service and get the tone
        ToneOptions toneOptions = new ToneOptions.Builder()
                .text(text)
                .build();
        comments.stream().forEach((comment)->service.tone(toneOptions).execute().getResult());
        ToneAnalysis tone = service.tone(toneOptions).execute().getResult();
        return comments;*/
    }

    public void authorize() {
        if (service == null) {
            service = new ToneAnalyzer("2017-09-21");
            IamOptions options = new IamOptions.Builder()
                    .apiKey("yourKeyHere")
                    .build();
            service.setIamCredentials(options);
        }
        else{
        }

    }
    private ToneAnalyzer service;
}
