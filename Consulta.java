import java.io.Serializable;

public class Consulta implements Serializable {
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
