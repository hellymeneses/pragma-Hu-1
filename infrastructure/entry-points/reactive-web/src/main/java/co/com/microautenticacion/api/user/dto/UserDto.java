package co.com.microautenticacion.api.user.dto;

import co.com.microautenticacion.api.shared.anotaciones.OnlyLetters;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {

  private String identification;

  @NotBlank(message = "El nombre es obligatorio")
  @OnlyLetters
  private String firstName;

  @NotBlank(message = "El apellido es obligatorio")
  @OnlyLetters
  private String lastName;

  @NotBlank(message = "El correo es obligatorio")
  @Email(message = "El correo no tiene un formato válido")
  private String email;

  private String address;
  @Size(min = 10, max = 10, message = "El número debe tener exactamente 10 dígitos")
  private String phoneNumber;

  private Date dateOfBirth;

  @NotNull(message = "El salario base es obligatorio")
  @DecimalMin(value = "0", inclusive = true, message = "El salario base debe ser al menos 0")
  @DecimalMax(value = "15000000", inclusive = true, message = "El salario base no puede ser mayor a 15,000,000")
  private BigDecimal baseSalary;

}