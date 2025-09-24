/**
 * 
 */
package co.gov.sdp.spdd.data.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Sumset
 *
 */
@Component
public class EntidadBdMapper <T> {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<T> buscarSQL(String strQuery, Class<T> entidad ) {
		return jdbcTemplate.query(strQuery, new BeanPropertyRowMapper<>(entidad));
	}
	
}
