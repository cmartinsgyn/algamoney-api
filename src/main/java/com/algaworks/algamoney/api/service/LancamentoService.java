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


    public Lancamento atualizar(Long codigo, Lancamento lancamento) {
        Lancamento lancamentoSalvo = buscarLancamentoExistente(codigo);
        /*apos buscar o lancamento existente, verifica se o que veio da pagina e
        o mesmo do buscado, se nao valida a pessoa */
        if (!lancamento.getPessoa().equals(lancamentoSalvo.getPessoa())){
            validarPessoa(lancamento);
        }

        BeanUtils.copyProperties(lancamento, lancamentoSalvo, "codigo");

        return lancamentoRepository.save(lancamentoSalvo);
    }

    private void validarPessoa(Lancamento lancamento) {
    /*verifica se a pessoa existe no banco de dados e se nao existir ou for inativa gera excecao*/
        Pessoa pessoa = null;
        if (lancamento.getPessoa().getCodigo() != null){
            pessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
        }

        if (pessoa == null || pessoa.isInativo()){
            throw new PessoaInexistenteOuInativaException();
        }
    }

    private Lancamento buscarLancamentoExistente(Long codigo) {
        Lancamento lancamentoSalvo = lancamentoRepository.findOne(codigo);

        if (lancamentoSalvo == null){
            throw new IllegalArgumentException();
        }
        return lancamentoSalvo;
    }
}
