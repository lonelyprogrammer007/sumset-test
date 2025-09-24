package co.gov.sdp.spdd.core.seguridad;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;

/**
 * 
 * @author SumSet 2019
 *
 */
@Component
@Order(1)
public class JWTAutenticacionFiltro extends OncePerRequestFilter {

	@Autowired
	private JWTUtil jwtUtil;

	/*
	 * Este filtro si el
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		boolean autorizar = false;
		String jwt = getJwtFromRequest(request);
		if (request.toString().indexOf(NHSPDDConstantes.CORE_CONTROLLER_BUZON_NOTIFICACION_OBTENER_NOTIFICACIONES) >= 0
				|| StringUtils.hasText(jwt) && jwtUtil.validarToken(jwt)) {

			autorizar = true;
		}
		if (autorizar) {
			filterChain.doFilter(request, response);
		} else {
			SecurityContextHolder.clearContext();
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);

		}

	}

	/*
	 * Obtener el token desde
	 * 
	 * @param request
	 * 
	 * @return
	 */
	private String getJwtFromRequest(HttpServletRequest request) {
		String token = request.getHeader(NHSPDDConstantes.HEADER_STRING);
		if (token != null && StringUtils.hasText(token) && token.startsWith(NHSPDDConstantes.TOKEN_PREFIX)) {
			return token.substring(6, token.length());
		}
		return "";
	}

}
