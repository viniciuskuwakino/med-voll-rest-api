package med.voll.api.medico;

public record DadosListagemMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {
    public DadosListagemMedico(Medico med) {
        this(med.getId(), med.getNome(), med.getEmail(), med.getCrm(), med.getEspecialidade());
    }
}
