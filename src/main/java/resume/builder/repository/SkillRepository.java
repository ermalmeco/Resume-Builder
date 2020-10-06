package resume.builder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import resume.builder.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill,Integer> {
}
