package com.koreait.ap;

import com.koreait.ap.model.ApartmentInfoEntity;
import com.koreait.ap.model.LocationCodeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApMapper {
    int insApartmentInfo(ApartmentInfoEntity entity);
    List<LocationCodeEntity> selLocationList();
}
