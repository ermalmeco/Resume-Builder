package resume.builder.dto.mapper;

import io.gitgub.eaxdev.jsonresume.validator.model.Interest;
import io.gitgub.eaxdev.jsonresume.validator.model.Language;
import resume.builder.Utils.Constants;

public class LanguageMapper {
    public static Language toLanguageDto(resume.builder.entity.Language language){
        Language languageDto = new Language();
        languageDto.setLanguage(language.getLanguage());
        languageDto.setFluency(language.getFluency());

        return languageDto;
    }
}