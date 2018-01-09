package com.algaworks.algamoney.api.service;

import com.algaworks.algamoney.api.model.Lancamento;
import com.algaworks.algamoney.api.model.Pessoa;
import com.algaworks.algamoney.api.repository.LancamentoRepository;
import com.algaworks.algamoney.api.repository.PessoaRepository;
import com.algaworks.algamoney.api.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Lancamento salvar(Lancamento lancamento) {
        Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
        if(pessoa == null || pessoa.isInativo()){
          throw new PessoaInexistenteOuInativaException();
        }
        return lancamentoRepository.save(lancamento);
    }

    private Lancamento buscarLancamentoPeloCodigo(Long codigo) {
        Lancamento LancamentoSalva = lancamentoRepository.findOne(codigo);
        if(LancamentoSalva == null){
            throw new EmptyResultDataAccessException(1);
        }
        return LancamentoSalva;
    }


    //        lancamentoRepository.save(LancamentoSalva);
    //        LancamentoSalva.setAtivo(ativo);
    //        Lancamento LancamentoSalva = buscarLancamentoPeloCodigo(codigo);
    //    public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
    //
    //    }
    //
    //        return lancamentoRepository.save(LancamentoSalva);
    //        BeanUtils.copyProperties(Lancamento, LancamentoSalva, "codigo");
    //        Lancamento LancamentoSalva = buscarLancamentoPeloCodigo(codigo);
//    public Lancamento atualizar(Long codigo, Lancamento Lancamento){

//    }
}
