package com.surveytask.service;

import com.surveytask.model.Member;
import com.surveytask.model.Participation;
import com.surveytask.model.Survey;
import com.surveytask.model.SurveyStatistics;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ServicesTest {

    private Services services = new Services();

    private List<Participation> participationList = new ArrayList<>();
    private Map<Integer, Member> membersMap = new HashMap<>();
    private Map<Integer, Survey> surveyMap = new HashMap<>();

    {
        participationList.add(new Participation(21, 6, 4, 20));
        participationList.add(new Participation(3, 6, 4, 20));
        participationList.add(new Participation(3, 7, 4, 20));
        participationList.add(new Participation(4, 6, 3, null));
        participationList.add(new Participation(5, 6, 2, null));

        membersMap.put(3, new Member(3, "name1", "name1@gmail.com", true));
        membersMap.put(4, new Member(4, "name2", "name2@gmail.com",false));
        membersMap.put(5, new Member(5, "name3", "name3@gmail.com",true));
        membersMap.put(21, new Member(21, "name4", "name4@gmail.com",false));
        membersMap.put(42, new Member(42, "name5", "name5@gmail.com",true));
        membersMap.put(43, new Member(43, "name6", "name6@gmail.com",false));

        surveyMap.put(6, new Survey(6, "survey1", 2, 20, 5));
        surveyMap.put(7, new Survey(7, "survey2", 0,30, 15));


        services.setParticipationList(participationList);
        services.setMemberMap(membersMap);
        services.setSurveyMap(surveyMap);
    }

    @Test
    void testGetMembersBySurveyCompleted(){

        List<Member> memberList = new ArrayList<>();
        memberList.add(new Member(21, "name4", "name4@gmail.com",false));
        memberList.add(new Member(3, "name1", "name1@gmail.com",true));


        assertEquals(memberList, services.getMembersBySurveyCompleted(6));

    }

    @Test
    void testGetSurveysCompletedByMember(){

        List<Survey> surveyList = new ArrayList<>();
        surveyList.add(new Survey(6, "survey1", 2,20, 5));
        surveyList.add(new Survey(7, "survey2", 0,30, 15));


        assertEquals(surveyList, services.getSurveysCompletedByMember(3));

    }

    @Test
    void testGetPointsByMember(){

        assertEquals(50, services.getPointsByMember(3));

    }

    @Test
    void testGetMembersBySurveyNotCompleted(){

        List<Member> memberList = new ArrayList<>();
        memberList.add(new Member(5, "name3", "name3@gmail.com",true));
        memberList.add(new Member(42, "name5", "name5@gmail.com",true));

        assertEquals(memberList, services.getMembersBySurveyNotCompleted(6));

    }

    @Test
    void testGetSurveyStatistics(){

        List<SurveyStatistics> surveyStatisticsList = new ArrayList<>();
        surveyStatisticsList.add(new SurveyStatistics(6, "survey1", 2,1, 1, 20));
        surveyStatisticsList.add(new SurveyStatistics(7, "survey2", 1,0, 0, 20));

        assertEquals(surveyStatisticsList, services.getSurveyStatistics());

    }
}
