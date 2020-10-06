package resume.builder.dto.mapper;

import io.gitgub.eaxdev.jsonresume.validator.model.Profiles;

public class ProfileMapper {
    public static Profiles toProfileDto(resume.builder.entity.Profiles profile){
        Profiles profileDto = new Profiles();
        profileDto.setUsername(profile.getUsername());
        profileDto.setNetwork(profile.getNetwork());
        profileDto.setUrl(profile.getUrl());

        return profileDto;
    }
}

