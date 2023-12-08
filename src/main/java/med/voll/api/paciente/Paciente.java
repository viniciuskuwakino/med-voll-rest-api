package med.voll.api.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

@Table(name="pacientes")
@Entity(name="Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;


    public Paciente(DadosCadastroPaciente req) {
        this.nome = req.nome();
        this.email = req.email();
        this.cpf = req.cpf();
        this.telefone = req.telefone();
        this.endereco = new Endereco(req.endereco());
        this.ativo = true;
    }

    public void atualizarInfo(DadosAtualizacaoPaciente req) {
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
