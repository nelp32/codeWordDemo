package kz.nelp.codeWordDemo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "codeword_data")
public class Codeword {
    @Id
    private Integer id;

    @Column(name = "codeword", nullable = false)
    private String codeword;
}
