package com.example.client.dto;

import com.example.client.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "surname", target = "surname")
    @Mapping(source = "contact", target = "contact")
    Client customerDTOToClient(CustomerDTO customerDTO);
    CustomerDTO clientToCustomerDTO(Client client);

}
