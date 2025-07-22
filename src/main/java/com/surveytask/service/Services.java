package com.surveytask.service;

import com.surveytask.io.FileLoader;
import com.surveytask.model.Member;
import com.surveytask.model.Participation;
import com.surveytask.model.Survey;
import com.surveytask.model.SurveyStatistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Services {

    private Map<Integer, Member> memberMap;
    private List<Participation> participationList;
    private Map<Integer, String> statusMap;
    private Map<Integer, Survey> surveyMap;

    public Services() {
        FileLoader fileLoader = new FileLoader();

        this.memberMap  = fileLoader.readMembers();
        this.participationList = fileLoader.readParticipation();
        this.statusMap = fileLoader.readStatuses();
        this.surveyMap = fileLoader.readSurveys();
    }

    public Map<Integer, Member> getMemberMap() {
        return memberMap;
    }

    public void setMemberMap(Map<Integer, Member> memberMap) {
        this.memberMap = memberMap;
    }

    public List<Participation> getParticipationList() {
        return participationList;
    }

    public void setParticipationList(List<Participation> participationList) {
        this.participationList = participationList;
    }

    public Map<Integer, String> getStatusMap() {
        return statusMap;
    }

    public void setStatusMap(Map<Integer, String> statusMap) {
        this.statusMap = statusMap;
    }

    public Map<Integer, Survey> getSurveyMap() {
        return surveyMap;
    }

    public void setSurveyMap(Map<Integer, Survey> surveyMap) {
        this.surveyMap = surveyMap;
    }

    public List<Member> getMembersBySurveyCompleted(int surveyId){
        return participationList.stream()
                .filter(p -> p.getSurveyId() == surveyId && statusMap.get(p.getStatus()).equals("Completed"))
                .map(p -> memberMap.get(p.getMemberId()))
                .distinct()
                .toList();
    }

    public List<Survey> getSurveysCompletedByMember(int memberId){
        return participationList.stream()
                .filter(p -> p.getMemberId() == memberId && statusMap.get(p.getStatus()).equals("Completed"))
                .map(p -> surveyMap.get(p.getSurveyId()))
                .distinct()
                .toList();
    }

    public int getPointsByMember(int memberId){
        int sum = 0;
        for (Participation participation : participationList){

            if (participation.getMemberId() == memberId && statusMap.get(participation.getStatus()).equals("Completed")){
                sum += surveyMap.get(participation.getSurveyId()).getCompletionPoints();

            } else if (participation.getMemberId() == memberId && statusMap.get(participation.getStatus()).equals("Filtered")){
                sum += surveyMap.get(participation.getSurveyId()).getFilteredPoints();

            }
        }

        return sum;
    }

    public List<Member> getMembersBySurveyNotCompleted(int surveyId){

        List<Member> notCompletedMembersList = new ArrayList<>();

        for (int key : memberMap.keySet()){
            boolean notContacted = true;

            if (memberMap.get(key).isActive()){
                for (Participation participation : participationList){
                    if (participation.getSurveyId() == surveyId && key == participation.getMemberId()){
                        if (statusMap.get(participation.getStatus()).equals("Rejected") || statusMap.get(participation.getStatus()).equals("Not Asked")){
                            notCompletedMembersList.add(memberMap.get(key));
                        }
                        notContacted = false;
                        break;
                    }
                }
                if (notContacted){
                    notCompletedMembersList.add(memberMap.get(key));
                }
            }
        }

        return notCompletedMembersList;
    }

    public List<SurveyStatistics> getSurveyStatistics(){
        List<SurveyStatistics> surveyStatisticsList = new ArrayList<>();

        for (int key : surveyMap.keySet()){
            int completions = 0;
            int filters = 0;
            int rejections = 0;
            float length = 0;

            for (Participation participation : participationList){
                if (participation.getSurveyId() == key){
                    if (statusMap.get(participation.getStatus()).equals("Completed")){
                        completions++;
                        length += participation.getLength();

                    } else if (statusMap.get(participation.getStatus()).equals("Filtered")){
                        filters++;

                    } else if (statusMap.get(participation.getStatus()).equals("Rejected")){
                        rejections++;

                    }
                }
            }

            surveyStatisticsList.add(new SurveyStatistics(surveyMap.get(key).getSurveyId(), surveyMap.get(key).getName(), completions, filters, rejections, length/completions));
        }

        return surveyStatisticsList;
    }
}
