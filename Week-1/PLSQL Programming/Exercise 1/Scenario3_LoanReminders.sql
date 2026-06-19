DECLARE
    CURSOR due_loans_cursor IS
        SELECT l.loan_id, l.customer_id, l.due_date, c.customer_name
        FROM loans l
        JOIN customers c ON c.customer_id = l.customer_id
        WHERE l.due_date BETWEEN SYSDATE AND SYSDATE + 30;

BEGIN
    FOR loan_rec IN due_loans_cursor LOOP
        DBMS_OUTPUT.PUT_LINE(
            'Reminder: Loan #' || loan_rec.loan_id ||
            ' for customer ' || loan_rec.customer_name ||
            ' (ID ' || loan_rec.customer_id || ')' ||
            ' is due on ' || TO_CHAR(loan_rec.due_date, 'DD-MON-YYYY') || '.'
        );
    END LOOP;

    DBMS_OUTPUT.PUT_LINE('Scenario 3 complete: reminders printed for loans due in next 30 days.');
END;
/