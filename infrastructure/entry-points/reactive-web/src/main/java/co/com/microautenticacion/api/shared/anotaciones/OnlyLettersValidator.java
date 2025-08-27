package co.com.microautenticacion.api.shared.anotaciones;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OnlyLettersValidator implements ConstraintValidator<OnlyLetters, String> {

  private static final String ONLY_LETTERS_REGEX = "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$";

  @Override
  public void initialize(OnlyLetters constraintAnnotation) {

  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null || value.isEmpty()) {
      return true;
    }
    return value.matches(ONLY_LETTERS_REGEX);
  }
}