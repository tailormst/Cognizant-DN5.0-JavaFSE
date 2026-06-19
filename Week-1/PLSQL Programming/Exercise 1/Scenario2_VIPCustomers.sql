DECLARE
    CURSOR customer_cursor IS
        SELECT customer_id, balance
        FROM customers;

BEGIN
    FOR customer_rec IN customer_cursor LOOP
        IF customer_rec.balance > 10000 THEN
            UPDATE customers
            SET is_vip = 'Y'
            WHERE customer_id = customer_rec.customer_id;

            DBMS_OUTPUT.PUT_LINE(
                'Customer ' || customer_rec.customer_id ||
                ' promoted to VIP (balance: ' || customer_rec.balance || ').'
            );
        ELSE
            UPDATE customers
            SET is_vip = 'N'
            WHERE customer_id = customer_rec.customer_id;
        END IF;
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Scenario 2 complete: VIP flags updated.');
END;
/