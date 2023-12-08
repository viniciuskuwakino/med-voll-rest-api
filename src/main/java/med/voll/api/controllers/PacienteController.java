package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPaciente req) {
        repository.save(new Paciente(req));
    }

    @GetMapping
    public Page<DadosListagemPaciente> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoPaciente req) {
        Paciente pac = repository.getReferenceById(req.id());
        pac.atualizarInfo(req);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        Paciente pac = repository.getReferenceById(id);
        pac.inativar();
    }
}
