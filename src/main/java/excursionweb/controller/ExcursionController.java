package excursionweb.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import excursionweb.dao.ExcursionDao;
import excursionweb.javabean.Excursion;

@Controller
@RequestMapping("/excursiones")

public class ExcursionController {
	@Autowired
	private ExcursionDao edao;//recogemos el objeto ExcursionDaoImplList q incluye los datos cargados y metodos
	
	
	
	//DEVOLVER BUSQUEDA POR ORIGEN
	
	
	@GetMapping("/buscar/origen")
	public String findByOrigen(@RequestParam(required = false) String origen, Model model) {
		if ( origen == null ) {
	        model.addAttribute("mensaje", "Por favor, ingrese un origen.");
	        return "buscar";  
	    }// Volver al formulario	
	        else if (edao.findByOrigen(origen).size()>0) {
			model.addAttribute("excursiones", edao.findByOrigen(origen));
			model.addAttribute("mensaje", "Excursiones según Origen seleccionado ");
			 return "listaBuscados";
			 
			}else {
				model.addAttribute("mensaje", "No existen excursiones con el Origen seleccionado ");
			 return "buscar";
			}		
			
	}
	
	
	//DEVOLVER BUSQUEDA POR DESTINO
	
	@GetMapping("/buscar/destino")
	public String findByDestino(@RequestParam(required = false) String destino, Model model) {
		if (edao.findByDestino(destino).size()>0) {
			model.addAttribute("excursiones", edao.findByDestino(destino));
			model.addAttribute("mensaje", "Excursiones según Destino seleccionado ");
			 return "listaBuscados";
			 
			}else {
				model.addAttribute("mensaje", "No existen excursiones con el Destino seleccionado ");
			 return "buscar";
			}		
			
	}
	
	
	//DEVOLVER BUSQUEDA POR PRECIO
	
	@GetMapping("/buscar/precioMin")
	public String findByMin(@RequestParam(required = false) Double precioMin,
			Model model) {
		 // Verificar si los parámetros son nulos o inválidos
	    if ( precioMin == null ) {
	        model.addAttribute("mensaje", "Por favor, ingrese un valor válido.");
	        return "buscar";  
	    }// Volver al formulario	
	        else if(edao.findByMin(precioMin).size()>0) {
			model.addAttribute("excursiones", edao.findByMin(precioMin));
			model.addAttribute("mensaje", "Excursiones según Precio seleccionado ");
			 return "listaBuscados";
			 
			}else {
				model.addAttribute("mensaje", "No existen excursiones a ese precio ");
			 return "buscar";
			}		
			
	}
	
	//DEVOLVER BUSQUEDA POR PRECIO
	
	@GetMapping("/buscar/precioMax")
	public String findByMax(@RequestParam(required = false) Double precioMax,
			Model model) {
		 // Verificar si los parámetros son nulos o inválidos
	    if ( precioMax == null ) {
	        model.addAttribute("mensaje", "Por favor, ingrese un valor válido.");
	        return "buscar";  
	    }// Volver al formulario	
	        else if (edao.findByMax(precioMax).size()>0) {
		model.addAttribute("excursiones", edao.findByMax(precioMax));
		model.addAttribute("mensaje", "Excursiones según Precio Máximo ");
		 
		 return "listaBuscados";
	}else
		model.addAttribute("mensaje", "No existen excursiones a ese precio ");
	 return "buscar";
		
}
	
	//DEVOLVER BUSQUEDA POR RANGO PRECIOS
	
	@GetMapping("/buscar/rangoPrecios")
	public String findByPrecios(@RequestParam(required = false) Double precioMin, 
	                            @RequestParam(required = false) Double precioMax, 
	                            Model model) {
	    // Verificar si los parámetros son nulos o inválidos
	    if (precioMin == null || precioMax == null || precioMin < 0 || precioMax < 0 || precioMin > precioMax) {
	        model.addAttribute("mensaje", "Por favor, ingrese valores válidos para el rango de precios.");
	        return "buscar";  // Volver al formulario
	    }
	    else if (edao.findByPrecio(precioMin,precioMax).size()>0) {
			 model.addAttribute("excursiones", edao.findByPrecio(precioMin,precioMax));
			 model.addAttribute("mensaje", "Excursiones según rango de Precios ");
		 
		 return "listaBuscados";
	}else
		model.addAttribute("mensaje", "No existen excursiones en ese rango de precios ");
	 return "buscar";
		
}
	
	//RECOGEMOS PETICION DE BUSQUEDA
	
