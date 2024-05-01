package ru.sfedu.problem.dto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.sfedu.problem.Problem;

@Mapper(componentModel = "spring")
public interface ProblemMapper {
    ProblemMapper INSTANCE = Mappers.getMapper(ProblemMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "descriptionOfProblem", target = "descriptionOfProblem")
    @Mapping(source = "scoringId", target = "scoringId")
    ProblemDTO problemToProblemDTO(Problem problem);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "descriptionOfProblem", target = "descriptionOfProblem")
    @Mapping(source = "scoringId", target = "scoringId")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "dateOfFirstContact", ignore = true)
    Problem problemDTOToProblem(ProblemDTO problemDTO);
}
