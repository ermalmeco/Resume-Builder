package resume.builder.dto.mapper;

import io.gitgub.eaxdev.jsonresume.validator.model.Language;

public class LanguageMapper {
    public static Language toLanguageDto(resume.builder.entity.Language language){
        Language languageDto = new Language();
        languageDto.setLanguage(language.getLanguage());
        languageDto.setFluency(language.getFluency());

        return languageDto;
    }
}