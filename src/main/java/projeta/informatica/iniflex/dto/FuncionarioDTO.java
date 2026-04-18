package projeta.informatica.iniflex.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;


public class FuncionarioDTO {

    private String nome;
    private String funcao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private String dataNascimento;
    private String salario;


    public FuncionarioDTO(String maria, LocalDate localDate, String dataNascimento, String operador) {

    }

    public FuncionarioDTO(String nome, String funcao, String dataNascimento, String salario) {
        this.nome = nome;
        this.funcao = funcao;
        this.dataNascimento = dataNascimento;
        this.salario = salario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getSalario() {
        return salario;
    }
}
