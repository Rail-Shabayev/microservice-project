package org.rail.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 3, message = "Name is too short. (min = 3, max = 20)")
    @Size(max = 20, message = "Name is too big. (min = 3, max = 20)")
    private String name;

    @DecimalMin(value = "0.1", message = "Damage can't be smaller than 0.1")
    private BigDecimal damage;

    @Max(value = 24, message = "Health can't be bigger that 24.")
    @Min(value = 1, message = "Health 1 is minimum.")
    private Integer health;

    @DecimalMax(value = "2.0", message = "Speed can't be more than 2.0.")
    @DecimalMin(value = "0.1", message = "Speed can't be smaller than 0.1")
    private BigDecimal speed;

    @DecimalMin(value = "0", message = "Luck can't be smaller than 0")
    private BigDecimal luck;
}
