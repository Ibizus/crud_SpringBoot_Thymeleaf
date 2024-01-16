package org.iesvdm.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Slf4j // Anotación de Lombok para poder usar el objeto LOG y guardar una traza con diferentes niveles (info, error, debug)
@Repository // Componente de Spring de la capa de persistencia
public class ClienteDAOImpl implements ClienteDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /* OTRA FORMA DE INYECTAR LA JDBC EN EL CONSTRUCTOR (sin usar Autowired):
    public ClienteDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }*/

    @Override
    public void create(Cliente cliente) {
        // ASÍ SE HARÍA SI NO NECESITAMOS RECUPERAR EL ID GENERADO AUTOMÁTICAMENTE:
//        jdbcTemplate.update("INSERT INTO cliente (nombre, apellido1, apellido2, ciudad, categoría) VALUES (?, ?, ?, ?, ?)",
//                cliente.getNombre(),
//                cliente.getApellido1(),
//                cliente.getApellido2(),
//                cliente.getCiudad(),
//                cliente.getCategoria());

        // RECUPERANDO ID:
        // La triple comilla permite guardar una consulta SQL en bloque como cadena:
        String sqlInsert = """
							INSERT INTO cliente (nombre, apellido1, apellido2, ciudad, categoría) 
							VALUES  (     ?,         ?,         ?,       ?,         ?)
						   """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rows = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlInsert, new String[] { "id" });
            int idx = 1;
            ps.setString(idx++, cliente.getNombre());
            ps.setString(idx++, cliente.getApellido1());
            ps.setString(idx++, cliente.getApellido2());
            ps.setString(idx++, cliente.getCiudad());
            ps.setInt(idx, cliente.getCategoria());
            return ps;
        },keyHolder);

        cliente.setId(keyHolder.getKey().intValue());
        log.info("Insertados {} registros.", rows);
    }


    @Override
    public List<Cliente> getAll() {

        List<Cliente> clienteList = jdbcTemplate.query(
                "SELECT * FROM cliente",
                (rs, rowNum) -> new Cliente(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getString("ciudad"),
                        rs.getInt("categoría")
                )
        );

        log.info("Devueltos {} registros.", clienteList.size());
        return clienteList;
    }


    @Override
    public Optional<Cliente> find(int id) {

        Cliente encontrado =  jdbcTemplate
                .queryForObject("SELECT * FROM cliente WHERE id = ?"
                        , (rs, rowNum) -> new Cliente(rs.getInt("id"),
                                rs.getString("nombre"),
                                rs.getString("apellido1"),
                                rs.getString("apellido2"),
                                rs.getString("ciudad"),
                                rs.getInt("categoría"))
                        , id
                );

        if (encontrado != null) {
            return Optional.of(encontrado);
        }
        else {
            log.info("Cliente no encontrado.");
            return Optional.empty();
        }
    }



    @Override
    public void update(Cliente cliente) {

        int rows = jdbcTemplate.update(""" 
                                              UPDATE cliente SET 
														nombre = ?, 
														apellido1 = ?, 
														apellido2 = ?,
														ciudad = ?,
														categoría = ?  
												        WHERE id = ? """,
                                    cliente.getNombre()
                                    , cliente.getApellido1()
                                    , cliente.getApellido2()
                                    , cliente.getCiudad()
                                    , cliente.getCategoria()
                                    , cliente.getId());

        log.info("Update de Cliente con {} registros actualizados.", rows);
    }



    @Override
    public void delete(int id) {

        int rows = jdbcTemplate.update("DELETE FROM cliente WHERE id = ?", id);

        log.info("Delete de Cliente con {} registros eliminados.", rows);
    }
}
