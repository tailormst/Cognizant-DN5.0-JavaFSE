CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest
AS
    v_interest_rate CONSTANT NUMBER := 0.01;
BEGIN
    FOR acct_rec IN (SELECT account_id, balance FROM savings_accounts) LOOP

        UPDATE savings_accounts
        SET balance = balance + (balance * v_interest_rate)
        WHERE account_id = acct_rec.account_id;

        DBMS_OUTPUT.PUT_LINE(
            'Account ' || acct_rec.account_id ||
            ': interest of ' || ROUND(acct_rec.balance * v_interest_rate, 2) ||
            ' applied (old balance: ' || acct_rec.balance || ').'
        );
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Monthly interest processing complete for all savings accounts.');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error while processing monthly interest: ' || SQLERRM);
        RAISE;
END ProcessMonthlyInterest;
/

EXEC ProcessMonthlyInterest;