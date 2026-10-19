import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ProcessoSeletivo {

    private static final double SALARIO_BASE = 2000.0;
    private static final int MAX_SELECIONADOS = 5;
    private static final int MAX_TENTATIVAS = 3;

    public static void main(String[] args) {
        String[] candidatos = {
            "FELIPE", "MARCIA", "JULIA", "PAULO", "AUGUSTO",
            "MONICA", "FABRICIO", "MIRELA", "DANIELA", "JORGE"
        };

        System.out.println("=== 1) Análise de candidato ===");
        analisarCandidato(1900.0);
        analisarCandidato(2000.0);
        analisarCandidato(2200.0);

        System.out.println("\n=== 2) Seleção de candidatos ===");
        List<String> selecionados = selecionarCandidatos(candidatos);

        System.out.println("\n=== 3) Contato com selecionados ===");
        for (String candidato : selecionados) {
            entrarEmContato(candidato);
        }
    }

    // Compara a pretensão salarial com o salário base
    static void analisarCandidato(double salarioPretendido) {
        System.out.printf("Salário pretendido: R$ %.2f -> ", salarioPretendido);
        if (SALARIO_BASE > salarioPretendido) {
            System.out.println("LIGAR PARA O CANDIDATO");
        } else if (SALARIO_BASE == salarioPretendido) {
            System.out.println("LIGAR PARA O CANDIDATO COM CONTRA PROPOSTA");
        } else {
            System.out.println("AGUARDANDO O RESULTADO DOS DEMAIS CANDIDATOS");
        }
    }

    // Seleciona até 5 candidatos cuja pretensão seja menor ou igual ao salário base
    static List<String> selecionarCandidatos(String[] candidatos) {
        List<String> selecionados = new ArrayList<>();
        int i = 0;

        while (selecionados.size() < MAX_SELECIONADOS && i < candidatos.length) {
            String candidato = candidatos[i++];
            double salarioPretendido = valorPretendido();

            System.out.printf("O candidato %s solicitou este valor de salário: R$ %.2f%n",
                    candidato, salarioPretendido);

            if (SALARIO_BASE >= salarioPretendido) {
                System.out.println("  -> Candidato SELECIONADO: " + candidato);
                selecionados.add(candidato);
            }
        }

        System.out.println("\nCandidatos selecionados: " + selecionados);
        return selecionados;
    }

    // Gera um valor aleatório entre 1800 e 2200
    static double valorPretendido() {
        return ThreadLocalRandom.current().nextDouble(1800, 2200);
    }

    // Simula tentativas de contato (máximo de 3)
    static void entrarEmContato(String candidato) {
        int tentativas = 1;
        boolean continuarTentando = true;
        boolean atendeu = false;

        do {
            atendeu = atender();
            continuarTentando = !atendeu;
            if (continuarTentando) {
                tentativas++;
            } else {
                System.out.println("CONTATO REALIZADO COM SUCESSO com " + candidato);
            }
        } while (continuarTentando && tentativas <= MAX_TENTATIVAS);

        if (atendeu) {
            System.out.println("  Conseguimos contato com " + candidato
                    + " na tentativa " + tentativas);
        } else {
            System.out.println("  Não conseguimos contato com " + candidato
                    + ". Número máximo de tentativas (" + MAX_TENTATIVAS + ") realizado.");
        }
    }

    // Simula o candidato atendendo (aprox. 1 chance em 3)
    static boolean atender() {
        return ThreadLocalRandom.current().nextInt(3) == 1;
    }
}
