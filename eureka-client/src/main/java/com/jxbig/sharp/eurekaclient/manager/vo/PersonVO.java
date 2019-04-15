package com.jxbig.sharp.eurekaclient.manager.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@ToString
@Document(collection = "back_person")
public class PersonVO implements Serializable {

    @Id
    @Field("_id")
    @Getter @Setter
    private String id;

    @Field("group_id")
    @Getter @Setter
    private String groupId;

    @Field("nick_name")
    @Getter @Setter
    private String nickName;

    @Field("sex")
    @Getter @Setter
    private String sex;

}
