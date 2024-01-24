package org.iesvdm.mapper;

import org.iesvdm.domain.Pedido;
import org.iesvdm.dto.PedidosDetailsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    @Mapping(source = "pedido.id_cliente", target = "id_cliente")
    @Mapping(source = "pedido.id_comercial", target = "id_comercial")
    public PedidosDetailsDTO pedidoToPedidoDTO(Pedido pedido);

    @Mapping(source = "id_cliente", target = "id_cliente")
    @Mapping(source = "id_comercial", target = "id_comercial")
    public Pedido pedidoDTOToPedido(PedidosDetailsDTO pedidosDetailsDto);
}
