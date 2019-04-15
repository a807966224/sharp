package com.jxbig.sharp.eurekaclient.manager.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Document(collection = "back_group")
@Component
@ToString
public class GroupVO implements Serializable{

    @Field("_id")
    @Getter @Setter
    private String id;

    @Field("name")
    @Getter @Setter
    private String name;

    @Field("num")
    @Getter @Setter
    private Integer num;

}
