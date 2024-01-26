package org.iesvdm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iesvdm.domain.Comercial;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentStatisticsDTO {

    private Comercial comercial;
    private int totalPedidos;
    private int trimestre;
    private int semestre;
    private int anual;
    private int lustro;
}
