package com.test;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {
    void insertStore(StoreDTO storeDTO);
    void updateLikeCount(StoreDTO storeDTO);
    List<StoreDTO> selectAllStore();
    StoreDTO selectStore(StoreDTO storeDTO);
}
