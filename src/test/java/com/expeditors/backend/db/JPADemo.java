package com.expeditors.backend.db;

import com.expeditors.backend.domain.Adopter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static java.lang.System.out;


@SpringBootTest
public class JPADemo {
    @PersistenceContext
    private EntityManager em;

    /*
    @BeforeEach
    public void beforeEach() {
        String pw = System.getenv("DB_PASSWORD");
        var props = Map.of(
                "jakarta.persistence.jdbc.url","jdbc:postgres://localhost:5433/adoptapp",
                "jakarta.persistence.jdbc.user","larku",
                "jakarta.persistence.jdbc.password",pw,
                "hibernate.dialect","org.hibernate.dialect.PostgresSQLDialect",
                "jakarta.persistence.spi.PersistenceProvider","org.hibernate.jpa.HibernatePersistenceProvider"

        );

        //emf = Persistence.createEntityManagerFactory("LarkUPU_SE", props);
    }
    */

    @Test
    public void dumpAllAdopters() {
        //EntityManager em = emf.createEntityManager();
        TypedQuery<Adopter> query = em.createQuery("select c from Adopter c join fetch c.Pet", Adopter.class);

        List<Adopter> result = query.getResultList();

        out.println("result: " + result.size());
        result.forEach(out::println);

    }

}
