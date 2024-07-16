package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired private StoreMapper storeMapper;

    public void insertStore(StoreDTO storeDTO){
        storeMapper.insertStore(storeDTO);
    }
    public void updateLike(StoreDTO storeDTO){
        storeMapper.updateLikeCount(storeDTO.get가맹점명());
    }
    public List<StoreDTO> getStoreList(String keyword){
        return storeMapper.selectAllStore(keyword);
    }
    public StoreDTO getStore(StoreDTO storeDTO){
        return storeMapper.selectStore(storeDTO);
    }
}
