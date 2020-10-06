package resume.builder.dto.mapper;

import io.gitgub.eaxdev.jsonresume.validator.model.Reference;

public class ReferenceMapper {
    public static Reference toReferenceDto(resume.builder.entity.Reference reference){
        Reference referenceDto = new Reference();
        referenceDto.setName(reference.getName());
        referenceDto.setReference(reference.getReference());

        return referenceDto;
    }
}