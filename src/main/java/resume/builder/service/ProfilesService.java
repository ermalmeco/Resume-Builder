package resume.builder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import resume.builder.entity.Profiles;
import resume.builder.entity.Resume;
import resume.builder.repository.ProfilesRepository;

@Service
public class ProfilesService {
    @Autowired
    ProfilesRepository repository;

    public Profiles addProfiles(io.gitgub.eaxdev.jsonresume.validator.model.Profiles profiles, Resume result){
        Profiles newProfile = new Profiles();
        newProfile.setNetwork(profiles.getNetwork());
        newProfile.setUsername(profiles.getUsername());
        newProfile.setUrl(profiles.getUrl());
        newProfile.setResumeObj(result);

        return repository.save(newProfile);
    }

    public void delete(Profiles profiles){
        repository.delete(profiles);
    }
}
