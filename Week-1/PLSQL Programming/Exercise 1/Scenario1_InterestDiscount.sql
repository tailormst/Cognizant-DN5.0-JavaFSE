DECLARE
    CURSOR customer_cursor IS
        SELECT customer_id, age
        FROM customers;

    v_customer_id customers.customer_id%TYPE;
    v_age         customers.age%TYPE;

BEGIN
    FOR customer_rec IN customer_cursor LOOP
        v_customer_id := customer_rec.customer_id;
        v_age         := customer_rec.age;

        IF v_age > 60 THEN
            UPDATE loans
            SET interest_rate = interest_rate - 1
            WHERE customer_id = v_customer_id;

            DBMS_OUTPUT.PUT_LINE(
                'Customer ' || v_customer_id ||
                ' (age ' || v_age || ') -> 1% interest discount applied.'
            );
        END IF;
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Scenario 1 complete: interest discounts applied where eligible.');
END;
/