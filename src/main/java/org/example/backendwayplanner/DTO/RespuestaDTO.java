package org.example.backendwayplanner.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RespuestaDTO {
    public Integer estado;
    private String token;
    private String mensaje;
    private Object cuerpo;
}
