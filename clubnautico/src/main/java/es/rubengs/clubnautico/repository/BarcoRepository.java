package es.rubengs.clubnautico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.rubengs.clubnautico.model.Barco;

@Repository
public interface BarcoRepository extends JpaRepository<Barco,Integer>{

}