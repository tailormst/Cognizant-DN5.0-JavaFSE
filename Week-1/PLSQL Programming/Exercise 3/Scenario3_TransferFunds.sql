CREATE OR REPLACE PROCEDURE TransferFunds (
    p_from_account_id IN accounts.account_id%TYPE,
    p_to_account_id IN accounts.account_id%TYPE,
    p_amount IN NUMBER
)
AS
    v_source_balance accounts.balance%TYPE;
    insufficient_funds EXCEPTION;

BEGIN
    IF p_amount IS NULL OR p_amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Transfer amount must be greater than zero.');
    END IF;

    SELECT balance
    INTO v_source_balance
    FROM accounts
    WHERE account_id = p_from_account_id
    FOR UPDATE;

    IF v_source_balance < p_amount THEN
        RAISE insufficient_funds;
    END IF;

    UPDATE accounts
    SET balance = balance - p_amount
    WHERE account_id = p_from_account_id;

    UPDATE accounts
    SET balance = balance + p_amount
    WHERE account_id = p_to_account_id;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE(
        'Transferred ' || p_amount || ' from account ' ||
        p_from_account_id || ' to account ' || p_to_account_id || '.'
    );

EXCEPTION
    WHEN insufficient_funds THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE(
            'Transfer failed: account ' || p_from_account_id ||
            ' has insufficient balance (' || v_source_balance ||
            ') for a transfer of ' || p_amount || '.'
        );

    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE(
            'Transfer failed: source account ' || p_from_account_id ||
            ' was not found.'
        );

    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error while transferring funds: ' || SQLERRM);
        RAISE;
END TransferFunds;
/

EXEC TransferFunds(101, 102, 500);