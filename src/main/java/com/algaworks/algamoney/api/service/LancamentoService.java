package com.algaworks.algamoney.api.service;

import com.algaworks.algamoney.api.model.Lancamento;
import com.algaworks.algamoney.api.repository.LancamentoRepository;
import com.algaworks.algamoney.api.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

//    public Lancamento atualizar(Long codigo, Lancamento Lancamento){
//        Lancamento LancamentoSalva = buscarLancamentoPeloCodigo(codigo);
//        BeanUtils.copyProperties(Lancamento, LancamentoSalva, "codigo");
//        return lancamentoRepository.save(LancamentoSalva);
//
//    }
//
//    public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
//        Lancamento LancamentoSalva = buscarLancamentoPeloCodigo(codigo);
//        LancamentoSalva.setAtivo(ativo);
//        lancamentoRepository.save(LancamentoSalva);
//    }

    private Lancamento buscarLancamentoPeloCodigo(Long codigo) {
        Lancamento LancamentoSalva = lancamentoRepository.findOne(codigo);
        if(LancamentoSalva == null){
            throw new EmptyResultDataAccessException(1);
        }
        return LancamentoSalva;
    }
}
