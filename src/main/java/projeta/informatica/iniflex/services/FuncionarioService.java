package projeta.informatica.iniflex.services;

import org.springframework.stereotype.Service;
import projeta.informatica.iniflex.dto.FuncionarioDTO;
import projeta.informatica.iniflex.entites.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    public List<FuncionarioDTO> executar() {

        List<Funcionario> funcionarios = new ArrayList<>();


        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 1, 3), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 3, 7), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloisa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));


        funcionarios.removeIf(f -> "João".equals(f.getNome()));


        funcionarios.forEach(f ->
                f.setSalario(f.getSalario().multiply(new BigDecimal("1.10")))
        );


        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);


        Map<String, List<Funcionario>> agrupados =
                funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));


        System.out.println("\n--- Agrupados por função ---");
        agrupados.forEach((funcao, lista) -> {
            System.out.println(funcao + ":");
            lista.forEach(f -> System.out.println(" - " + f.getNome()));
        });


        System.out.println("\n--- Aniversariantes ---");
        funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10 ||
                        f.getDataNascimento().getMonthValue() == 12)
                .forEach(f -> System.out.println(f.getNome()));

        Funcionario maisVelho = Collections.min(funcionarios,
                Comparator.comparing(Funcionario::getDataNascimento));

        int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();

        System.out.println("\nMais velho: " + maisVelho.getNome() + " - " + idade + " anos");


        System.out.println("\n--- Ordem alfabética ---");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(f -> System.out.println(f.getNome()));

        BigDecimal total = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("\nTotal salários: " + nf.format(total));

        BigDecimal salarioMinimo = new BigDecimal("1212");

        System.out.println("\n--- Salários mínimos ---");
        funcionarios.forEach(f -> {
            BigDecimal qtd = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println(f.getNome() + ": " + qtd);
        });


        return funcionarios.stream()
                .map(f -> new FuncionarioDTO(
                        f.getNome(),
                        f.getFuncao(),
                        df.format(f.getDataNascimento()),
                        nf.format(f.getSalario())
                ))
                .collect(Collectors.toList());
    }
    public Map<String, List<FuncionarioDTO>> agruparPorFuncao(List<FuncionarioDTO> lista) {
        return lista.stream()
                .collect(Collectors.groupingBy(FuncionarioDTO::getFuncao));

}

    public List<FuncionarioDTO> aniversariantes(List<FuncionarioDTO> lista) {
        return lista.stream()
                .filter(f -> f.getDataNascimento().contains("/10/")
                        || f.getDataNascimento().contains("/12/"))
                .toList();
    }

    public List<FuncionarioDTO> ordenarPorNome(List<FuncionarioDTO> lista) {
        return lista.stream()
                .sorted(Comparator.comparing(FuncionarioDTO::getNome))
                .toList();
    }

    public double totalSalarios(List<FuncionarioDTO> lista) {
        return lista.stream()
                .map(f -> f.getSalario().replace(".", "").replace(",", "."))
                .mapToDouble(Double::parseDouble)
                .sum();
    }

}