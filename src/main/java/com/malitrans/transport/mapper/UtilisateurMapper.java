package com.malitrans.transport.mapper;

import com.malitrans.transport.dto.UtilisateurDTO;
import com.malitrans.transport.model.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {
    UtilisateurMapper INSTANCE = Mappers.getMapper(UtilisateurMapper.class);

    UtilisateurDTO toDto(Utilisateur utilisateur);
    Utilisateur toEntity(UtilisateurDTO dto);
}
