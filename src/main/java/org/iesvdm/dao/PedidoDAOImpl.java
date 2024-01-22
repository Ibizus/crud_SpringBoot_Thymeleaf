package org.iesvdm.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.domain.Pedido;
import org.iesvdm.service.ClienteService;
import org.iesvdm.service.ComercialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
// Anotación de Lombok para poder usar el objeto LOG y guardar una traza con diferentes niveles (info, error, debug)
@Repository // Componente de Spring de la capa de persistencia
public class PedidoDAOImpl implements PedidoDAO{

        private JdbcTemplate jdbcTemplate;
        @Autowired
        private ComercialDAO comercialDAO;
        @Autowired
        private ClienteDAO clienteDAO;

    /* OTRA FORMA DE INYECTAR LA JDBC EN EL CONSTRUCTOR (sin usar Autowired):*/
    public PedidoDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Optional<List<Pedido>> findPedidoByComercialId(int idComercial) {

        List<Pedido> pedidosComercial =  jdbcTemplate
                .query("SELECT * FROM pedido WHERE id_comercial = ?"
                        , (rs, rowNum) -> new Pedido(rs.getInt("id"),
                                rs.getDouble("total"),
                                rs.getDate("fecha"),
                                rs.getInt("id_cliente"),
                                rs.getInt("id_comercial"),
                                clienteDAO.find(rs.getInt("id_cliente")).get(),
                                comercialDAO.find(rs.getInt("id_comercial")).get()
                                )
                        , idComercial
                );

        if (pedidosComercial != null) {
            return Optional.of(pedidosComercial);
        }
        else {
            log.info("No hay pedidos del comercial indicado.");
            return Optional.empty();
        }
    }

}
