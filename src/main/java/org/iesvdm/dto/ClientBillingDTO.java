package org.iesvdm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientBillingDTO {

    private int idCliente;
    private String nombreCliente;
    private double totalCliente;
}
