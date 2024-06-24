/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btocntrs.ProveedoresSinaiAPI.entities;

import com.btocntrs.ProveedoresSinaiAPI.exceptions.InvalidSupplierException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Alberto Contreras
 */
@Entity
@Table(name = "proveedores")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Supplier {
    
    private static final String RFC_REGEX = "^[A-ZÑ&]{3,4}\\d{6}[A-Z0-9]{3}$";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @JsonProperty("rfc")
    @Column(name = "rfc", unique = true, nullable = false, length = 13)
    private String rfc;
    
    @JsonProperty("razon_social")
    @Column(name = "razon_social", unique = true, nullable = false, length = 100)
    private String razonSocial;
    
    @JsonProperty("nombre_comercial")
    @Column(name = "nombre_comercial", unique = false, nullable = true, length = 100)
    private String nombreComercial;
    
    @JsonProperty("productos")
    @Column(name = "productos", unique = false, nullable = false, length = 150)
    private String productos;
    
    @JsonProperty("banco")
    @Column(name = "banco", unique = false, nullable = true, length = 80)
    private String banco;
    
    @JsonProperty("cuenta")
    @Column(name = "cuenta", unique = true, nullable = true, length = 50)
    private String cuenta;
    
    @JsonProperty("telefono")
    @Column(name = "telefono", unique = true, nullable = true, length = 20)
    private String telefono;
    
    public boolean updateSupplierDataWith(Supplier otherSupplier){
        if(!this.equals(otherSupplier)){
            
            if(!otherSupplier.rfc.replaceAll(" ", "").isEmpty() && otherSupplier.rfc != null && !otherSupplier.rfc.equals(this.rfc)){
            this.setRfc(otherSupplier.rfc);
            }
        
            if(!otherSupplier.razonSocial.replaceAll(" ", "").isEmpty() && otherSupplier.razonSocial != null && !otherSupplier.razonSocial.equals(this.razonSocial)){
                this.setRazonSocial(otherSupplier.razonSocial);
            }
        
            if(!otherSupplier.nombreComercial.replaceAll(" ", "").isEmpty() && otherSupplier.nombreComercial != null && !otherSupplier.nombreComercial.equals(this.nombreComercial)){
                this.setNombreComercial(otherSupplier.nombreComercial);
            }
        
            if(!otherSupplier.productos.replaceAll(" ", "").isEmpty() && otherSupplier.productos != null && !otherSupplier.productos.equals(this.productos)){
                this.setProductos(otherSupplier.productos);
            }
        
            if(!otherSupplier.banco.replaceAll(" ", "").isEmpty() && otherSupplier.banco != null && !otherSupplier.banco.equals(this.banco)){
                this.setBanco(otherSupplier.banco);
            }
        
            if(!otherSupplier.cuenta.replaceAll(" ", "").isEmpty() && otherSupplier.cuenta != null && !otherSupplier.cuenta.equals(this.cuenta)){
                this.setCuenta(otherSupplier.cuenta);
            }
        
            if(!otherSupplier.telefono.replaceAll(" ", "").isEmpty() && otherSupplier.telefono != null && !otherSupplier.telefono.equals(this.telefono)){
                this.setTelefono(otherSupplier.telefono);
            }
            
            return true;
        }
        
        return false;
    }
    
     // Método para validar RFC
    public static boolean isValidRFC(String rfc) {
        // Compilar la expresión regular
        Pattern pattern = Pattern.compile(RFC_REGEX);
        // Crear un matcher con la cadena RFC
        Matcher matcher = pattern.matcher(rfc);
        // Retornar si coincide con la expresión regular
        return matcher.matches();
    }
    
    @JsonIgnore
    public boolean isValidSupplier() throws InvalidSupplierException{
        
        if(this.getRfc() == null || this.getRfc().replaceAll(" ", "").isEmpty() || !isValidRFC(this.getRfc())){
            throw new InvalidSupplierException("Invalid RFC");
        }
        
        if(this.getRazonSocial() == null || this.getRazonSocial().replaceAll(" ", "").isEmpty()){
            throw new InvalidSupplierException("Razon Social is empty");
        }
        
        if(this.getProductos()== null || this.getProductos().replaceAll(" ", "").isEmpty()){
            throw new InvalidSupplierException("Productos is empty");
        }
        
        return true;
    }
    
}
