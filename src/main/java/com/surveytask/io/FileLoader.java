package com.surveytask.io;

import com.surveytask.model.Member;
import com.surveytask.model.Participation;
import com.surveytask.model.Survey;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileLoader {

    public static final String FILE_SOURCE = "src/main/resources/";


    //Members
    public Map<Integer, Member> readMembers(){
        Scanner sc;
        try {
            sc = new Scanner(new File(FILE_SOURCE + "OO - 2 - Members.csv"));
        } catch (
                FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Map< Integer, Member> memberMap = new HashMap<>();

        sc.nextLine(); //skip header
        while (sc.hasNextLine()){
            String[] line = sc.nextLine().split(",");
            Member member = new Member(Integer.parseInt(line[0]), line[1], line[2], Integer.parseInt(line[3])==1);
            memberMap.put(member.getMemberId(), member);
        }

        return memberMap;
    }

    //Statuses
    public Map<Integer, String> readStatuses(){
        Scanner sc;
        try {
            sc = new Scanner(new File(FILE_SOURCE + "OO - 2 - Statuses.csv"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Map<Integer, String> statusMap = new HashMap<>();

        sc.nextLine(); //skip header
        while (sc.hasNextLine()){
            String[] line = sc.nextLine().split(",");
            statusMap.put(Integer.parseInt(line[0]), line[1]);
        }

        return statusMap;
    }

    //Surveys
    public Map<Integer, Survey> readSurveys(){
        Scanner sc;
        try {
            sc = new Scanner(new File( FILE_SOURCE+ "OO - 2 - Surveys.csv"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Map<Integer, Survey> surveyMap = new HashMap<>();

        sc.nextLine(); //skip header
        while (sc.hasNextLine()){
            String[] line = sc.nextLine().split(",");
            Survey survey = new Survey(Integer.parseInt(line[0]), line[1], Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]));
            surveyMap.put(survey.getSurveyId(), survey);
        }

        return surveyMap;
    }

    //Participation
    public List<Participation> readParticipation(){
        Scanner sc;
        try {
            sc = new Scanner(new File(FILE_SOURCE + "OO - 2 - Participation.csv"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<Participation> participationList = new ArrayList<>();

        sc.nextLine(); //skip header
        while (sc.hasNextLine()){
            String[] line = sc.nextLine().split(",");
            Integer length = (line.length > 3) ? Integer.parseInt(line[3]) : null;
            Participation participation = new Participation(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]), length);
            participationList.add(participation);
        }
        return participationList;
    }

}
