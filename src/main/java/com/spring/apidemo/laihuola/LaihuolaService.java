package com.spring.apidemo.laihuola;

import com.spring.apidemo.laihuola.data.BaseR;
import com.spring.apidemo.laihuola.body.LoginBody;
import com.spring.apidemo.laihuola.data.DataR;
import com.spring.apidemo.laihuola.resp.GoodsResp;
import com.spring.apidemo.laihuola.resp.LoginResp;
import com.spring.apidemo.mzitu.PushRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;

@Service
public class LaihuolaService {

    private static final Logger log = LoggerFactory.getLogger(PushRepository.class);

    private static final String baseUrl = "http://api.sowl.cn/api";

    private WebClient webClient =
            WebClient.builder()
                    .baseUrl(baseUrl)
                    .build();

    private String nonce() {
        int i = new Random().nextInt(9000) + 1000;
        return String.valueOf(i);
    }

    private String timestamp() {
        long l = System.currentTimeMillis();
        return String.valueOf(l / 1000);
    }

    private String signature(String timestamp, String nonce) {
        String token = "3649620B82877575A0B7D67DEBA008A0";
        String s = token + timestamp + nonce;
        return DigestUtils.md5DigestAsHex(s.getBytes());
    }

    public Mono<BaseR<LoginResp>> login(String phone, String captcha) {
        String nonce = nonce();
        String timestamp = timestamp();
        String signature = signature(timestamp, nonce);
        LoginBody body = new LoginBody(phone, captcha);
        return webClient.post()
                .uri("/login")
                .body(Mono.just(body), LoginBody.class)
                .header("Signature", signature)
                .header("Timestamp", timestamp)
                .header("Nonce", nonce)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<BaseR<LoginResp>>() {
                });
    }

    public Mono<BaseR> captcha(String phone) {
        String nonce = nonce();
        String timestamp = timestamp();
        String signature = signature(timestamp, nonce);
        return webClient.get()
                .uri("/login/captchas?phoneNumber={phone}", phone)
                .header("Signature", signature)
                .header("Timestamp", timestamp)
                .header("Nonce", nonce)
                .retrieve()
                .bodyToMono(BaseR.class);
    }

    public Mono<BaseR<DataR<List<GoodsResp>>>>
    goods(String token, String place1, String place2, int container, int page, int count) {
        String nonce = nonce();
        String timestamp = timestamp();
        String signature = signature(timestamp, nonce);
        String authorization = "Bearer " + token;
        String uri = "/v2/Order/GetOrdersList?startingPlace={place1}&destinationPlace={place2}&getcontainer={container}&skip={page}&count={count}";
        return webClient.get()
                .uri(uri, place1, place2, container, page, count)
                .header("Authorization", authorization)
                .header("Signature", signature)
                .header("Timestamp", timestamp)
                .header("Nonce", nonce)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<BaseR<DataR<List<GoodsResp>>>>() {
                });
    }

}
