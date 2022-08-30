package com.ppanticona.fabio.config.dbmigrations;

import com.ppanticona.fabio.config.Constants;
import com.ppanticona.fabio.domain.Authority;
import com.ppanticona.fabio.domain.User;
import com.ppanticona.fabio.security.AuthoritiesConstants;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import java.time.Instant;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Creates the initial database setup.
 */
@ChangeUnit(id = "users-initialization", order = "001", runAlways = true)
public class InitialSetupMigration {

    private final MongoTemplate template;

    public InitialSetupMigration(MongoTemplate template) {
        this.template = template;
    }

    @Execution
    public void changeSet() {
        Authority userAuthority = createUserAuthority();
        userAuthority = template.save(userAuthority);
        Authority adminAuthority = createAdminAuthority();
        adminAuthority = template.save(adminAuthority);

        Authority cajeroAuthority = createCajeroAuthority();
        cajeroAuthority = template.save(cajeroAuthority);

        Authority contadorAuthority = createContadorAuthority();
        contadorAuthority = template.save(contadorAuthority);
        Authority inventariadorAuthority = createInventariadorAuthority();
        inventariadorAuthority = template.save(inventariadorAuthority);
        Authority gerenteAuthority = createGerenteAuthority();
        gerenteAuthority = template.save(gerenteAuthority);

        addUsers(userAuthority, adminAuthority);
    }

    @RollbackExecution
    public void rollback() {}

    private Authority createAuthority(String authority) {
        Authority adminAuthority = new Authority();
        adminAuthority.setName(authority);
        return adminAuthority;
    }

    private Authority createAdminAuthority() {
        Authority adminAuthority = createAuthority(AuthoritiesConstants.ADMIN);
        return adminAuthority;
    }

    private Authority createUserAuthority() {
        Authority userAuthority = createAuthority(AuthoritiesConstants.USER);
        return userAuthority;
    }

    private Authority createCajeroAuthority() {
        Authority cajeroAuthority = createAuthority(AuthoritiesConstants.CAJERO);
        return cajeroAuthority;
    }

    private Authority createContadorAuthority() {
        Authority contadorAuthority = createAuthority(AuthoritiesConstants.CONTADOR);
        return contadorAuthority;
    }

    private Authority createInventariadorAuthority() {
        Authority inventariadorAuthority = createAuthority(AuthoritiesConstants.INVENTARIADOR);
        return inventariadorAuthority;
    }

    private Authority createGerenteAuthority() {
        Authority gerenteAuthority = createAuthority(AuthoritiesConstants.GERENTE);
        return gerenteAuthority;
    }

    private void addUsers(Authority userAuthority, Authority adminAuthority) {
        User user = createUser(userAuthority);
        template.save(user);
        User admin = createAdmin(adminAuthority, userAuthority);
        template.save(admin);
    }

    private User createUser(Authority userAuthority) {
        User userUser = new User();
        userUser.setId("user-2");
        userUser.setLogin("user");
        userUser.setPassword("$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K");
        userUser.setFirstName("");
        userUser.setLastName("User");
        userUser.setEmail("user@localhost");
        userUser.setActivated(true);
        userUser.setLangKey("en");
        userUser.setCreatedBy(Constants.SYSTEM);
        userUser.setCreatedDate(Instant.now());
        userUser.getAuthorities().add(userAuthority);
        return userUser;
    }

    private User createAdmin(Authority adminAuthority, Authority userAuthority) {
        User adminUser = new User();
        adminUser.setId("user-1");
        adminUser.setLogin("admin");
        adminUser.setPassword("$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC");
        adminUser.setFirstName("admin");
        adminUser.setLastName("Administrator");
        adminUser.setEmail("admin@localhost");
        adminUser.setActivated(true);
        adminUser.setLangKey("en");
        adminUser.setCreatedBy(Constants.SYSTEM);
        adminUser.setCreatedDate(Instant.now());
        adminUser.getAuthorities().add(adminAuthority);
        adminUser.getAuthorities().add(userAuthority);
        return adminUser;
    }
}
