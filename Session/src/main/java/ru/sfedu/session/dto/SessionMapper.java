package ru.sfedu.session.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.sfedu.session.Session;

@Mapper(componentModel = "spring")
public interface SessionMapper {
    SessionMapper INSTANCE = Mappers.getMapper(SessionMapper.class);
    @Mapping(source = "date", target = "date")
    SessionDTO sessionToSessionDTO(Session session);
    Session sessionDTOToSession(SessionDTO sessionDTO);

}
