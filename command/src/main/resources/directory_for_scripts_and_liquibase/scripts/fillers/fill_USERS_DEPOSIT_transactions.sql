INSERT INTO users_deposits(user_id, debit_amount, saving_amount, credit_amount, invest_amount)
SELECT
    t.user_id,
    SUM(CASE WHEN p.TYPE = 'DEBIT' AND t.TYPE = 'DEPOSIT' THEN t.amount ELSE 0 END) AS debit_amount,
    SUM(CASE WHEN p.TYPE = 'SAVING' AND t.TYPE = 'DEPOSIT' THEN t.amount ELSE 0 END) AS saving_amount,
    SUM(CASE WHEN p.TYPE = 'CREDIT' AND t.TYPE = 'DEPOSIT' THEN t.amount ELSE 0 END) AS credit_amount,
    SUM(CASE WHEN p.TYPE = 'INVEST' AND t.TYPE = 'DEPOSIT' THEN t.amount ELSE 0 END) AS invest_amount
FROM
	TRANSACTIONS t
JOIN
	products p ON t.product_id = p.id
GROUP BY
    t.user_id;
