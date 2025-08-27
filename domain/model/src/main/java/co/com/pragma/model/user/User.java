package co.com.pragma.model.user;
import co.com.pragma.exceptions.DomainException;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {

    private static final String EL_CAMPO_NOMBRE_ES_OBLIGATORIO = "El campo nombre es obligatorio";
    private static final String EL_CAMPO_APELLIDO_ES_OBLIGATORIO = "El campo apellido es obligatorio";
    private static final String EL_CAMPO_EMAIL_ES_OBLIGATORIO = "El campo email es obligatorio";
    private static final String EL_CAMPO_SALARIO_ES_OBLIGATORIO = "El campo salario es obligatorio";
    private BigInteger id;
    private String nombre;
    private String apellido;
    private String email;
    private String dni;
    private Long telefono;
    private Long salarioBase;
    private LocalDate fechaNacimiento;
    private String direccion;

    public void validarCamposObligatorios(){
        this.validarCampoString(this.nombre, EL_CAMPO_NOMBRE_ES_OBLIGATORIO);
        this.validarCampoString(this.apellido, EL_CAMPO_APELLIDO_ES_OBLIGATORIO);
        this.validarCampoString(this.email, EL_CAMPO_EMAIL_ES_OBLIGATORIO);
        this.validarCampoLong(this.salarioBase, EL_CAMPO_SALARIO_ES_OBLIGATORIO);
    }

    private void validarCampoString(String campo, String message){
        if(campo == null || campo.isEmpty()){
            throw new DomainException(message);
        }
    }

    private void validarCampoLong(Long campo, String message){
        if(campo == null){
            throw new DomainException(message);
        }
    }
}
