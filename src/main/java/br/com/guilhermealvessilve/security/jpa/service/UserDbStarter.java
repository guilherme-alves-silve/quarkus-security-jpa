package br.com.guilhermealvessilve.security.jpa.service;

import br.com.guilhermealvessilve.security.jpa.data.User;
import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import javax.transaction.Transactional;

@Singleton
public class UserDbStarter {

    private static final Logger LOGGER = Logger.getLogger(UserDbStarter.class);

    @Transactional
    public void onStartup(@Observes StartupEvent event) {
        User.add("user", "user", "user");
        User.add("admin", "admin", "user,admin");
        User.add("john", "john", "user");
        User.add("doe", "doe", "user,admin");
        LOGGER.info("Saved users with success!");
    }
}
