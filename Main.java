import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    private static List<Paciente> pacientes = new ArrayList<>();
    private static List<Consulta> consultas = new ArrayList<>();
    private static final String PACIENTES_FILE = "pacientes.csv";
    private static final String CONSULTAS_FILE = "consultas.csv";

    public static void main(String[] args) {
        carregarDados();

        while (true) {
            ClinicaConsultaAgil.mostrarMenu();
            int opcao = ClinicaConsultaAgil.scanner.nextInt();
            ClinicaConsultaAgil.scanner.nextLine();
            switch (opcao) {
                case 1:
                    ClinicaConsultaAgil.cadastrarPacientes();
                    salvarDados();
                    break;
                case 2:
                    ClinicaConsultaAgil.marcarConsulta();
                    salvarDados();
                    break;
                case 3:
                    ClinicaConsultaAgil.cancelarConsulta();
                    salvarDados();
                    break;
                case 4:
                    System.out.println("Programa Encerrado.");
                    salvarDados();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    static class ClinicaConsultaAgil {
        private static Scanner scanner = new Scanner(System.in);
        private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
            String dataStr = scanner.nextLine();
            LocalDate data;
            try {
                data = LocalDate.parse(dataStr, dateFormatter);
            } catch (Exception e) {
                System.out.println("Data inválida. Formato esperado: dd/mm/aaaa");
                return;
            }

            LocalDate hoje = LocalDate.now();
            if (data.isBefore(hoje)) {
                System.out.println("Não é possível agendar consultas para datas passadas.");
                return;
            }

            System.out.print("Hora (hh:mm): ");
            String hora = scanner.nextLine();
            System.out.print("Especialidade: ");
            String especialidade = scanner.nextLine();

            for (Consulta c : consultas) {
                if (c.getData().equals(data.format(dateFormatter)) && c.getHora().equals(hora)) {
                    System.out.println("Horário já agendado.");
                    return;
                }
            }

            Consulta consulta = new Consulta(paciente, data.format(dateFormatter), hora, especialidade);
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

    static class Paciente {
        private String nome;
        private String telefone;

        public Paciente(String nome, String telefone) {
            this.nome = nome;
            this.telefone = telefone;
        }

        public String getNome() {
            return nome;
        }

        public String getTelefone() {
            return telefone;
        }
    }

    static class Consulta {
        private Paciente paciente;
        private String data;
        private String hora;
        private String especialidade;

        public Consulta(Paciente paciente, String data, String hora, String especialidade) {
            this.paciente = paciente;
            this.data = data;
            this.hora = hora;
            this.especialidade = especialidade;
        }

        public Paciente getPaciente() {
            return paciente;
        }

        public String getData() {
            return data;
        }

        public String getHora() {
            return hora;
        }

        public String getEspecialidade() {
            return especialidade;
        }
    }

    public static void salvarDados() {
        salvarPacientes();
        salvarConsultas();
    }

    private static void salvarPacientes() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PACIENTES_FILE))) {
            for (Paciente paciente : pacientes) {
                writer.println(paciente.getNome() + "," + paciente.getTelefone());
            }
            System.out.println("Pacientes salvos com sucesso em " + PACIENTES_FILE);
        } catch (IOException e) {
            System.out.println("Erro ao salvar pacientes: " + e.getMessage());
        }
    }

    private static void salvarConsultas() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CONSULTAS_FILE))) {
            for (Consulta consulta : consultas) {
                Paciente paciente = consulta.getPaciente();
                writer.println(paciente.getNome() + "," + consulta.getData() + "," +
                        consulta.getHora() + "," + consulta.getEspecialidade());
            }
            System.out.println("Consultas salvos com sucesso em " + CONSULTAS_FILE);
        } catch (IOException e) {
            System.out.println("Erro ao salvar consultas: " + e.getMessage());
        }
    }

    public static void carregarDados() {
        carregarPacientes();
        carregarConsultas();
    }

    private static void carregarPacientes() {
        pacientes.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(PACIENTES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    pacientes.add(new Paciente(parts[0], parts[1]));
                }
            }
            System.out.println("Pacientes carregados com sucesso de " + PACIENTES_FILE);
        } catch (IOException e) {
            System.out.println("Nenhum paciente salvo encontrado ou erro ao carregar pacientes.");
        }
    }

    private static void carregarConsultas() {
        consultas.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(CONSULTAS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    Paciente paciente = new Paciente(parts[0], "");
                    consultas.add(new Consulta(paciente, parts[1], parts[2], parts[3]));
                }
            }
            System.out.println("Consultas carregadas com sucesso de " + CONSULTAS_FILE);
        } catch (IOException e) {
            System.out.println("Nenhuma consulta salva encontrada ou erro ao carregar consultas.");
        }
    }
}
