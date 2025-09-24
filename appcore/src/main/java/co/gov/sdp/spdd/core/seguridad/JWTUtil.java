package co.gov.sdp.spdd.core.seguridad;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * 
 * @author SumSet 2019
 *
 */
@Component
public class JWTUtil {

    private static final Logger logger = LoggerFactory.getLogger(JWTUtil.class);

    public String generarToken(Authentication authentication, List<String> permisos) {
        Date fechaExpiracion = new Date(System.currentTimeMillis() + NHSPDDConstantes.JWT_EXPIRACION_MS_SESSION);
        Claims claims = Jwts.claims().setSubject(authentication.getName());
        claims.put("usuario", authentication.getName());
        claims.put("rol", authentication.getAuthorities());
        claims.put("permisos", permisos);
        return Jwts.builder().setClaims(claims).setExpiration(fechaExpiracion)
                .signWith(SignatureAlgorithm.HS256, NHSPDDConstantes.JWT_SECRETO).compact();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(NHSPDDConstantes.JWT_SECRETO).parseClaimsJws(token).getBody();
    }

    /**
     * Obtener cualquier Claim desde el token
     *
     * @param token
     * @param claimsResolver
     * @return
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Obtener la fecha de expiracion desde el token
     *
     * @param token
     * @return
     */
    public Date getFechaExpiracionJWT(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * Validar si el token ha expirado
     *
     * @param token
     * @return
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getFechaExpiracionJWT(token);
        return expiration.before(new Date());
    }

    /**
     * Validar que el token corresponde a un usuario determinado
     *
     * @param token
     * @param nombreUsuario
     * @return
     */
    public Boolean validarToken(String token, String nombreUsuario) {
        final String result = getNombreUsuarioJWT(token);
        if (result.equals(nombreUsuario) && !isTokenExpired(token)) {
            Date fechaExpiracion = new Date(System.currentTimeMillis() + NHSPDDConstantes.JWT_EXPIRACION_MS_SESSION);
            Jwts.parser().setSigningKey(NHSPDDConstantes.JWT_SECRETO).parseClaimsJws(token);
            Jwts.builder().setExpiration(fechaExpiracion);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Validar todas las caracterisiticas de un token
     *
     * @param token
     * @return
     */
    public boolean validarToken(String token) {
        try {
            if (!isTokenExpired(token)) {
                Date fechaExpiracion = new Date(System.currentTimeMillis() + NHSPDDConstantes.JWT_EXPIRACION_MS_SESSION);
                Jwts.parser().setSigningKey(NHSPDDConstantes.JWT_SECRETO).parseClaimsJws(token);
                Jwts.builder().setExpiration(fechaExpiracion);
                return true;
            } else {
                return false;
            }
        } catch (SignatureException ex) {
            logger.error("Firma JWT inválida");
        } catch (MalformedJwtException ex) {
            logger.error("Token JWT no válido");
        } catch (ExpiredJwtException ex) {
            logger.error("Token JWT caducado");
        } catch (UnsupportedJwtException ex) {
            logger.error("Token JWT no soportado");
        } catch (IllegalArgumentException ex) {
            logger.error("Los claims están vacíos");
        }
        return false;
    }

    /**
     * Obtener nombre de usuario desde el claim
     *
     * @param token
     * @return
     */
    public String getNombreUsuarioJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(NHSPDDConstantes.JWT_SECRETO).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    /**
     * Validar el token
     *
     * @param token
     * @return
     */
    public String getUsuarioTokenValidado(String token) {
        final String result = getNombreUsuarioJWT(token);
        boolean expirado = isTokenExpired(token);
        if (expirado) {
            return result;
        } else {
            return null;
        }
    }


    /**
     * Obtener el estado actual del
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public String getAplicacionUsuarioActual() {
        UsernamePasswordAuthenticationToken aut = (UsernamePasswordAuthenticationToken) SecurityContextHolder
                .getContext().getAuthentication();
        Map<String, Object> detalles = (HashMap<String, Object>) aut.getDetails();
        return detalles.get("aplicacion").toString();
    }
}
