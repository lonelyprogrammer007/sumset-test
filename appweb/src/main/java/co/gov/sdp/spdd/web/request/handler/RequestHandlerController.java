package co.gov.sdp.spdd.web.request.handler;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class RequestHandlerController {

	@ExceptionHandler(HttpSessionRequiredException.class)
	public String errorInternoServidor(Model model, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("sesion", 1);
		return "redirect:/";

	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String paginaNoEncontrada() {
		return "redirect:/home";
	}

	@ExceptionHandler(HttpClientErrorException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String paginaProhibida(Model model, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("prohibido", 1);
		return "redirect:/home";
	}

	@ExceptionHandler(HttpServerErrorException.class)
	public String restInternalError(Model model, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("errorSeguridad", 1); // ha ocurrido un error en el servicio de seguridads
		return "redirect:/";
	}

}
