package com.sistema_estoque.estoque_api.DTOs;

import com.sistema_estoque.estoque_api.models.enums.TipoMovimentacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimentacaoDTO {
    private Long id;
    private String codigoP;
    private String codigoF;
    private Integer quantidade;
    private TipoMovimentacaoEnum tipo;
    private LocalDateTime data = LocalDateTime.now();
    private String alerta;
}
