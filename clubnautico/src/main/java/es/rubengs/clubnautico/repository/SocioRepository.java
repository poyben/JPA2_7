package es.rubengs.clubnautico.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import es.rubengs.clubnautico.model.Socio;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Integer> {

}
