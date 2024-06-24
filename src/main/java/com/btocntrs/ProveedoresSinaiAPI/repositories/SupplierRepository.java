/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.btocntrs.ProveedoresSinaiAPI.repositories;

import com.btocntrs.ProveedoresSinaiAPI.entities.Supplier;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author btocn
 */
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    
    List<Supplier> findByRfc(String rfc);
    
    List<Supplier> findByRazonSocialLike(String razonSocial);
    
    List<Supplier> findByBancoLikeIgnoreCase(String banco);
    
    @Transactional
    void deleteByRfc(String rfc);
    
}
