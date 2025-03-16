package excursionweb.dao;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import excursionweb.javabean.Excursion;



 @Repository
public class ExcursionDaoImplList implements ExcursionDao {
	 
	 private List<Excursion> lista;
		
		public ExcursionDaoImplList() {
			lista= new ArrayList<>();
			cargarDatos();
		}

		private void cargarDatos() {
			lista.add(new Excursion (1001,"Viaje de negocios", "Bilbao", "Orense",LocalDate.of (2020,05,13),3,"CREADO","Y",150,70,900, "Congreso",LocalDate.now()));
			lista.add(new Excursion (1002,"Viaje culinario", "Madrid", "Orense",LocalDate.of(2024,11,03),3,"CREADO","Y",30,20,1200, "Evento culinario",LocalDate.now()));
			lista.add(new Excursion (1003,"Viaje de placer", "Madrid", "Orense",LocalDate.of(2024,11,03),3,"TERMINADO","Y",100,70,700, "Visita Guiada",LocalDate.now()));
			lista.add(new Excursion (1004,"Viaje de negocios", "Madrid", "Orense",LocalDate.of(2024,11,03),3,"CREADO","N",250,100,900, "Congreso",LocalDate.now()));
			lista.add(new Excursion (1005,"Viaje de placer", "Madrid", "Orense",LocalDate.of(2024,11,03),3,"CREADO","N",150,50,500, "Visita Guiada",LocalDate.now()));
			lista.add(new Excursion (1006,"Viaje deportivo", "Madrid", "Orense",LocalDate.of(2024,11,03),3,"TERMINADO","Y",250,200,300, "Evento Deportivo",LocalDate.now()));
			lista.add(new Excursion (1007,"Viaje de placer", "Madrid", "Orense",LocalDate.of(2024,11,03),3,"CANCELADO","N",150,70,900, "Visita Guiada",LocalDate.now()));
			
		}
		

		//BUSCAMOS POR ID
		
		@Override
		public Excursion findById(int idExcursion) {
			for(Excursion ele:lista) {
				if(ele.getIdExcursion()==idExcursion)
					return ele;
			}
			return null;
		}
		//RECOGEMOS TODOS LOS OBJETOS DE LA BBDD

		@Override
		public List<Excursion> findAll() {
			System.out.println(lista);
			return lista;
		}

		
		/*ASIGNO POR DEFECTO UN ID, COMPROBAMOS SI EXISTE LA EXCURSION EN ESTE FORMULARIO, 
		 * PERO DESDE AQUI, EL ID,ADJUDICADO INTERNO, ESTA PROTEGIDO, LA FECHA DE ALTA, SE FIJA AUTOMÁTICAMNTE TAMBIÉN
		 * Y TAMBIÉN ESTÁ PROTEGIDA, SE QUE SE PEGA REVENTON, PERO NO PUEDO HACERLO MEJOR PARA EVITAR DUPLICADOS */
		@Override
		public int insertOne(Excursion excursion) {
			for (Excursion ele:lista)
				if(ele.getDescripcion().equalsIgnoreCase(excursion.getDescripcion())
					&& ele.getOrigen().equalsIgnoreCase(excursion.getOrigen())
					&& ele.getDestino().equalsIgnoreCase(excursion.getDestino())
					&& ele.getFechaExcursion()==(excursion.getFechaExcursion())
					&& ele.getAforoMaximo()==(excursion.getAforoMaximo())
					&& ele.getMinimoAsistentes()==(excursion.getMinimoAsistentes())
					&& ele.getDuracion()==(excursion.getDuracion())
					&& ele.getPrecioUnitario()==(excursion.getPrecioUnitario())
					&& ele.getDestacado().equals(excursion.getDestacado())
					&& ele.getEstado().equals(excursion.getEstado()))
				return 0;
			else {
				int pos=lista.size()-1;
				int nuevoId =(int) (lista.get(pos).getIdExcursion()+1);
				excursion.setIdExcursion(nuevoId);
				
				}
			return lista.add(excursion) ? 1 : 0;
		}
		
		/* A PARTIR DE AQUI, NO HAY COMPROBACIONES DE ID, PORQUE ESTÁ PROTEGIDO, Y PORQUE EN LA MAYORIA DE LOS CASOS, ENTRAN POR PATH
		 * CON LO CUAL, EXISTEN, SINO, NO APARECERIAN EN LA APP*/
		
