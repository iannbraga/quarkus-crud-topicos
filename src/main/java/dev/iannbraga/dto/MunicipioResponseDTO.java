package dev.iannbraga.dto;

import dev.iannbraga.entity.Municipio;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
public class MunicipioResponseDTO {
    public String name;
    public String nameEstado;
    public String sigla;

    public MunicipioResponseDTO(String name, String nameEstado, String sigla){
        this.name = name;
        this.nameEstado = nameEstado;
        this.sigla = sigla;
    }

    public MunicipioResponseDTO(Municipio municipio){
        this.name = municipio.getName();
        this.nameEstado = municipio.getEstado().getName();
        this.sigla = municipio.getEstado().getSigla();
    }
}
