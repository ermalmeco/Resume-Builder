package resume.builder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import resume.builder.entity.Work;

public interface WorkRepository extends JpaRepository<Work,Integer> {
}
