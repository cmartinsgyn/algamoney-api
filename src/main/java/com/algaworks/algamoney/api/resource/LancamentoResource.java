package com.algaworks.algamoney.api.resource;

import com.algaworks.algamoney.api.event.RecursoCriadoEvent;
import com.algaworks.algamoney.api.exceptionhandler.AlgamoneyExceptionHandler;
import com.algaworks.algamoney.api.model.Lancamento;
import com.algaworks.algamoney.api.repository.LancamentoRepository;
import com.algaworks.algamoney.api.repository.filter.LancamentoFilter;
import com.algaworks.algamoney.api.service.LancamentoService;
import com.algaworks.algamoney.api.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private LancamentoService LancamentoService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public List<Lancamento> pesquisar(LancamentoFilter lancamentoFilter){
        return lancamentoRepository.filtrar(lancamentoFilter);
    }


    @GetMapping("/{codigo}")
    public ResponseEntity<Lancamento> buscarPeloCodigo(@PathVariable Long codigo){
        Lancamento lancamento = lancamentoRepository.findOne(codigo);
        return lancamento != null ? ResponseEntity.ok(lancamento) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response){
        Lancamento LancamentoSalva = LancamentoService.salvar(lancamento);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, LancamentoSalva.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(LancamentoSalva);

    }

    @ExceptionHandler({PessoaInexistenteOuInativaException.class})
    public ResponseEntity<Object> handlerPessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex){
        String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();//ExceptionUtils.getRootCauseMessage(ex);

        List<AlgamoneyExceptionHandler.Erro> erros = Arrays.asList(new AlgamoneyExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }


//    @DeleteMapping("/{codigo}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void remover (@PathVariable Long codigo){
//        lancamentoRepository.delete(codigo);
//    }

//    @PutMapping("/{codigo}")
//    public ResponseEntity<Lancamento>atualizar(@PathVariable Long codigo, @Valid @RequestBody Lancamento Lancamento){
//        Lancamento LancamentoSalva = LancamentoService.atualizar(codigo, Lancamento);
//        return ResponseEntity.ok(LancamentoSalva);
//    }
//
//    @PutMapping("/{codigo}/ativo")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo){
//        LancamentoService.atualizarPropriedadeAtivo(codigo, ativo);
//
//    }


}// end class
