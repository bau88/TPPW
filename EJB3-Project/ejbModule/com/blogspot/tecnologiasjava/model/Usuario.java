package com.blogspot.tecnologiasjava.model;

import java.io.Serializable; 
import java.util.List;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity; 
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity 
@Table(name="usuario")
public class Usuario extends EntidadBase implements Serializable{
	 private static final long serialVersionUID = 1L; 
     
	    @Id 
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Integer idUsuario; 
	    
	    //Los demas atributos seran persistidas mediante reflexion
	    private String nombreUsuario; 
	    
	    private String nombre; 
	    
	    private String contrasenha;
	    
	    public Usuario(){
	    	
	    }
		
	    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    private List<Rol> roles = new ArrayList<Rol>();
	    
	    public List<Rol> getRoles() {
			return roles;
		}
		public void setRoles(List<Rol> roles) {
			this.roles = roles;
		}
		public Integer getIdUsuario() {
			return idUsuario;
		}
		public void setIdUsuario(Integer idUsuario) {
			this.idUsuario = idUsuario;
		}
		public String getNombreUsuario() {
			return nombreUsuario;
		}
		public void setNombreUsuario(String nombreUsuario) {
			this.nombreUsuario = nombreUsuario;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getContrasenha() {
			return contrasenha;
		}
		public void setContrasenha(String contrasenha) {
			this.contrasenha = contrasenha;
		} 

	   
}
