package jhenriquedsm.SpringREST.repositories;

import jhenriquedsm.SpringREST.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);

    // Define custom query using JPQL with index parameters
    @Query("select p from Person p where p.firstName =?1 and p.lastName =?2")
    Person findByJPQL(String firstName, String lastName);

    // Define custom query using JPQL with named parameters
    @Query("select p from Person p where p.firstName =:firstName and p.lastName =:lastName")
    Person findByJPQLNamedParameters(@Param("firstName") String firstName, @Param("lastName") String lastName);

    // Define custom query using NativeSQL with index parameters
    @Query(value = "select * from person p where p.first_name =?1 and p.last_name =?2", nativeQuery = true)
    Person findByNativeSQL(String firstName, String lastName);

    // Define custom query using NativeSQL with named parameters
    @Query(value = "select * from person p where p.first_name =:firstName and p.last_name =:lastName", nativeQuery = true)
    Person findByNativeSQLwithNamedParameters(@Param("firstName") String firstName, @Param("lastName") String lastName);
}