package com.ge.predix.labs.data.jpa.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ge.predix.labs.data.jpa.domain.Tag;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@SuppressWarnings("unchecked")
@Transactional
public class TagService {

    public static final String TAGS = "tags";

    @PersistenceContext
    private EntityManager em;

    public Tag createTag(String name, float value, Date timeStamp) {
        Tag tag = new Tag();
        tag.setName(name);
        tag.setValue(value);
        tag.setTStamp(timeStamp);
        em.persist(tag);
        return tag;
    }

    public Collection<Tag> search(String name) {
        String sqlName = ("%" + name + "%").toLowerCase();
        String sql = "select c.* from tag c where (LOWER( c.name ) LIKE :fn)";
        return em.createNativeQuery(sql, Tag.class)
                .setParameter("fn", sqlName)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public List<Tag> getAllTags() {
        return em.createQuery("FROM Tag").getResultList();
    }

    @Cacheable(TAGS)
    @Transactional(readOnly = true)
    public Tag getTagById(Integer id) {
        return em.find(Tag.class, id);
    }

    @CacheEvict(TAGS)
    public void deleteTag(Integer id) {
        Tag tag = getTagById(id);
        em.remove(tag);
    }

    @CachePut(value = TAGS, key = "#id")
    public void updateTag(Integer id, String name, float value, Date date) {
        Tag tag = getTagById(id);
        tag.setTStamp(date);
        tag.setName(name);
        tag.setValue(value);
        em.merge(tag);
    }
}
