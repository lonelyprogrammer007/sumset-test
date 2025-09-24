package co.gov.sdp.spdd.web.ip.icontroller.estructura.pot;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

public interface IEstructuraPOTController {

	public ResponseEntity listarPot(int draw,int start,int length,HttpServletRequest req);
}
