package resume.builder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import resume.builder.entity.Publication;
import resume.builder.entity.Resume;
import resume.builder.repository.PublicationRepository;

@Service
public class PublicationService {

    @Autowired
    PublicationRepository repository;

    public Publication addPublication(io.gitgub.eaxdev.jsonresume.validator.model.Publication publication, Resume result){
        Publication newPublication = new Publication();
        newPublication.setName(publication.getName());
        newPublication.setPublisher(publication.getPublisher());
        newPublication.setReleaseDate(publication.getReleaseDate());
        newPublication.setWebsite(publication.getWebsite());
        newPublication.setSummary(publication.getSummary());
        newPublication.setResumeObj(result);

        return repository.save(newPublication);
    }

    public void delete(Publication publication){
        repository.delete(publication);
    }

}
