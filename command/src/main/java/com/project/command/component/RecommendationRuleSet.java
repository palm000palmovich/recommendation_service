package com.project.command.component;


import com.project.command.model.DepositTransactions;
import com.project.command.model.RecommendationsByRules;
import com.project.command.model.Rule;
import com.project.command.model.WithdrawTransactions;
import com.project.command.repository.RecommendationsByRulesRepository;
import com.project.command.repository.RecommendationsConstants;
import com.project.command.repository.RecommendationsRepository;
import com.project.command.repository.RuleRepository;
import org.apache.tomcat.util.digester.Rules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.*;


@Component
public class RecommendationRuleSet {
    @Autowired
    private RecommendationsRepository recommendationsRepository;
    private RecommendationsConstants recommendationsConstants;

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private RecommendationsByRulesRepository recommendationsByRulesRepository;
    private Logger logger = LoggerFactory.getLogger(RecommendationRuleSet.class);


    //главный метод, проверяет все методы-чекеры и подбирает подходящую рекоммендацию
    public List<RecommendationsByRules> recommendationSelection(String userId) {
        List<Rule> ruleOfList = ruleRepository.findAll();

        Map<String, Integer> depositMap = new HashMap<>();
        Map<String, Integer> withdrawMap = new HashMap<>();

        DepositTransactions depositTransactions = recommendationsRepository.getDepositAmountByUserId(userId);
        WithdrawTransactions withdrawTransactions = recommendationsRepository.getWithdrawAmountByUserId(userId);

        //Filling deposit Map
        depositMap.put("DEBIT", depositTransactions.getDebit_amount());
        depositMap.put("SAVING", depositTransactions.getSaving_amount());
        depositMap.put("CREDIT", depositTransactions.getCredit_amount());
        depositMap.put("INVEST", depositTransactions.getInvest_amount());

        //Filling withdraw Map
        withdrawMap.put("DEBIT", withdrawTransactions.getDebit_amount());
        withdrawMap.put("SAVING", withdrawTransactions.getSaving_amount());
        withdrawMap.put("CREDIT", withdrawTransactions.getCredit_amount());
        withdrawMap.put("INVEST", withdrawTransactions.getInvest_amount());

        List<RecommendationsByRules> listOfRecs = new ArrayList<>();

        List<RecommendationsByRules> allRecs = recommendationsByRulesRepository.findAll();
        UUID uuid = UUID.fromString(userId);


        int timeBefore = LocalTime.now().getNano();
        allRecs.parallelStream().forEach(recommendationsByRules -> {
            List<Rule> rulesList = ruleRepository.findByRecommendationProductId(recommendationsByRules.getProduct_id());

            if (checkAllQuerys(userId, rulesList, depositMap, withdrawMap) == true){listOfRecs.add(recommendationsByRules);}
        });
        int timeAfter = LocalTime.now().getNano();
        logger.debug("Runtime: " + (timeAfter - timeBefore));


        return listOfRecs;
    }


    //United method-checker for different querys
    public boolean checkAllQuerys(String userId, List<Rule> rulesList,
                                  Map<String, Integer> depositMap, Map<String, Integer> withdrawMap) {
        boolean flag5 = false, greateFlag = false;
        List<Integer> listForCheckTrueCount = new ArrayList<>();
        int counter = 0;

        //List rules -> rule1, rule2, rule3...rulen
        //rule[i] -> [0]:query; [1]:arguments(list); [3]:negate

        //rulesList отсортирован в закономерности: USER_OF -> ACTIVE_USER_OF ->  TRANSACTION_SUM_COMPARE ->
        // TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW
        Rule rule1 = rulesList.get(0);
        Rule rule2 = rulesList.get(1);
        Rule rule3 = rulesList.get(2);

        if (rule1.getQuery().equals("USER_OF")){
            flag5 = userOfCheck(rule1.getArguments(), rule1.isNegate(),
                    depositMap, withdrawMap);
            if (flag5){listForCheckTrueCount.add(1);}
            logger.info("USER_OF checking result: " + flag5);
            counter++;
        } else if (rule1.getQuery().equals("ACTIVE_USER_OF")) {
            flag5 = activeUserOfCheck(userId, rule1.getArguments(), rule1.isNegate());
            if (flag5){listForCheckTrueCount.add(1);}
            logger.info("ACTIVE_USER_OF checking result: " + flag5);
            counter++;
        }

        if (rule2.getQuery().equals("ACTIVE_USER_OF")){
            flag5 = activeUserOfCheck(userId, rule2.getArguments(), rule2.isNegate());
            if (flag5){listForCheckTrueCount.add(1);}
            logger.info("ACTIVE_USER_OF checking result: " + flag5);
            counter++;
        } else if (rule2.getQuery().equals("TRANSACTION_SUM_COMPARE")) {
            flag5 = transactionSumCompareCheck(rule2.getArguments(), rule2.isNegate(),
                    depositMap, withdrawMap);
            if (flag5){listForCheckTrueCount.add(1);}
            logger.info("TRANSACTION_SUM_COMPARE checking result: " + flag5);
            counter++;
        }

        if (rule3.getQuery().equals("TRANSACTION_SUM_COMPARE")){
            flag5 = transactionSumCompareCheck(rule3.getArguments(), rule3.isNegate(),
                    depositMap, withdrawMap);
            if (flag5){listForCheckTrueCount.add(1);}
            logger.info("TRANSACTION_SUM_COMPARE checking result: " + flag5);
            counter++;
        } else if (rule3.getQuery().equals("TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW")){
            flag5 = compareTransactionsByProduct(rule3.getArguments(), rule3.isNegate(),
                    depositMap, withdrawMap);
            if (flag5){listForCheckTrueCount.add(1);}
            logger.info("TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW checking result: " + flag5);
            counter++;
        }

        logger.info("Count of true puncts" + listForCheckTrueCount.toString());

        if (counter == 3) {
            if (listForCheckTrueCount.size() == 3) {
                greateFlag = true;
            }
        }

        //Если true, то будем добавлять в список реков для юзера
        return greateFlag;
    }


