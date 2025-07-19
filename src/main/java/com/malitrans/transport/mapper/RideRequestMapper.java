package com.malitrans.transport.mapper;

import com.malitrans.transport.dto.RideRequestDTO;
import com.malitrans.transport.model.RideRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RideRequestMapper {
    RideRequestMapper INSTANCE = Mappers.getMapper(RideRequestMapper.class);

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "chauffeur.id", target = "chauffeurId")
    RideRequestDTO toDto(RideRequest rideRequest);

    @Mapping(target = "client.id", source = "clientId")
    @Mapping(target = "chauffeur.id", source = "chauffeurId")
    RideRequest toEntity(RideRequestDTO dto);
}
