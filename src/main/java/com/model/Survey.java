package main.java.com.model;

public class Survey {
    private int surveyId;
    private String name;
    private int expectedCompletes;
    private int completionPoints;
    private int filteredPoints;

    public Survey(int surveyId, String name, int expectedCompletes, int completionPoints, int filteredPoints) {
        this.surveyId = surveyId;
        this.name = name;
        this.expectedCompletes = expectedCompletes;
        this.completionPoints = completionPoints;
        this.filteredPoints = filteredPoints;
    }

    public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExpectedCompletes() {
        return expectedCompletes;
    }

    public void setExpectedCompletes(int expectedCompletes) {
        this.expectedCompletes = expectedCompletes;
    }

    public int getCompletionPoints() {
        return completionPoints;
    }

    public void setCompletionPoints(int completionPoints) {
        this.completionPoints = completionPoints;
    }

    public int getFilteredPoints() {
        return filteredPoints;
    }

    public void setFilteredPoints(int filteredPoints) {
        this.filteredPoints = filteredPoints;
    }

    @Override
    public String toString() {
        return "Survey{" +
                "surveyId=" + surveyId +
                ", name='" + name + '\'' +
                ", expectedCompletes=" + expectedCompletes +
                ", completionPoints=" + completionPoints +
                ", filteredPoints=" + filteredPoints +
                '}';
    }
}
