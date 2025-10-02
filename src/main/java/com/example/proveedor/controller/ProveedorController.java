package com.example.proveedor.controller;

import com.example.proveedor.model.Proveedor;
import com.example.proveedor.repository.ProveedorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marca la clase como un controlador REST (devuelve JSON)
@RequestMapping("/api/proveedores") // Prefijo común para todos los endpoints de este controlador
public class ProveedorController {

    private final ProveedorRepository repository;

    // Inyección del repositorio mediante constructor
    public ProveedorController(ProveedorRepository repository) {
        this.repository = repository;
    }

    // Crear un nuevo proveedor
    // POST /api/proveedores
    @PostMapping
    public Proveedor create(@RequestBody Proveedor proveedor) {
        return repository.save(proveedor); // Guarda en la base de datos
    }

    // Listar todos los proveedores
    // GET /api/proveedores
    @GetMapping
    public List<Proveedor> getAll() {
        return repository.findAll(); // Devuelve todos los registros
    }

    // Buscar un proveedor por ID
    // GET /api/proveedores/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok) // Si existe, devuelve 200 OK con el proveedor
                .orElse(ResponseEntity.notFound().build()); // Si no existe, devuelve 404
    }

    // Actualizar un proveedor existente
    // PUT /api/proveedores/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> update(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        return repository.findById(id)
                .map(p -> {
                    // Actualiza los campos
                    p.setNombre(proveedor.getNombre());
                    p.setDireccion(proveedor.getDireccion());
                    p.setTelefono(proveedor.getTelefono());
                    p.setEmail(proveedor.getEmail());
                    return ResponseEntity.ok(repository.save(p)); // Guarda cambios en BD
                })
                .orElse(ResponseEntity.notFound().build()); // Si no existe, devuelve 404
    }

    // Eliminar un proveedor
    // DELETE /api/proveedores/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return repository.findById(id)
                .map(p -> {
                    repository.delete(p); // Elimina el proveedor de la BD
                    return ResponseEntity.ok().<Void>build(); // Devuelve 200 OK sin contenido
                })
                .orElse(ResponseEntity.notFound().build()); // Si no existe, devuelve 404
    }
}
