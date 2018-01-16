package me.psychopunch.demo.datarest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static java.util.Arrays.asList;

@RepositoryRestController
@RequestMapping("/api/people")
public class PersonController {

    @Autowired
    PagedResourcesAssembler pagedResourcesAssembler;

    @GetMapping
    public void getPersons(Pageable pageable, PersistentEntityResourceAssembler assembler) {
        Person person = new Person("Juan", "dela Cruz");
        Page persons = new PageImpl(asList(person), pageable, 1);
        pagedResourcesAssembler.toResource(persons, assembler);
    }

}