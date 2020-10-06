package resume.builder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import resume.builder.entity.Reference;
import resume.builder.entity.Resume;
import resume.builder.repository.ReferenceRepository;

@Service
public class ReferenceService {
    @Autowired
    ReferenceRepository repository;

    public Reference addReference(io.gitgub.eaxdev.jsonresume.validator.model.Reference reference, Resume result){
        Reference newReference = new Reference();
        newReference.setName(reference.getName());
        newReference.setReference(reference.getReference());
        newReference.setResumeObj(result);

        return repository.save(newReference);
    }

    public void delete(Reference reference){
        repository.delete(reference);
    }

}
