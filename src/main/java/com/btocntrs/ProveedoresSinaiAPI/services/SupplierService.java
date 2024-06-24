/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btocntrs.ProveedoresSinaiAPI.services;

import com.btocntrs.ProveedoresSinaiAPI.entities.Supplier;
import com.btocntrs.ProveedoresSinaiAPI.exceptions.InvalidSupplierException;
import com.btocntrs.ProveedoresSinaiAPI.exceptions.SupplierNotFoundException;
import com.btocntrs.ProveedoresSinaiAPI.repositories.SupplierRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author btocn
 */
@Service
public class SupplierService {
    
    @Autowired
    private SupplierRepository supplierRepository;
    
    public Supplier createSupplier(Supplier supplier) throws InvalidSupplierException{
        if (supplier.isValidSupplier()){
            return supplierRepository.save(supplier);
        }
        
        return null;
    }
    
    public List<Supplier> readAllSuppliers(){
        return supplierRepository.findAll();
    }
    
    public Supplier readSupplierById(Long id) throws SupplierNotFoundException{
        return supplierRepository.findById(id).orElseThrow(() -> new SupplierNotFoundException("Supplier not found"));
    }
    
    public List<Supplier> readSupplierByRfc(String rfc){
        List<Supplier> suppliers = supplierRepository.findByRfc(rfc);
        if (suppliers == null || suppliers.isEmpty()){
            throw new SupplierNotFoundException("Supplier not found by rfc");
        }
        return suppliers;
    }
    
    public List<Supplier> readSuppliersByBanco(String banco){
        return supplierRepository.findByBancoLike("%" + banco + "%");
    }
    
    public Supplier updateSupplier(Supplier updateSupplier) throws SupplierNotFoundException, InvalidSupplierException{
        Supplier supplierDB = readSupplierById(updateSupplier.getId());
        if (updateSupplier.isValidSupplier()){
            return supplierRepository.save(updateSupplier);
        }
        
        return supplierDB;
    }
    
    public Supplier updateSupplierByRfc(String rfc, Supplier updateSupplier) throws SupplierNotFoundException, InvalidSupplierException{
        Supplier supplierDB = readSupplierByRfc(rfc).getFirst();
        if (updateSupplier.isValidSupplier()){
            return supplierRepository.save(updateSupplier);
        }
        
        return supplierDB;
    }
    
    public void deleteSupplier(Supplier supplier){
        supplierRepository.deleteById(supplier.getId());
    }
    
    public void deleteSupplierbyId(Long id){
        supplierRepository.deleteById(id);
    }
    
    public void deleteSupplierByRfc(String rfc){
        supplierRepository.deleteByRfc(rfc);
    }
    
}
