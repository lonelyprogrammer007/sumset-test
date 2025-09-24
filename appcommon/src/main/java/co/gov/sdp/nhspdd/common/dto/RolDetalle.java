package co.gov.sdp.nhspdd.common.dto;



/**
 * Esta clase implementa GrantedAuthority encargada de determinar los roles por
 * usuario
 *
 * @author mtovar
 *
 */
public class RolDetalle  {

    private static final long serialVersionUID = 1L;

    private String authority;

    /**
     * Constructor por default
     */
    public RolDetalle() {
    }

    /**
     * Constructor que recibe el id del rol
     *
     * @param id
     */
    public RolDetalle(String authority) {
        this.authority = authority;
    }

    
    public String getAuthority() {
        return authority;
    }

    /**
     *
     * @param authority
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return authority;
    }

}
