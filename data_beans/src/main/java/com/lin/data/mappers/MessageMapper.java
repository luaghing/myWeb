package com.lin.data.mappers;


import com.lin.data.core.mapper.Mapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Lazy;
import com.lin.data.beans.Message;

import java.util.List;

/**
 * Created  on 2017-10-12.
 *
 * @author 自动生成
 */
@Repository
@Scope
@Lazy
public interface MessageMapper extends Mapper<Message> {

}
