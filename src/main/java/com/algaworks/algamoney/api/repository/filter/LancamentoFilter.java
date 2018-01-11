package com.algaworks.algamoney.api.repository.filter;

import com.algaworks.algamoney.api.model.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LancamentoFilter {

    private String descricao;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataVencimentoDe;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataVencimentoAte;

    private TipoLancamento tipoLancamento;


}
