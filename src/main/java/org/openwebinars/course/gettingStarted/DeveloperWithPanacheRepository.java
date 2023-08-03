package org.openwebinars.course.gettingStarted;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

// TODO: Look into if it would implement DeveloperWithPanache instead of Developer
@ApplicationScoped
public class DeveloperWithPanacheRepository implements PanacheRepository<Developer> {

    // Recommended to make it transactional that it's a creation
    @Transactional
    public Developer create(Developer developer) {
        persist(developer);
        return developer;
    }

    public Developer findByName(String name) {
        return find("name", name).firstResult();
    }
}
