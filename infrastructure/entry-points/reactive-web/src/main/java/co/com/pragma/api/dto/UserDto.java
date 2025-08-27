package co.com.pragma.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class UserDto {
    private String nombre;
    private String apellido;
    private String email;
    private String dni;
    private Long telefono;
    private Long salarioBase;
    private LocalDate fechaNacimiento;
    private String direccion;
}
