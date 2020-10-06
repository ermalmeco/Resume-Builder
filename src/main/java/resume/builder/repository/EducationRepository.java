package resume.builder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import resume.builder.entity.Education;

public interface EducationRepository extends JpaRepository<Education,Integer> {
}
