package com.surveytask.model;

import java.util.Objects;

public class SurveyStatistics {
    private int surveyId;
    private String name;
    private int completions;
    private int filters;
    private int rejections;
    private float averageCompletionTime;

    public SurveyStatistics(int surveyId, String name, int completions, int filters, int rejections, float averageCompletionTime) {
        this.surveyId = surveyId;
        this.name = name;
        this.completions = completions;
        this.filters = filters;
        this.rejections = rejections;
        this.averageCompletionTime = averageCompletionTime;
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

    public int getCompletions() {
        return completions;
    }

    public void setCompletions(int completions) {
        this.completions = completions;
    }

    public int getFilters() {
        return filters;
    }

    public void setFilters(int filters) {
        this.filters = filters;
    }

    public int getRejections() {
        return rejections;
    }

    public void setRejections(int rejections) {
        this.rejections = rejections;
    }

    public float getAverageCompletionTime() {
        return averageCompletionTime;
    }

    public void setAverageCompletionTime(float averageCompletionTime) {
        this.averageCompletionTime = averageCompletionTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SurveyStatistics that = (SurveyStatistics) o;
        return surveyId == that.surveyId && completions == that.completions && filters == that.filters && rejections == that.rejections && Float.compare(averageCompletionTime, that.averageCompletionTime) == 0 && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surveyId, name, completions, filters, rejections, averageCompletionTime);
    }

    @Override
    public String toString() {
        return "SurveyStatistics{" +
                "surveyId=" + surveyId +
                ", name='" + name + '\'' +
                ", completions=" + completions +
                ", filters=" + filters +
                ", rejections=" + rejections +
                ", averageCompletionTime=" + averageCompletionTime +
                '}';
    }
}
