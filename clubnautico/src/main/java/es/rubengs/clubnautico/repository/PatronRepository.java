package es.rubengs.clubnautico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.rubengs.clubnautico.model.Patron;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Integer> {

}
