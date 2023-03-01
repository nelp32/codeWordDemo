CREATE OR REPLACE FUNCTION codeword_insert_row()
    RETURNS TRIGGER AS $$
BEGIN
    IF(SELECT COUNT(*) FROM codeword_data) >= 1 THEN
        RAISE EXCEPTION 'this table cannot store more than 1 record';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS tr_codeword_insert_row ON codeword_data;
CREATE TRIGGER tr_codeword_insert_row
    BEFORE INSERT ON codeword_data
    FOR EACH ROW EXECUTE FUNCTION codeword_insert_row();