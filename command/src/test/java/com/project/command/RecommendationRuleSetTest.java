package com.project.command;

import com.project.command.component.RecommendationRuleSet;
import com.project.command.model.Rule;
import com.project.command.repository.RecommendationsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecommendationRuleSetTest {
    private Map<String, Integer> depositMap;
    private Map<String, Integer> withdrawMap;


    private RecommendationRuleSet recommendationRuleSet;


    @BeforeEach
    public void setUp(){
        depositMap = new HashMap<>();
        withdrawMap = new HashMap<>();
        recommendationRuleSet = new RecommendationRuleSet();

    }

    //USER_OF Test
    @Test
    public void userOfCheckMustBeTrueTest(){
        List<String> arguments = List.of("CREDIT");

        depositMap.put("DEBIT", 1000);
        depositMap.put("SAVING", 100);
        depositMap.put("CREDIT", 2000);
        depositMap.put("INVEST", 30000);

        withdrawMap.put("DEBIT", 300);
        withdrawMap.put("SAVING", 450);
        withdrawMap.put("CREDIT", 600);
        withdrawMap.put("INVEST", 210);

        boolean actual = recommendationRuleSet.userOfCheck(arguments, false, depositMap, withdrawMap);
        boolean expected = true;

        assertEquals(expected, actual);
    }
    @Test
    public void userOfCheckMustBeFalseTest(){
        List<String> arguments = List.of("CREDIT");

        depositMap.put("DEBIT", 1000);
        depositMap.put("SAVING", 100);
        depositMap.put("CREDIT", 2000);
        depositMap.put("INVEST", 30000);

        withdrawMap.put("DEBIT", 300);
        withdrawMap.put("SAVING", 450);
        withdrawMap.put("CREDIT", 600);
        withdrawMap.put("INVEST", 210);

        boolean actual = recommendationRuleSet.userOfCheck(arguments, true, depositMap, withdrawMap);
        boolean expected = false;

        assertEquals(expected, actual);
    }
    @Test
    public void userOfCheckMustBeTrue2Test(){
        List<String> arguments = List.of("CREDIT");

        depositMap.put("DEBIT", 1000);
        depositMap.put("SAVING", 100);
        depositMap.put("CREDIT", 0);
        depositMap.put("INVEST", 30000);

        withdrawMap.put("DEBIT", 300);
        withdrawMap.put("SAVING", 450);
        withdrawMap.put("CREDIT", 0);
        withdrawMap.put("INVEST", 210);

        boolean actual = recommendationRuleSet.userOfCheck(arguments, true, depositMap, withdrawMap);
        boolean expected = true;

        assertEquals(expected, actual);
    }
    @Test
    public void userOfCheckMustBeFalse2Test(){
        List<String> arguments = List.of("CREDIT");

        depositMap.put("DEBIT", 1000);
        depositMap.put("SAVING", 100);
        depositMap.put("CREDIT", 1);
        depositMap.put("INVEST", 30000);

        withdrawMap.put("DEBIT", 300);
        withdrawMap.put("SAVING", 450);
        withdrawMap.put("CREDIT", 1);
        withdrawMap.put("INVEST", 210);

        boolean actual = recommendationRuleSet.userOfCheck(arguments, true, depositMap, withdrawMap);
        boolean expected = false;

        assertEquals(expected, actual);
    }

    //TRANSACTION_SUM_COMPARE
    @Test
    public void transactionSumCompareCheckMustBeTrueTest() {

        depositMap.put("DEBIT", 10001);
        depositMap.put("SAVING", 100);
        depositMap.put("CREDIT", 2300);
        depositMap.put("INVEST", 3000);

        withdrawMap.put("DEBIT", 3000);
        withdrawMap.put("SAVING", 1000);
        withdrawMap.put("CREDIT", 0);
        withdrawMap.put("INVEST", 210);

        List<List<String>> arguments = List.of(List.of("DEBIT","DEPOSIT", ">", "1000"),
                List.of("SAVING","WITHDRAW", "=", "1000"),
                List.of("SAVING","DEPOSIT", "<", "1000"),

                List.of("CREDIT","DEPOSIT", ">", "10000"),
                List.of("DEBIT","WITHDRAW", "<", "1000"),
                List.of("INVEST","DEPOSIT", "<", "1000"));

        for (int i = 0; i < arguments.size()/2; i++){
            boolean actual1 = recommendationRuleSet.transactionSumCompareCheck(arguments.get(i), false, depositMap, withdrawMap);
            System.out.println(i + ")Arguments: " + arguments.get(i).toString() + " actual: " + actual1);
            assertTrue(actual1);

        }

        for (int i = arguments.size()/2; i < arguments.size(); i++){
            boolean actual2 = recommendationRuleSet.transactionSumCompareCheck(arguments.get(i), true, depositMap, withdrawMap);
            System.out.println(i + ")Arguments: " + arguments.get(i).toString() + " actual: " + actual2);
            assertTrue(actual2);
        }
    }

    //TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW
    @Test
    public void compareTransactionsByProductMustBeTrue1Test(){
        depositMap.put("DEBIT", 10001);
        depositMap.put("SAVING", 100);
        depositMap.put("CREDIT", 2300);
        depositMap.put("INVEST", 3000);

        withdrawMap.put("DEBIT", 3000);
        withdrawMap.put("SAVING", 1000);
        withdrawMap.put("CREDIT", 2300);
        withdrawMap.put("INVEST", 210);

        List<List<String>> arguments = List.of(List.of("DEBIT", ">"),
                List.of("SAVING", "<"),
                List.of("CREDIT", "="),
                List.of("INVEST", ">"));

        for (int i = 0; i < arguments.size(); i++){
            boolean actual = recommendationRuleSet.compareTransactionsByProduct(arguments.get(i), false, depositMap, withdrawMap);
            System.out.println(i + ")Arguments: " + arguments.get(i).toString() + " actual: " + actual);
            assertTrue(actual);
        }
    }

    @Test
    public void compareTransactionsByProductMustBeTrue2Test(){
        depositMap.put("DEBIT", 1000);
        depositMap.put("SAVING", 1001);
        depositMap.put("CREDIT", 2300);
        depositMap.put("INVEST", 30);

        withdrawMap.put("DEBIT", 3000);
        withdrawMap.put("SAVING", 1000);
        withdrawMap.put("CREDIT", 2301);
        withdrawMap.put("INVEST", 210);

        List<List<String>> arguments = List.of(List.of("DEBIT", ">"),
                List.of("SAVING", "<"),
                List.of("CREDIT", "="),
                List.of("INVEST", ">"));

        int timeBefore1 = LocalTime.now().getNano();
        arguments.parallelStream().forEach(arg -> {
                    boolean actual = recommendationRuleSet.compareTransactionsByProduct(arg, true, depositMap, withdrawMap);
                    System.out.println("Arguments: " + arg.toString() + " actual: " + actual);
                    assertTrue(actual);
                }
                );
        int timeAfter1 = LocalTime.now().getNano();
        System.out.println("Run time: " + (timeAfter1 - timeBefore1) + " nanoSecs");
    }


}
