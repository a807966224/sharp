package com.jxbig.sharp.eurekaclient.manager.service;

import com.jxbig.sharp.eurekaclient.manager.vo.GroupVO;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void insertGroup(GroupVO group){
        System.out.println(group);
        mongoTemplate.save(group);

    }

    public void deleteById(String id){
        Query query = new Query(Criteria.where("_id").is(id));
        DeleteResult deleteResult = mongoTemplate.remove(query, GroupVO.class);
        System.out.println(deleteResult);
    }

    public GroupVO findGroupById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        GroupVO vo = mongoTemplate.findOne(query, GroupVO.class);
        return vo;
    }

    public void updateGroupById(GroupVO group){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(group.getId()));
        Update update = new Update();
        if (!StringUtils.isEmpty(group.getName())) {
            update.set("name", group.getName());
        }
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, GroupVO.class);
        System.out.println(updateResult);
    }

    public List<GroupVO> queryGroupByPage(String name, Integer offset, Integer limit) {
        Query query = new Query();
        if (!StringUtils.isEmpty(name)) {
            query.addCriteria(Criteria.where("name").is(name));
        }
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "_id")));
        int skip = (offset - 1) * limit;
        query.skip(skip);
        query.limit(limit);

        List<GroupVO> list = mongoTemplate.find(query, GroupVO.class);
        return list;

    }

}
