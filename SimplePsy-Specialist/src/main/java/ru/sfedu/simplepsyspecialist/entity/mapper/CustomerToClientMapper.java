package ru.sfedu.simplepsyspecialist.entity.mapper;

import org.springframework.web.bind.annotation.Mapping;
import ru.sfedu.simplepsyspecialist.entity.Client;
import ru.sfedu.simplepsyspecialist.entity.Customer;

@Mapper
public interface CustomerToClientMapper {

    CustomerToClientMapper INSTANCE = Mappers.getMapper(CustomerToClientMapper.class);

    @Mapping(source = "lastName", target = "middleName")
    @Mapping(source = "status", target = "source")
    @Mapping(source = "dateOfFirstRequest", target = "dateOfFirstContact")
    @Mapping(source = "preferredMeetingFormat", target = "preferMeetingFormat")
    @Mapping(source = "onlineMeetingPlace", target = "interactionPlatform")
    @Mapping(source = "offlineMeetingPlace", target = "meetingAddress")
    @Mapping(source = "sex", target = "gender")
    @Mapping(source = "dateOfBirth", target = "birthDay")
    @Mapping(source = "familyStatus", target = "maritalStatus")
    @Mapping(source = "problemsId", target = "problems")
    Client customerToClient(Customer customer);
}