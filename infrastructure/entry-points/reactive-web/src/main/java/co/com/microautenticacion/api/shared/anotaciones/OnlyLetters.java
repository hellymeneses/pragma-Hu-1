package co.com.microautenticacion.api.shared.anotaciones;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OnlyLettersValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface OnlyLetters {

  String message() default "El campo solo debe contener letras";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}