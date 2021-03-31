package br.com.api.start.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Empresa implements Serializable {

    @Id
    private Integer id;
    private String nome;
    private String sigla;


}
