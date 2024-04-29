package ru.sfedu.session.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.sfedu.session.Session;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface SessionMapper {
    SessionMapper INSTANCE = Mappers.getMapper(SessionMapper.class);

    @Mappings({
            @Mapping(source = "date", target = "date")
    })
    SessionDTO sessionToSessionDTO(Session session);

    @Mappings({
            @Mapping(source = "date", target = "date")
    })
    Session sessionDTOToSession(SessionDTO sessionDTO);

    // Добавляем методы преобразования для LocalDateTime
    default LocalDateTime map(LocalDateTime value) {
        return value;
    }

    default LocalDateTime map(String value) {
        return LocalDateTime.parse(value);
    }
}

