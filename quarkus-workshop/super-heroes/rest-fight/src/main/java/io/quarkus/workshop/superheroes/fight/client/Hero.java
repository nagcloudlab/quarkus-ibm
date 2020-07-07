package io.quarkus.workshop.superheroes.fight.client;


import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;

@Schema(description="The hero fighting against the villain")
public class Hero {

	public Long id;
	
    @NotNull
    public String name;
    public String otherName;
    @NotNull
    public int level;
    @NotNull
    public String picture;
    public String powers;

    // tag::adocSkip[]
    @Override
    public String toString() {
        return "Hero{" +
            "name='" + name + '\'' +
            ", level=" + level +
            ", picture='" + picture + '\'' +
            ", powers='" + powers + '\'' +
            '}';
    }
    // end::adocSkip[]
}
// end::adocBean[]