import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClinicaConsultaAgil {
    private static List<Paciente> pacientes = new ArrayList<>();
    private static List<Consulta> consultas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void mostrarMenu() {
        System.out.println("Olá, bem vindo(a) à clínica de consultas Ágil");
        System.out.println("Digite 1 para cadastrar um paciente");
        System.out.println("Digite 2 para marcar uma consulta");
        System.out.println("Digite 3 para cancelar uma consulta");
        System.out.println("Digite 4 para encerrar o programa");
    }

    public static void cadastrarPacientes() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        for (Paciente p : pacientes) {
            if (p.getTelefone().equals(telefone)) {
                System.out.println("Paciente já cadastrado!");
                return;
            }
        }

        Paciente paciente = new Paciente(nome, telefone);
        pacientes.add(paciente);
        System.out.println("Paciente cadastrado com sucesso!");
    }

    public static void marcarConsulta() {
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }

        System.out.println("Pacientes cadastrados:");
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println((i + 1) + ". " + pacientes.get(i).getNome());
        }

        System.out.print("Escolha o número do paciente: ");
        int numPaciente = scanner.nextInt();
        scanner.nextLine();

        if (numPaciente < 1 || numPaciente > pacientes.size()) {
            System.out.println("Número inválido.");
            return;
        }

        Paciente paciente = pacientes.get(numPaciente - 1);
        System.out.print("Data (dd/mm/aaaa): ");
        String data = scanner.nextLine();
        System.out.print("Hora (hh:mm): ");
        String hora = scanner.nextLine();
        System.out.print("Especialidade: ");
        String especialidade = scanner.nextLine();

        for (Consulta c : consultas) {
            if (c.getData().equals(data) && c.getHora().equals(hora)) {
                System.out.println("Horário já agendado.");
                return;
            }
        }

        Consulta consulta = new Consulta(paciente, data, hora, especialidade);
        consultas.add(consulta);
        System.out.println("Consulta agendada com sucesso!");
    }

    public static void cancelarConsulta() {
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta agendada.");
            return;
        }

        System.out.println("Consultas agendadas:");
        for (int i = 0; i < consultas.size(); i++) {
            Consulta c = consultas.get(i);
            System.out.println((i + 1) + ". " + c.getPaciente().getNome() + " - " + c.getData() + " " + c.getHora() + " - " + c.getEspecialidade());
        }

        System.out.print("Escolha o número da consulta para cancelar: ");
        int numConsulta = scanner.nextInt();
        scanner.nextLine();

        if (numConsulta < 1 || numConsulta > consultas.size()) {
            System.out.println("Número inválido.");
            return;
        }

        consultas.remove(numConsulta - 1);
        System.out.println("Consulta cancelada com sucesso!");
    }
}
