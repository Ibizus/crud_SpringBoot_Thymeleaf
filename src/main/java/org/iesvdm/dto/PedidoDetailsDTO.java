package org.iesvdm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDetailsDTO {

    private long numeroPedidos;
    private Double sumaTotal;
    private Double media;
    private int id_Pedido_Min;
    private int id_Pedido_Max;
}
