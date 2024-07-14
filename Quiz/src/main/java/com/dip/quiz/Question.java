package com.dip.quiz;

public class Question {


    private String question;
    private String trueAnswer;
    private String fakeAnswer1;
    private String fakeAnswer2;
    private String fakeAnswer3;

    public Question(String question, String trueAnswer, String fakeAnswer1,String fakeAnswer2,String fakeAnswer3){
        this.question = question;
        this.trueAnswer = trueAnswer;
        this.fakeAnswer1 = fakeAnswer1;
        this.fakeAnswer2 = fakeAnswer2;
        this.fakeAnswer3 = fakeAnswer3;
    }
    public String getQuestion(){
        return question;
    }
    public String getTrueAnswer(){
        return trueAnswer;
    }
    public String getFakeAnswer1(){
        return fakeAnswer1;
    }
    public String getFakeAnswer2(){
        return fakeAnswer2;
    }
    public String getFakeAnswer3(){
        return fakeAnswer3;
    }

}
