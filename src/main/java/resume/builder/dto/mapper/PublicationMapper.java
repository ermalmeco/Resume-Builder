package resume.builder.dto.mapper;

import io.gitgub.eaxdev.jsonresume.validator.model.Language;
import io.gitgub.eaxdev.jsonresume.validator.model.Publication;

public class PublicationMapper {
    public static Publication toPublicationDto(resume.builder.entity.Publication publication){
        Publication publicationDto = new Publication();
        publicationDto.setName(publication.getName());
        publicationDto.setPublisher(publication.getPublisher());
        publicationDto.setReleaseDate(publication.getReleaseDate());
        publicationDto.setSummary(publication.getSummary());
        publicationDto.setWebsite(publication.getWebsite());

        return publicationDto;
    }
}