	@GetMapping("/buscar")
	public String findBy(Model model) {
		
		return "buscar";	
	}
	
	//RECOGEMOS PETICION DE DETALLE Y DEVOLVEMOS 
	
	@GetMapping("/destacado")
	public String findByDestacado(Model model) {
		if (edao.findByDestacados().size()>0) {
		model.addAttribute("excursiones",edao.findByDestacados());
		model.addAttribute("mensaje", "Excursiones Destacadas");
		return "detalleEstados";
		}else 
			model.addAttribute("mensaje", "No hay Excursiones Destacadas");
			return "forward:/";
		
	}
	
	//RECOGEMOS PETICION DE ESTADO Y DEVOLVEMOS
	
	@GetMapping("/estado/creado")
	public String findByActivo(Model model) {
		if (edao.findByActivos().size()>0) {
		model.addAttribute("excursiones",edao.findByActivos());
		model.addAttribute("mensaje", "Excursiones Activas");
		return "detalleEstados";
		}else
			model.addAttribute("mensaje", "No hay Excursiones Creadas");
		return "forward:/";
			
	}
	
	//RECOGEMOS PETICION DE ESTADO Y DEVOLVEMOS
	
	@GetMapping("/estado/terminado")
	public String findByCancelado(Model model) {
		if (edao.findByCancelados().size()>0) {
		model.addAttribute("excursiones",edao.findByCancelados());
		model.addAttribute("mensaje", "Excursiones Canceladas");
		return "detalleEstados";
		}else
			model.addAttribute("mensaje", "No hay Excursiones Terminadas");
		return "forward:/";
	}
	
	
	// DEVOLVEMOS RESULTADO PETICION MODIFICACION
	
	@PostMapping("/modificar/{idExcursion}")
	public String ProcModificar(@PathVariable int idExcursion, Excursion excursion,
			RedirectAttributes ratt) {
		
		excursion.setIdExcursion(idExcursion);
		
		if (edao.updateOne(excursion) == 1)
			ratt.addFlashAttribute("mensaje", "Excursion modificada correctamente");
		else
			ratt.addFlashAttribute("mensaje", "La excursion no se ha podido modificar");
			
		
		return "redirect:/";  // para la tarea, quema los datos y solo sbrevive el ratt
	}
	
	//RECOGEMOS PETICION MODIFICACION (quitaria la primera parte; no la veo ncesaria)
	
	@GetMapping("/modificar/{idExcursion}")
	public String ProcModificar(Model model, @PathVariable int idExcursion) {
	    Excursion excursion = edao.findById(idExcursion);
	    if (excursion == null) {
	        model.addAttribute("mensaje", "Excursión no existe");
	        return "forward:/";  /* Redirigir si la excursión no se encuentra, no deberia pasar
	        					*estamos pasando objetos directamente q estan en nuestra bbdd con
	        					 el campo id de solo lectura*/
	    }
	    model.addAttribute("excursion", excursion);
	    return "formModificarExcursion";
	}
	
	//DEVOLVEMOS RESULTADO DE PETICION ALTA
	
	@PostMapping("/alta")
	public String procFormAlta(Excursion excursion, RedirectAttributes ratt) {
		 if(edao.insertOne(excursion) == 1) 
			 ratt.addFlashAttribute("mensaje", "Alta realizada");
		 
		 else 
			 ratt.addFlashAttribute("mensaje", "Alta no realizada excursión duplicada");
		 
		 return "redirect:/";
	}
	
	//RECOGEMOS PETICION DE ALTA
	
	@GetMapping("/alta")
	public String procAlta() {
		
		return "formAlta";
	}
	
	//RECOGEMOS PETICION DE DETALLE Y DEVOLVEMOS
	
	@GetMapping("/detalle/{idExcursion}")
	public String verDetalle(Model model, @PathVariable int idExcursion) {
	
		Excursion excursion= edao.findById(idExcursion);
		model.addAttribute("excursion",excursion);
		
		return "detalleExcursion";	
		
		
	}
	
	//RECOGEMOS PETICION DE CANCELACION Y DEVOLVEMOS
	
	@GetMapping("/cancelar/{idExcursion}")
	public String cancelOne(Model model, @PathVariable int idExcursion) {
	
		Excursion exc= edao.cancelOne(idExcursion);
		
		if (exc != null)
			model.addAttribute("mensaje", "Excursion cancelada");
		else
			model.addAttribute("mensaje", "La excursion ya estaba cancelada anteriormente");
		
		return "forward:/";	
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
	
}
