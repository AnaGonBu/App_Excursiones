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

import excursionweb.dao.ExcursionDao;

@Controller
public class HomeController {
	
	@Autowired
	private ExcursionDao edao;/*recogemos el objeto ExcursionDaoImplList q incluye los datos cargados y metodos
							  nuestra bbdd*/
	
	//RECOGEMOS PETICION DE CARGAR LA BBDD EN LA APP
	@GetMapping("/")
	public String procHome(Model model) {
		
		model.addAttribute("excursiones",edao.findAll());//excursiones es la lista de excusiones del ImplList
		return "home";									//q es lo que mandamos al home de la app en nuestro caso
	
	}
	@InitBinder
	public void iniBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}
