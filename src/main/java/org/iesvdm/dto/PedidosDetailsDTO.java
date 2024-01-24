package org.iesvdm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidosDetailsDTO {

    private int numeroPedidos;
    private Double sumaTotal;
    private Double media;
    private int id_Pedido_Max;
    private int id_Pedido_Min;
}
