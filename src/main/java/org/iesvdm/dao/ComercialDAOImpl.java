package org.iesvdm.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.domain.Comercial;
import org.iesvdm.dto.AgentStatisticsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class ComercialDAOImpl implements ComercialDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Comercial comercial) {

        String sqlInsert = """
							INSERT INTO comercial (nombre, apellido1, apellido2, comisión) 
							VALUES  (     ?,         ?,         ?,       ?)
						   """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rows = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlInsert, new String[] { "id" });
            int idx = 1;
            ps.setString(idx++, comercial.getNombre());
            ps.setString(idx++, comercial.getApellido1());
            ps.setString(idx++, comercial.getApellido2());
            ps.setFloat(idx, comercial.getComision());
            return ps;
        },keyHolder);

        comercial.setId(keyHolder.getKey().intValue());
        log.info("Insertados {} registros.", rows);
    }


    @Override
    public List<Comercial> getAll() {

        List<Comercial> listComercial = jdbcTemplate.query(
                "SELECT * FROM comercial",
                (rs, rowNum) -> new Comercial(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getFloat("comisión"))
        );

        log.info("Devueltos {} registros.", listComercial.size());
        return listComercial;
    }


    @Override
    public Optional<Comercial> find(int id) {

        Comercial encontrado =  jdbcTemplate
                .queryForObject("SELECT * FROM comercial WHERE id = ?"
                        , (rs, rowNum) -> new Comercial(rs.getInt("id"),
                                rs.getString("nombre"),
                                rs.getString("apellido1"),
                                rs.getString("apellido2"),
                                rs.getFloat("comisión"))
                        , id
                );

        if (encontrado != null) {
            return Optional.of(encontrado);}
        else {
            log.info("Comercial no encontrado.");
            return Optional.empty(); }
    }


    @Override
    public void update(Comercial comercial) {

        int rows = jdbcTemplate.update("""
										UPDATE comercial SET 
														nombre = ?, 
														apellido1 = ?, 
														apellido2 = ?,
														comisión = ?  
												WHERE id = ?
										""", comercial.getNombre()
                , comercial.getApellido1()
                , comercial.getApellido2()
                , comercial.getComision()
                , comercial.getId());

        log.info("Update de Comercial con {} registros actualizados.", rows);
    }

    @Override
    public void delete(int id) {

        int rows = jdbcTemplate.update("DELETE FROM comercial WHERE id = ?", id);

        log.info("Delete de Comercial con {} registros eliminados.", rows);
    }


    public int numPedidosTotales(int idComercial){

        return jdbcTemplate.queryForObject(
                "SELECT COUNT(p.id) as numPedidos FROM comercial c JOIN pedido p ON c.id = p.id_comercial WHERE c.id = ?",
                (rs, numRow) -> rs.getInt("numPedidos"),
                idComercial);
    }

    public int numPedidosTrimestre(int idComercial){

        return jdbcTemplate.queryForObject(
                "SELECT COUNT(p.id) as numPedidos FROM comercial c JOIN pedido p ON c.id = p.id_comercial WHERE c.id = ? AND timestampdiff(MONTH , p.fecha, current_date) <= 3",
                (rs, numRow) -> rs.getInt("numPedidos"),
                idComercial);
    }

    public int numPedidosSemestre(int idComercial){

        return jdbcTemplate.queryForObject(
                "SELECT COUNT(p.id) as numPedidos FROM comercial c JOIN pedido p ON c.id = p.id_comercial WHERE c.id = ? AND timestampdiff(MONTH , p.fecha, current_date) <= 6",
                (rs, numRow) -> rs.getInt("numPedidos"),
                idComercial);
    }

    public int numPedidosAnual(int idComercial){

        int pedidosAnual = jdbcTemplate.queryForObject(
                "SELECT COUNT(p.id) as numPedidos FROM comercial c JOIN pedido p ON c.id = p.id_comercial WHERE c.id = ? AND timestampdiff(YEAR , p.fecha, current_date) <= 1",
                (rs, numRow) -> rs.getInt("numPedidos"),
                idComercial);

        return pedidosAnual;
    }

    public int numPedidosLustro(int idComercial){

        return jdbcTemplate.queryForObject(
                "SELECT COUNT(p.id) as numPedidos FROM comercial c JOIN pedido p ON c.id = p.id_comercial WHERE c.id = ? AND timestampdiff(YEAR , p.fecha, current_date) <= 5",
                (rs, numRow) -> rs.getInt("numPedidos"),
                idComercial);
    }

}
