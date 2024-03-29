package org.iesvdm.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    private int id;

    @NotBlank(message="{msg.valid.blank}")
    @NotNull(message="{msg.valid.null}")
    @Length(max=30, message="{msg.valid.maxLenght}")
    private String nombre;

    @NotBlank(message="{msg.valid.blank}")
    @NotNull(message="{msg.valid.null}")
    @Length(max=30, message="{msg.valid.maxLenght}")
    private String apellido1;

    @Length(max=30, message="{msg.valid.maxLenght}")
    private String apellido2;

    @NotBlank(message="{msg.valid.blank}")
    @Email(message="{msg.valid.email}")
    @Length(max=100, message="{msg.valid.maxLenght}")
    private String email;

    @NotBlank(message="{msg.valid.blank}")
    @NotNull(message="{msg.valid.null}")
    @Length(max=30, message="{msg.valid.maxLenght}")
    private String ciudad;

    @Min(value=100, message="{msg.valid.min}")
    @Max(value=1000, message="{msg.valid.max}")
    private int categoria;
}
