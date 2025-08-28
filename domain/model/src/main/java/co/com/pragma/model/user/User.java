package co.com.pragma.model.user;
import co.com.pragma.model.exceptions.DomainException;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.regex.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {

    private static final String FALTAN_CAMPOS_OBLIGATORIOS = "Faltan campos obligatorios";
    private static final int LIMITE_SALARIO = 15000000;
    private static final String EL_SALARIO_EXCEDE_EL_TOPE_MAXIMO = "El salario excede el tope maximo";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);
    public static final String ERROR_EN_EL_FORMATO_DEL_EMAIL = "Error en el formato del email";
    public static final int LIMITE_INFERIOR_SALARIO = 0;
    private BigInteger id;
    private String nombre;
    private String apellido;
    private String email;
    private String dni;
    private Long telefono;
    private Long salarioBase;
    private LocalDate fechaNacimiento;
    private String direccion;

    public void validarCamposCrearUsuario(){
        this.validarCampoObligatorioString(this.nombre);
        this.validarCampoObligatorioString(this.apellido);
        this.validarCampoObligatorioString(this.email);
        this.validarCampoLong(this.salarioBase);
        this.validarEmail();
        this.validarSalario();
    }

    private void validarCampoObligatorioString(String campo){
        if(campo == null || campo.trim().isEmpty()){
            throw new DomainException(FALTAN_CAMPOS_OBLIGATORIOS);
        }
    }

    private void validarCampoLong(Long campo){
        if(campo == null){
            throw new DomainException(FALTAN_CAMPOS_OBLIGATORIOS);
        }
    }

    private void validarEmail(){
        if(this.email != null && !pattern.matcher(this.email).matches()){
            throw new DomainException(ERROR_EN_EL_FORMATO_DEL_EMAIL);
        }
    }

    private void validarSalario(){
        if(this.salarioBase != null && (this.salarioBase > LIMITE_SALARIO || this.salarioBase < LIMITE_INFERIOR_SALARIO)){
            throw new DomainException(EL_SALARIO_EXCEDE_EL_TOPE_MAXIMO);
        }
    }
}
