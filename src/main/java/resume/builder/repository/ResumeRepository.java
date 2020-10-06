package resume.builder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import resume.builder.entity.Resume;

public interface ResumeRepository extends JpaRepository<Resume,Integer> {
    Resume findResumeByUserId(int userId);
}
