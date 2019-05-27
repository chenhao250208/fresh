package com.store.fresh.mapper;

import com.store.fresh.entity.PicturePath;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PicturePathMapper {
    int insert(PicturePath record);

    List<PicturePath> selectAll();
}