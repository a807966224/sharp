package com.jxbig.sharp.eurekaclient.manager.service;

import com.jxbig.sharp.eurekaclient.common.config.PersonEvent;
import com.jxbig.sharp.eurekaclient.manager.vo.PersonVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PersonService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private ApplicationContext context;

    public List<PersonVO> queryPersonByPage(String groupId, Integer offset, Integer limit) {
        Query query = new Query();
        if (StringUtils.isEmpty(groupId)) {
            return null;
        }
        query.addCriteria(Criteria.where("group_id").is(groupId));
        query.with(new Sort(Sort.Direction.DESC, "_id"));
        Integer skip = (offset - 1) * limit;
        query.skip(skip);
        query.limit(limit);
        List<PersonVO> list = mongoTemplate.find(query, PersonVO.class);
        return list;
    }

    public void insertPerson(PersonVO vo) {
        // 获取组人数，达到4人即成组，不在添加，并且开始组内人员游戏
        Query query = new Query();
        query.addCriteria(Criteria.where("group_id").is(vo.getGroupId()));
        long count = mongoTemplate.count(query, PersonVO.class);
        if (count == 4) {
            System.out.println("人数已经足够了，不进行操作");
        }
        if (count < 4) {
            System.out.println(vo);
            vo.setId(UUID.randomUUID().toString().replace("-",""));
            mongoTemplate.insert(vo);

            count = mongoTemplate.count(query, PersonVO.class);
            if (count == 4) {
                context.publishEvent(new PersonEvent("人数已足，开始游戏"));
            }
        }
        System.out.println("组内人数已满，不进行操作");

    }

    public PersonVO findPersonById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        PersonVO personVO = mongoTemplate.findOne(query, PersonVO.class);
        return personVO;
    }

    public void deletePersonById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, PersonVO.class);
    }

}
