package com.project.command.component;

import com.project.command.model.RecommendationDTO;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.project.command.repository.RecommendationsConstants.*;

@Component
public abstract class RecommendationRuleSetImplToInvest implements RecommendationRuleSet {

    @Override
    public Optional<RecommendationDTO> matchesRules(String user_id) {
        if (atLeastOneProductDebit(user_id) && noOneProductInvest(user_id) && sumOfSavingMoreThanOneThousandRub(user_id) > 1000) {
            return Optional.of(new RecommendationDTO(PRODUCT_NAME_INVEST, PRODUCT_ID_INVEST, PRODUCT_TEXT_INVEST));
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
     * Пользователь не использует продукты с типом INVEST.
     */
    @Query(value = "SELECT NOT EXISTS (SELECT 1 FROM transactions WHERE user_id = :userId AND type = 'INVEST')")
    public abstract boolean noOneProductInvest(@Param("userId") String userId);

    /**
     * Сумма пополнений продуктов с типом SAVING больше 1000 ₽.
     */
    @Query(value = "SELECT SUM(amount) FROM transactions WHERE user_id = :userId AND type = 'SAVING'")
    public abstract long sumOfSavingMoreThanOneThousandRub(@Param("userId") String userId);
}
