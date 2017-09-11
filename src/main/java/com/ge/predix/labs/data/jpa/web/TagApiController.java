package com.ge.predix.labs.data.jpa.web;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ge.predix.labs.data.jpa.domain.Tag;
import com.ge.predix.labs.data.jpa.service.TagService;

@ComponentScan
@RestController
public class TagApiController {

    @Autowired  private TagService tagService;

    public static final String TAGS = "/tags";
    public static final String SEARCH = "/search";
    public static final String GET_TAG_BY_ID = TAGS + "/{id}";

    @RequestMapping(value = SEARCH + TAGS, method = RequestMethod.GET)
    public Collection<Tag> search(@RequestParam("q") String query) throws Exception {
        Collection<Tag> tags = tagService.search(query);
        return tags;
    }

    @RequestMapping(value = GET_TAG_BY_ID, method = RequestMethod.GET)
    public Tag tagById(@PathVariable  Integer id) {
        return this.tagService.getTagById(id);
    }

    @RequestMapping(value = TAGS, method = RequestMethod.GET)
    public Collection<Tag> tags() throws Exception {
        Collection<Tag> tags = tagService.getAllTags();
        return tags;
    }

    @RequestMapping(value = TAGS, method = RequestMethod.POST)
    public Integer addTag(@RequestParam String name, @RequestParam float value) {
        return tagService.createTag(name, value,  new Date()).getId();
    }

    @RequestMapping(value = GET_TAG_BY_ID, method = RequestMethod.PUT)
    public Integer updateTag(@PathVariable  Integer id, @RequestBody Tag tag) {
        tagService.updateTag(id, tag.getName(), tag.getValue(), tag.getTStamp());
        return id;
    }
}