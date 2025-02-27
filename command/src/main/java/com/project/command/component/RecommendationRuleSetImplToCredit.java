package com.project.command.component;

import com.project.command.model.RecommendationDTO;
import com.project.command.repository.UserRepository;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.project.command.repository.RecommendationsConstants.*;

@Component
public abstract class RecommendationRuleSetImplToCredit implements RecommendationRuleSet{
   private final JdbcTemplate jdbcTemplate;

    public RecommendationRuleSetImplToCredit(UserRepository userRepository, JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<RecommendationDTO> matchesRules(String user_id) {
        if (addDebitMoreThanSpendDebit(user_id) && noOneProductCredit(user_id) &&  sumSpendDebitMoreOneHundredThousandsRub(user_id) > 100_000) {
            return Optional.of(new RecommendationDTO(PRODUCT_NAME_CREDIT, PRODUCT_ID_CREDIT, PRODUCT_TEXT_CREDIT));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Сумма пополнений по всем продуктам типа DEBIT больше, чем сумма трат по всем продуктам типа DEBIT.
     */
    public abstract boolean addDebitMoreThanSpendDebit(@Param("userId") String userId);


    /**
     * Пользователь не использует продукты с типом CREDIT.
     */
    @Query(value = "SELECT NOT EXISTS (SELECT 1 FROM transactions WHERE user_id = :userId AND type = 'CREDIT')")
    public abstract boolean noOneProductCredit(@Param("userId") String userId);

    /**
     * Сумма трат по всем продуктам типа DEBIT больше, чем 100 000 ₽.
     */
    public abstract long sumSpendDebitMoreOneHundredThousandsRub(@Param("userId") String userId);

}

