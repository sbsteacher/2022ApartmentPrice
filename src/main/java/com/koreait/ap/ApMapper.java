package com.koreait.ap;

import com.koreait.ap.model.ApartmentInfoDto;
import com.koreait.ap.model.ApartmentInfoEntity;
import com.koreait.ap.model.LocationCodeEntity;
import com.koreait.ap.model.SearchDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApMapper {
    int insApartmentInfo(ApartmentInfoEntity entity);
    int insApartmentInfoForeach(ApartmentInfoDto dto);
    List<LocationCodeEntity> selLocationList();
    LocationCodeEntity selLocation(SearchDto dto);
}
