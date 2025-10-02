package com.example.proveedor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // // Campos que se mapearán como columnas en la tabla "proveedor"
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
}