		@Override //MODIFICAR UNA EXCURSION
		public int updateOne(Excursion excursion) {
			
			return (lista.set(lista.indexOf(excursion), excursion) != null) ? 1 : 0;
		}
		
		
		//BUSCAR EXCURSIONES POR ESTADO
			@Override 
		public List<Excursion> findByDestacados() {
			List <Excursion> lista2 = new ArrayList<>();
			String cadena="Y";
			for(Excursion ele:lista) {
				if(ele.getDestacado().contains(cadena)) 
				lista2.add(ele);
				
				}
			return lista2;
		}
			
			//BUSCAR EXCURSIONES POR ESTADO
		@Override 
		public List<Excursion> findByActivos() {
			List <Excursion> lista2 = new ArrayList<>();
			String cadena="CREADO";
			for(Excursion ele:lista) {
				if(ele.getEstado().contains(cadena)) 
				lista2.add(ele);
				
				}
			return lista2;
		}
		
		
		//BUSCAR EXCURSIONES POR ESTADO
		@Override 
		public List<Excursion> findByCancelados() {
			
			List <Excursion> lista2 = new ArrayList<>();
			String cadena="CANCELADO";
			
			for(Excursion ele:lista) {
				if(ele.getEstado().contains(cadena)) 
				lista2.add(ele);
				
				}
			return lista2;
		}
		

		// CANCELAR UNA EXCURSION
		@Override 
		public Excursion cancelOne(int idExcursion) {
			
			for(Excursion ele:lista) {
				if(ele.getIdExcursion()==idExcursion & !ele.getEstado().equals("CANCELADO")) {
					ele.setEstado("CANCELADO");			
					return ele;
				}
				
			}
			return null;
		}
		
		//METODOS PROPIOS
	
		//BUSCAR EXCURSIONES POR ESTADO
		public List<Excursion> findByTerminados() {
			List <Excursion> lista2 = new ArrayList<>();
			String cadena="TERMINADO";
			for(Excursion ele:lista) {
				if(ele.getEstado().contains(cadena)) 
				lista2.add(ele);
				
				}
			return lista2;
		}
		
		//BUSCAR EXCURSIONES POR RANGO DE PRECIO
		public List<Excursion> findByPrecio(double precioMin,double precioMax) {
			
			List <Excursion> lista2 = new ArrayList<>();
			
			for(Excursion ele:lista) {
				if(ele.getPrecioUnitario()>=precioMin && ele.getPrecioUnitario()<=precioMax) 
				lista2.add(ele);
				
				}
			return lista2;
		}
		
		
		//BUSCAR EXCURSIONES POR ORIGEN
		public List<Excursion> findByOrigen(String origen) {
			List <Excursion> lista2 = new ArrayList<>();
			for(Excursion ele:lista) {
				if(ele.getOrigen().equalsIgnoreCase(origen)) 
				lista2.add(ele);
				
				}
			return lista2;
		}
		
		
		//BUSCAR EXCURSIONES POR DESTINO
		public List<Excursion> findByDestino(String destino) {
			List <Excursion> lista2 = new ArrayList<>();
			for(Excursion ele:lista) {
				if(ele.getDestino().equalsIgnoreCase(destino)) 
				lista2.add(ele);
				
				}
			return lista2;
		}
		
		
		//BUSCAR EXCURSIONES POR PRECIO
		public List<Excursion> findByMin(double precioMin) {
			List <Excursion> lista2 = new ArrayList<>();
			for(Excursion ele:lista) {
				if(ele.getPrecioUnitario()>= precioMin) 
				lista2.add(ele);
				
				}
			return lista2;
		}
		
		
		//BUSCAR EXCURSIONES POR PRECIO
		public List<Excursion> findByMax(double precioMax) {
			List <Excursion> lista2 = new ArrayList<>();
			for(Excursion ele:lista) {
				if(ele.getPrecioUnitario()>=precioMax) 
				lista2.add(ele);
				
				}
			return lista2;
	
}
		@InitBinder
	    public void initBinder(WebDataBinder binder){
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	    }
 }
