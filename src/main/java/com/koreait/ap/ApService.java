package com.koreait.ap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.koreait.ap.model.ApartmentInfoEntity;
import com.koreait.ap.model.LocationCodeEntity;
import com.koreait.ap.model.SearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Service
public class ApService {
    @Autowired private ApMapper mapper;

    public List<LocationCodeEntity> selLocationList() {
        return mapper.selLocationList();
    }

    public void getData(SearchDto dto) {
        String lawdCd = dto.getExcd();
        String dealYm = String.format("%s%02d", dto.getYear(), dto.getMonth());
        System.out.println("dealym : " + dealYm);

        String serviceKey = "Y2UOCkD8Ilv2gViPGV33ddNTTQfRi92i8mRzUeQX+NgSiNTO3gp9hJZX4J6u8uXucMM6RdRBoGxMn6XHfsEzNA==";
        String url = "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("LAWD_CD", lawdCd)
                .queryParam("DEAL_YMD", dealYm)
                .queryParam("serviceKey", serviceKey)
                .queryParam("numOfRows", 5000)
                .build(false);

        RestTemplate rest = new RestTemplate();
        rest.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        ResponseEntity<String> responseEntity = rest.exchange(builder.toUriString(), HttpMethod.GET, null, String.class);
        String result = responseEntity.getBody();
        //System.out.println("result : " + result);

        ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = null;
        ApartmentInfoEntity[] list = null;
        List<ApartmentInfoEntity> list2 = null;
        try {
            jsonNode = om.readTree(result);
            //list = om.treeToValue(jsonNode.path("response").path("body").path("items").path("item"), ApartmentInfoEntity[].class);
            list2 = om.convertValue(jsonNode.path("response").path("body").path("items").path("item"), new TypeReference<List<ApartmentInfoEntity>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(ApartmentInfoEntity item : list2) {
            System.out.println(item);
        }
    }
}
