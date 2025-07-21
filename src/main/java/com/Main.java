package main.java.com;

import main.java.com.model.Member;
import main.java.com.model.Survey;
import main.java.com.service.Services;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        Services sv = new Services();

        List<Member> listOne = sv.getMembersBySurveyCompleted(18);
        for (Member i : listOne){
            System.out.println(i);
        }

        System.out.println();

        List<Survey> listTwo = sv.getSurveysCompletedByMember(4);
        for (Survey i : listTwo){
            System.out.println(i);
        }
        System.out.println();


        System.out.println("points earned by id: " + sv.getPointsByMember(4));

        System.out.println();

        List<Member> listThree = sv.getMembersBySurveyNotCompleted(4);
        for (Member i : listThree){
            System.out.println(i);
        }

        System.out.println();

        sv.getSurveyStatistics();

    }
}