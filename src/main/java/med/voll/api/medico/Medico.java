package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Medico(DadosCadastroMedico req) {
        this.nome = req.nome();
        this.email = req.email();
        this.telefone = req.telefone();
        this.crm = req.crm();
        this.especialidade = req.especialidade();
        this.endereco = new Endereco(req.endereco());
        this.ativo = true;
    }

    public void atualizarInfo(DadosAtualizacaoMedico req) {
        if (req.nome() != null) {
            this.nome = req.nome();
        }

        if (req.telefone() != null) {
            this.telefone = req.telefone();
        }

        if (req.endereco() != null) {
            this.endereco.atualizarInfo(req.endereco());
        }
    }

    public void inativar() {
        this.ativo = false;
    }
}
