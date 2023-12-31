package med.voll.api.domain.paciente;

import med.voll.api.domain.endereco.Endereco;

public record DadosDetalhamentoPaciente(Long id, String nome, String email, String telefone, String cpf, Endereco endereco) {
    public DadosDetalhamentoPaciente(Paciente pac) {
        this(pac.getId(), pac.getNome(), pac.getEmail(), pac.getTelefone(), pac.getCpf(), pac.getEndereco());
    }
}
