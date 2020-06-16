package br.com.guilhermealvessilve.security.jpa.resource;

import br.com.guilhermealvessilve.security.jpa.data.User;
import br.com.guilhermealvessilve.security.jpa.dto.UserDTO;
import br.com.guilhermealvessilve.security.jpa.service.DateGeneratorService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@Path("/jpa")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class JPAUserResource {

    private final DateGeneratorService generatorService;

    @Inject
    public JPAUserResource(final DateGeneratorService generatorService) {
        this.generatorService = generatorService;
    }

    @GET
    @Path("/user")
    @RolesAllowed({"user", "admin"})
    public UserDTO getUserInfo(@Context SecurityContext context) {
        final var user = User.findByUsername(context.getUserPrincipal().getName());
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                generatorService.generateDate()
        );
    }

    @GET
    @Path("/admin")
    @RolesAllowed("admin")
    public UserDTO getAdminInfo(@Context SecurityContext context) {
        final var user = User.findByUsername(context.getUserPrincipal().getName());
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                generatorService.generateDate()
        );
    }

    @GET
    @PermitAll
    @Path("/public")
    public UserDTO getInfo() {
        return new UserDTO(
                0L,
                "Public",
                "00/00/0000"
        );
    }
}
