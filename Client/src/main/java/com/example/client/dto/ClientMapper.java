package com.example.client.dto;


import com.example.client.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
    @Mapping(source = "name", target = "name")
    @Mapping(source = "surname", target = "surname")
    @Mapping(source = "contact", target = "contact")
    ClientDTO clinetToClientDTO(Client client);
    Client clientDTOToclient(ClientDTO clientDTO);


}