    //USER_OF
    public boolean userOfCheck(List<String> arguments, boolean negate,
                               Map<String, Integer> depositMap, Map<String, Integer> withdrawMap) {
        boolean flag1 = false;


        int prodSum = depositMap.get(arguments.get(0)) + withdrawMap.get(arguments.get(0));

        if (prodSum > 0 && negate == false) {
            flag1 = true;
        }
        if (prodSum == 0 && negate == true) {
            flag1 = true;
        }

        return flag1;
    }

    //ACTIVE_USER_OF
    public boolean activeUserOfCheck(String userId, List<String> arguments, boolean negate) {
        boolean flag2 = false;


        int countOfTransactions = recommendationsRepository.getCountTransactionsByProductName(userId, arguments.get(0));

        if (countOfTransactions >= 5 && negate == false) {
            flag2 = true;
        }
        if (countOfTransactions == 0 && negate == true) {
            flag2 = true;
        }

        return flag2;
    }


    //TRANSACTION_SUM_COMPARE
    public boolean transactionSumCompareCheck(List<String> arguments, boolean negate,
                                              Map<String, Integer> depositMap, Map<String, Integer> withdrawMap) {

        boolean flag3 = false;
        int amount = 0;

        String transactionType = arguments.get(1);
        switch (transactionType) {   //Определяем сумму по транзакции
            case "DEPOSIT":
                amount = depositMap.get(arguments.get(0));
            case "WITHDRAW":
                amount = withdrawMap.get(arguments.get(0));
        }

        if (transactionType.equals("DEPOSIT")) {
            amount = depositMap.get(arguments.get(0));
        }
        if (transactionType.equals("WITHDRAW")) {
            amount = withdrawMap.get(arguments.get(0));
        }

        String compareSign = arguments.get(2);
        int compareValue = Integer.parseInt(arguments.get(3));


        if (negate == false) {
            if (compareSign.equals(">")) {
                flag3 = (amount > compareValue);
            }
            if (compareSign.equals("=")) {
                flag3 = (amount == compareValue);
            }
            if (compareSign.equals("<")) {
                flag3 = (amount < compareValue);
            }
        }

        if (negate == true) {
            if (compareSign.equals(">")) {
                flag3 = (amount <= compareValue);
            }
            if (compareSign.equals("=")) {
                flag3 = (amount != compareValue);
            }
            if (compareSign.equals("<")) {
                flag3 = (amount >= compareValue);
            }
        }
        return flag3;
    }


    //TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW
    public boolean compareTransactionsByProduct(List<String> arguments, boolean negate,
                                                Map<String, Integer> depositMap, Map<String, Integer> withdrawMap) {
        boolean flag4 = false;

        String productType = arguments.get(0);

        String compareSign = arguments.get(1);

        int depositAmount = depositMap.get(productType);
        int withdrawAmount = withdrawMap.get(productType);

        if (negate == false) {
            if (compareSign.equals(">")) {
                flag4 = depositAmount > withdrawAmount;
            }
            if (compareSign.equals("=")) {
                flag4 = depositAmount == withdrawAmount;
            }
            if (compareSign.equals("<")) {
                flag4 = depositAmount < withdrawAmount;
            }
        }
        if (negate == true) {
            if (compareSign.equals(">")) {
                flag4 = depositAmount <= withdrawAmount;
            }
            if (compareSign.equals("=")) {
                flag4 = depositAmount != withdrawAmount;
            }
            if (compareSign.equals("<")) {
                flag4 = depositAmount >= withdrawAmount;
            }
        }

        return flag4;
    }
}