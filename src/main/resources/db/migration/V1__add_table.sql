CREATE TABLE IF NOT EXISTS codeword_data
(
    id serial NOT NULL,
    codeword varchar(255) NOT NULL,
    CONSTRAINT codeword_id_pk PRIMARY KEY(id),
    CONSTRAINT codeword_check CHECK(codeword != '')
);