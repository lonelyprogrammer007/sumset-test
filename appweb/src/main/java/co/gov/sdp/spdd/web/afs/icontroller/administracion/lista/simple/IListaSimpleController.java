package co.gov.sdp.spdd.web.afs.icontroller.administracion.lista.simple;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.ListaSimpleDTO;



/**
 * Clase que maneja los metodos de peticiones get,post,put y delete de listas
 * simples y argumentos
 *
 * @author Alex Leal
 *
 */

public interface IListaSimpleController {
	

	
	public String editarListaSimple(Model model, ListaSimpleDTO list);
 
	
	public String paginaArgumentos(Model model, ListaSimpleDTO idListaSimple);

	
	public String crearArgumento(ArgumentoListaSimpleDTO arg, Model model, RedirectAttributes redirectAttributes);


	public String postEditarListaSimple(ArgumentoListaSimpleDTO argumento, Model model, RedirectAttributes redirectAttributes);


}