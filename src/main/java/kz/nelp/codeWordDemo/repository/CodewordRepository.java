package kz.nelp.codeWordDemo.repository;

import kz.nelp.codeWordDemo.model.Codeword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodewordRepository extends JpaRepository<Codeword, Integer> {

}
