package org.rail.project.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CharacterDto {
    private String name;
    private BigDecimal damage;
    private Integer health;
    private BigDecimal speed;
    private BigDecimal luck;
}
