package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico req) {
        repository.save(new Medico(req));
    }


    @GetMapping
    public Page<DadosListagemMedico> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico req) {
        Medico med = repository.getReferenceById(req.id());
        med.atualizarInfo(req);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        Medico med = repository.getReferenceById(id);
        med.inativar();
    }

}
