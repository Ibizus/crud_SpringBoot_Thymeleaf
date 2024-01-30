package org.iesvdm.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    private int id;

    @NotNull(message="{msg.valid.null}")
    @Positive(message="msg.valid.positive")
    private double total;

    @NotNull(message="{msg.valid.null}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    @Positive(message="msg.valid.positive")
    @Min(value=1, message = "{msg.valid.min}")
    private int id_cliente;

    @Positive(message="msg.valid.positive")
    @Min(value=1, message = "{msg.valid.min}")
    private int id_comercial;

    @Valid
    private Cliente cliente;

    @Valid
    private Comercial comercial;
}