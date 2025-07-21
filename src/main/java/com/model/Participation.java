package main.java.com.model;

public class Participation {
    private int memberId;
    private int surveyId;
    private int status;
    private Integer length;

    public Participation(int memberId, int surveyId, int status, Integer length) {
        this.memberId = memberId;
        this.surveyId = surveyId;
        this.status = status;
        this.length = length;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
