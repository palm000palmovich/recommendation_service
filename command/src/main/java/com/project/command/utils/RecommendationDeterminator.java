package com.project.command.utils;

import com.project.command.model.Products;
import com.project.command.model.UserDepositTransaction;
import com.project.command.model.UserWithdrawTransaction;
import com.project.command.repository.UsersDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecommendationDeterminator {

    @Autowired
    private UsersDataRepository usersDataRepository;

    private Logger logger = LoggerFactory.getLogger(RecommendationDeterminator.class);

    private Products invest500 = new Products("Invest500",
            "Откройте свой путь к успеху с индивидуальным инвестиционным " +
            "счетом (ИИС) от нашего банка! Воспользуйтесь налоговыми льготами и начните инвестировать с умом. Пополните счет до конца " +
            "года и получите выгоду в виде вычета на взнос в следующем налоговом периоде. Не упустите возможность разнообразить свой портфель, " +
            "снизить риски и следить за актуальными рыночными тенденциями. Откройте ИИС сегодня и станьте ближе к финансовой независимости!");

    private Products topSaving = new Products("Top Saving", "Откройте свою собственную «Копилку» с нашим банком! " +
            "«Копилка» — это уникальный банковский инструмент, который поможет вам легко и удобно накапливать деньги на важные цели. " +
            "Больше никаких забытых чеков и потерянных квитанций — всё под контролем!\n" +
            "Преимущества «Копилки»:\n" +
            "Накопление средств на конкретные цели. Установите лимит и срок накопления, и банк будет автоматически переводить " +
            "определенную сумму на ваш счет.\n" +
            "Прозрачность и контроль. Отслеживайте свои доходы и расходы, контролируйте процесс накопления и корректируйте " +
            "стратегию при необходимости.\n" +
            "Безопасность и надежность. Ваши средства находятся под защитой банка, а доступ к ним возможен только через мобильное " +
            "приложение или интернет-банкинг.\n" +
            "Начните использовать «Копилку» уже сегодня и станьте ближе к своим финансовым целям!");

    private Products simpleCredit = new Products("Простой кредит", "Откройте мир выгодных кредитов с нами!\n" +
            "Ищете способ быстро и без лишних хлопот получить нужную сумму? Тогда наш выгодный кредит — именно то, что вам нужно!" +
            " Мы предлагаем низкие процентные ставки, гибкие условия и индивидуальный подход к каждому клиенту.\n" +
            "Почему выбирают нас:\n" +
            "Быстрое рассмотрение заявки. Мы ценим ваше время, поэтому процесс рассмотрения заявки занимает всего несколько часов.\n" +
            "Удобное оформление. Подать заявку на кредит можно онлайн на нашем сайте или в мобильном приложении.\n" +
            "Широкий выбор кредитных продуктов. Мы предлагаем кредиты на различные цели: покупку недвижимости, автомобиля, образование, " +
            "лечение и многое другое.\n" +
            "Не упустите возможность воспользоваться выгодными условиями кредитования от нашей компании!");


    //Determinator for own recommendations
    public List<Products> determineRecommendation(String user_id){
        logger.debug("Determinator for recs");

        UserWithdrawTransaction withdrawTransaction = usersDataRepository.getUserWithdrawInfo(user_id);
        logger.debug("User's withdraw-transactions: " + withdrawTransaction);
        int debit_amountWithdraw = withdrawTransaction.getDebit_amountWithdraw();
        int invest_amountWithdraw = withdrawTransaction.getInvest_amountWithdraw();
        int credit_amountWithdraw = withdrawTransaction.getCredit_amountWithdraw();
        int saving_amountWithdraw = withdrawTransaction.getSaving_amountWithdraw();

        UserDepositTransaction depositTransaction = usersDataRepository.getUserDepositInfo(user_id);
        logger.debug("User's deposit-transactions: " + depositTransaction);
        int debit_amountDeposit = depositTransaction.getDebit_amountDeposit();
        int invest_amountDeposit = depositTransaction.getInvest_amountDeposit();
        int credit_amountDeposit = depositTransaction.getCredit_amountDeposit();
        int saving_amountDeposit = depositTransaction.getSaving_amountDeposit();

        List<Products> recommendProd = new ArrayList<>();

        //Invest500
        if ((debit_amountDeposit + debit_amountDeposit != 0) &&
                (invest_amountDeposit + invest_amountWithdraw == 0) &&
                (saving_amountDeposit > 1000)){recommendProd.add(invest500);}
        //Top Saving
        if ((debit_amountDeposit + debit_amountWithdraw != 0) &&
                (debit_amountDeposit >= 50.000 || saving_amountDeposit >= 50.000) &&
                debit_amountDeposit > debit_amountWithdraw) {recommendProd.add(topSaving);}
        //Simple credit
        if (credit_amountDeposit + credit_amountWithdraw == 0 &&
                debit_amountDeposit > debit_amountWithdraw &&
                debit_amountWithdraw > 10.000){recommendProd.add(simpleCredit);}

        return recommendProd;
    }
}
