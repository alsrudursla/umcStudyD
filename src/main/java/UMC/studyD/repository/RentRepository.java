package UMC.studyD.repository;

import UMC.studyD.domain.mapping.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
}
