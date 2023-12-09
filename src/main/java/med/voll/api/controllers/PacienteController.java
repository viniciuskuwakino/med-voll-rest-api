package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPaciente> cadastrar(@RequestBody @Valid DadosCadastroPaciente req, UriComponentsBuilder uriBuilder) {
        var pac = new Paciente(req);
        repository.save(pac);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(pac.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(pac));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listar(Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPaciente> listar(@PathVariable Long id) {
        var pac = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(pac));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPaciente> atualizar(@RequestBody @Valid DadosAtualizacaoPaciente req) {
        Paciente pac = repository.getReferenceById(req.id());
        pac.atualizarInfo(req);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(pac));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> excluir(@PathVariable Long id) {
        Paciente pac = repository.getReferenceById(id);
        pac.inativar();

        return ResponseEntity.noContent().build();
    }
}
