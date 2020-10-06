package resume.builder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import resume.builder.entity.Reference;

public interface ReferenceRepository extends JpaRepository<Reference,Integer> {
}
