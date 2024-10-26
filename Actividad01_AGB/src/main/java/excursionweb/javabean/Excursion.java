package excursionweb.javabean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;



//NUESTRA CLASE PRINCIPAL 

public class Excursion implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	private int idExcursion;
	private String descripcion;
	private String origen;
	private String destino;
	private LocalDate fechaExcursion;
	private int duracion;
	private String estado;
	private String destacado;
	private int aforoMaximo;
	private int minimoAsistentes;
	private double precioUnitario;
	private String imagen;
	private final LocalDate fechaAlta;
	
	public Excursion() {
		super();
		
		this.fechaAlta =  LocalDate.now();
		
	}

	public Excursion(int idExcursion, String descripcion, String origen, String destino, LocalDate fechaExcursion,
			int duracion, String estado, String destacado, int aforoMaximo, int minimoAsistentes, double precioUnitario,
			String imagen, LocalDate fechaAlta) {
		super();
		this.idExcursion = idExcursion;
		this.descripcion = descripcion;
		this.origen = origen;
		this.destino = destino;
		this.fechaExcursion = fechaExcursion;
		this.duracion = duracion;
		this.estado = estado;
		this.destacado = destacado;
		this.aforoMaximo = aforoMaximo;
		this.minimoAsistentes = minimoAsistentes;
		this.precioUnitario = precioUnitario;
		this.imagen = imagen;
		this.fechaAlta = fechaAlta;
	}

	public int getIdExcursion() {
		return idExcursion;
	}

	public void setIdExcursion(int idExcursion) {
		this.idExcursion = idExcursion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public LocalDate getFechaExcursion() {
		return fechaExcursion;
	}

	public void setFechaExcursion(LocalDate fechaExcursion) {
		this.fechaExcursion = fechaExcursion;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDestacado() {
		return destacado;
	}

	public void setDestacado(String destacado) {
		this.destacado = destacado;
	}

	public int getAforoMaximo() {
		return aforoMaximo;
	}

	public void setAforoMaximo(int aforoMaximo) {
		this.aforoMaximo = aforoMaximo;
	}

	public int getMinimoAsistentes() {
		return minimoAsistentes;
	}

	public void setMinimoAsistentes(int minimoAsistentes) {
		this.minimoAsistentes = minimoAsistentes;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	/*No hay set de fecha de alta se construye una vez al crear el objeto*/

	@Override
	public int hashCode() {
		return Objects.hash(idExcursion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Excursion))
			return false;
		Excursion other = (Excursion) obj;
		return idExcursion == other.idExcursion;
	}

	@Override
	public String toString() {
		return "Excursion [idExcursion=" + idExcursion + ", descripcion=" + descripcion + ", origen=" + origen
				+ ", destino=" + destino + ", fechaExcursion=" + fechaExcursion + ", duracion=" + duracion + ", estado="
				+ estado + ", destacado=" + destacado + ", aforoMaximo=" + aforoMaximo + ", minimoAsistentes="
				+ minimoAsistentes + ", precioUnitario=" + precioUnitario + ", imagen=" + imagen + ", fechaAlta="
				+ fechaAlta + "]";
	}

	
	@InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
	
}
