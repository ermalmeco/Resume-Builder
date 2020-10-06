package resume.builder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import resume.builder.entity.Publication;

public interface PublicationRepository extends JpaRepository<Publication,Integer> {
}
