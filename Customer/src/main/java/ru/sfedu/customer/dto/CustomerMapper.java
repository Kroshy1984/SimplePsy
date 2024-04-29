package ru.sfedu.customer.dto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.sfedu.customer.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "surname", target = "surname")
    @Mapping(source = "contact", target = "contact")
    CustomerDTO customerToCustomerDTO(Customer customer);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "surname", target = "surname")
    @Mapping(source = "contact", target = "contact")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "dateOfFirstCall", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "avatar", ignore = true)
    @Mapping(target = "byteAvatar", ignore = true)
    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
