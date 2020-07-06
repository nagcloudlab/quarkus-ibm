package org.acme.quarkus.sample;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/api/heroes")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class HeroResource {

	@Inject
	HeroService heroService;

	@Operation(summary = "return a random hero")
	@APIResponse(responseCode = "200")
	@GET
	@Path("/random")
	public Response getRandomHero() {
		Hero hero = heroService.findRandomHero();
		return Response.ok(hero).build();
	}

	@GET
	public Response getAllHeroes() {
		List<Hero> heros = heroService.findAllHeroes();
		return Response.ok(heros).build();
	}

	@GET
	@Path("/{id}")
	public Response getHero(@PathParam(value = "id") long id) {
		Hero hero = heroService.findHeroById(id);
		if (hero != null)
			return Response.ok(hero).build();
		else
			return Response.noContent().build();
	}

	@POST
	public Response postHero(@Valid Hero hero, @Context UriInfo uriInfo) {
		hero = heroService.persistHero(hero);
		UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder().path(Long.toString(hero.id));
		return Response.created(uriBuilder.build()).build();
	}

	@PUT
	public Response updateHero(@Valid Hero hero) {
		hero = heroService.updateHero(hero);
		return Response.ok(hero).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteHero(@PathParam(value = "id") long id) {
		heroService.deleteHero(id);
		return Response.noContent().build();
	}

}