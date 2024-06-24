/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btocntrs.ProveedoresSinaiAPI.controllers;

import com.btocntrs.ProveedoresSinaiAPI.entities.Supplier;
import com.btocntrs.ProveedoresSinaiAPI.exceptions.SupplierNotFoundException;
import com.btocntrs.ProveedoresSinaiAPI.services.SupplierService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author btocn
 */
@RestController
@RequestMapping("/suppliers")
public class SupplierController {
    
    @Autowired
    private SupplierService supplierService;
    
    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier){
            Supplier savedSupplier = supplierService.createSupplier(supplier);
            return new ResponseEntity<>(savedSupplier, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<Supplier>> readAllSuppliers(){
        List<Supplier> allSuppliers = supplierService.readAllSuppliers();
        return new ResponseEntity<>(allSuppliers, HttpStatus.OK);
    } 
    
    @GetMapping("/{rfc}")
    public ResponseEntity readSupplierByRfc(@PathVariable String rfc) throws SupplierNotFoundException{
        Supplier supplier = supplierService.readSupplierByRfc(rfc).getFirst();
        return new ResponseEntity<>(supplier, HttpStatus.FOUND);
    }
    
    @GetMapping("/search")
    public ResponseEntity readSuppliersByBanco(@RequestParam(name = "banco") String banco){
        List<Supplier> suppliers = supplierService.readSuppliersByBanco(banco);
        return new ResponseEntity<>(suppliers, HttpStatus.FOUND);
    }
    
    @PutMapping
    public ResponseEntity<Supplier> updateSupplier(@RequestBody Supplier supplier){
        Supplier updatedSupplier = supplierService.updateSupplier(supplier);
        return new ResponseEntity<>(updatedSupplier, HttpStatus.OK);
    }
    
    @PutMapping("/{rfc}")
    public ResponseEntity<Supplier> updateSupplierByRfc(@PathVariable String rfc, @RequestBody Supplier supplier){
        Supplier updatedSupplier = supplierService.updateSupplierByRfc(rfc, supplier);
        return new ResponseEntity<>(updatedSupplier, HttpStatus.OK);
    }
    
    
    @DeleteMapping
    public ResponseEntity<Void> deleteSupplier(@RequestBody Supplier supplier){
        supplierService.deleteSupplier(supplier);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @DeleteMapping("/{rfc}")
    public ResponseEntity<Void> deleteSupplierByRfc(@PathVariable String rfc){
        supplierService.deleteSupplierByRfc(rfc);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
