package com.project.command.component;

import com.project.command.model.RecommendationDTO;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.project.command.repository.RecommendationsConstants.*;

@Component
public abstract class RecommendationRuleSetImplToSave implements RecommendationRuleSet{

    @Override
    public Optional<RecommendationDTO> matchesRules(String user_id) {
        if (atLeastOneProductDebit(user_id) && sumAddDebitOrSumAddSavingMoreThanFiftythousandRub(user_id) && addDebitMoreThanSpendDebit(user_id)) {
            return Optional.of(new RecommendationDTO(PRODUCT_NAME_SAVING, PRODUCT_ID_SAVING, PRODUCT_TEXT_SAVING));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Пользователь использует как минимум один продукт с типом DEBIT.
     */
    @Query(value = "SELECT EXISTS (SELECT 1 FROM transactions WHERE user_id = :userId AND type = 'DEBIT')")
    public abstract boolean atLeastOneProductDebit(@Param("userId") String userId);


    /**
     * Сумма пополнений по всем продуктам типа DEBIT больше или равна 50 000 ₽ ИЛИ Сумма пополнений по всем продуктам типа SAVING больше или равна 50 000 ₽.
     */
    public abstract boolean sumAddDebitOrSumAddSavingMoreThanFiftythousandRub(@Param("userId") String userId);

    /**
     * Сумма пополнений по всем продуктам типа DEBIT больше, чем сумма трат по всем продуктам типа DEBIT.
     */
    public abstract boolean addDebitMoreThanSpendDebit(@Param("userId") String userId);
}



