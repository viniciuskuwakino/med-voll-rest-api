package med.voll.api.paciente;

public record DadosListagemPaciente(String nome, String email, String telefone, String cpf) {
    public DadosListagemPaciente(Paciente pac) {
        this(pac.getNome(), pac.getEmail(), pac.getTelefone(), pac.getCpf());
    }
}
