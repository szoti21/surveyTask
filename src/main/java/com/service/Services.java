package main.java.com.service;

import main.java.com.io.FileLoader;
import main.java.com.model.Member;
import main.java.com.model.Participation;
import main.java.com.model.Survey;

import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

public class Services {
    private FileLoader fileLoader = new FileLoader();

    private Map<Integer, Member> memberMap = fileLoader.readMembers();
    private List<Participation> participationList = fileLoader.readParticipation();
    private Map<Integer, String> statusMap = fileLoader.readStatuses();
    private Map<Integer, Survey> surveyMap = fileLoader.readSurveys();

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
        int completed = participationList.stream()
                .filter(p -> p.getMemberId() == memberId && statusMap.get(p.getStatus()).equals("Completed"))
                .mapToInt(p -> surveyMap.get(p.getSurveyId()).getCompletionPoints())
                .sum();

        int filtered = participationList.stream()
                .filter(p -> p.getMemberId() == memberId && statusMap.get(p.getStatus()).equals("Filtered"))
                .mapToInt(p -> surveyMap.get(p.getSurveyId()).getFilteredPoints())
                .sum();

        return completed + filtered;
    }

    public List<Member> getMembersBySurveyNotCompleted(int surveyId){
        return participationList.stream()
                .filter(p -> p.getSurveyId() == surveyId && (statusMap.get(p.getStatus()).equals("Not Asked") || statusMap.get(p.getStatus()).equals("Rejected")))
                .filter(p -> memberMap.get(p.getMemberId()).isActive())
                .map(p -> memberMap.get(p.getMemberId()))
                .distinct()
                .toList();
    }

    public void getSurveyStatistics(){
        for (int i : surveyMap.keySet()){

            long completions = participationList.stream()
                    .filter(p -> p.getSurveyId() == i && (statusMap.get(p.getStatus()).equals("Completed")))
                    .map(Participation::getMemberId)
                    .count();

            long filters = participationList.stream()
                    .filter(p -> p.getSurveyId() == i && (statusMap.get(p.getStatus()).equals("Filtered")))
                    .map(Participation::getMemberId)
                    .count();

            long rejections = participationList.stream()
                    .filter(p -> p.getSurveyId() == i && (statusMap.get(p.getStatus()).equals("Rejected")))
                    .mapToInt(Participation::getMemberId)
                    .count();
            OptionalDouble avgLength = participationList.stream()
                    .filter(p -> p.getSurveyId() == i && p.getLength() != null)
                    .mapToInt(Participation::getLength)
                    .average();


            System.out.println("id: " + i + " name: " + surveyMap.get(i).getName() + " completions: " + completions + " Filters: " + filters + " Rejections: " + rejections + " Average completion time: " + Math.round(avgLength.getAsDouble()));

        }
    }
}
