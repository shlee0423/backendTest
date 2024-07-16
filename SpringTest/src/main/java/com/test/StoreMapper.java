package com.test;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreMapper {
    void insertStore(StoreDTO storeDTO);
    void updateLikeCount(@Param("가맹점명") String 가맹점명);
    List<StoreDTO> selectAllStore(
            @Param("keyword") String keyword
    );
    StoreDTO selectStore(StoreDTO storeDTO);
}
