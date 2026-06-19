CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_department IN employees.department%TYPE,
    p_bonus_percentage IN NUMBER
)
AS
    v_rows_updated NUMBER;
BEGIN
    IF p_bonus_percentage IS NULL OR p_bonus_percentage < 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Bonus percentage must be a non-negative number.');
    END IF;

    UPDATE employees
    SET salary = salary + (salary * p_bonus_percentage / 100)
    WHERE department = p_department;

    v_rows_updated := SQL%ROWCOUNT;

    IF v_rows_updated = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No employees found in department: ' || p_department);
    ELSE
        COMMIT;
        DBMS_OUTPUT.PUT_LINE(
            v_rows_updated || ' employee(s) in department "' || p_department ||
            '" received a ' || p_bonus_percentage || '% bonus.'
        );
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error while updating employee bonus: ' || SQLERRM);
        RAISE;
END UpdateEmployeeBonus;
/

EXEC UpdateEmployeeBonus('Sales', 5);