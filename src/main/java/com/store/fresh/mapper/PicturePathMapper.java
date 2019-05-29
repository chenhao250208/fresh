package com.store.fresh.mapper;

import com.store.fresh.entity.PicturePathExample;
import com.store.fresh.entity.PicturePathKey;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PicturePathMapper {
    long countByExample(PicturePathExample example);

    int deleteByExample(PicturePathExample example);

    int deleteByPrimaryKey(PicturePathKey key);

    int insert(PicturePathKey record);

    int insertSelective(PicturePathKey record);

    List<PicturePathKey> selectByExample(PicturePathExample example);

    int updateByExampleSelective(@Param("record") PicturePathKey record, @Param("example") PicturePathExample example);

    int updateByExample(@Param("record") PicturePathKey record, @Param("example") PicturePathExample example);
}