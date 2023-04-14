package com.example.cafe.kakao;


import com.example.cafe.kakao.dto.LocalSearchReqDto;
import com.example.cafe.kakao.dto.LocalSearchResDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
public class KakaoClient {

    @Value("${kakao.client.key}")
    private String restApiKey;

    @Value("${kakao.client.local}")
    private String kakaoLocalSearchUrl;

    //지역 검색
    public LocalSearchResDto localSearch(LocalSearchReqDto localSearchReqDto) {
        var uri = UriComponentsBuilder.fromUriString(kakaoLocalSearchUrl)
                .queryParams(localSearchReqDto.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        log.info("uri={}", uri.toString());

        var headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + restApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = RequestEntity.get(uri)
                .headers(headers).build();

        var result = new RestTemplate().exchange(httpEntity,
                new ParameterizedTypeReference<LocalSearchResDto>(){});

//        log.info("response header = {}", result.getHeaders());
//        log.info("response status = {}", result.getStatusCode());
//        log.info("response body = {}", result.getBody());

        return result.getBody();
    }
}
