package excursionweb.dao;

import java.util.List;

import excursionweb.javabean.Excursion;

public interface ExcursionDao {

	Excursion findById(int idExcursion);
	List<Excursion> findAll();
	int insertOne(Excursion excursion);
	int updateOne(Excursion excursion);
	Excursion cancelOne(int idExcursion);
	List<Excursion> findByActivos();
	List<Excursion> findByCancelados();
	List<Excursion> findByTerminados();
	List<Excursion> findByDestacados();
	List<Excursion> findByOrigen(String origen);
	List<Excursion> findByDestino(String destino);
	List<Excursion> findByMin(double precioUnitario);
	List<Excursion> findByMax(double precioUnitario);
	List<Excursion> findByPrecio(double precioMin, double precioMax);
	
	
	
	
	
}
