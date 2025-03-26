package com.inflearn.toby;

import com.inflearn.toby.api.ApiTemplate;
import com.inflearn.toby.api.ErApiExRateExtractor;
import com.inflearn.toby.api.SimpleApiExecutor;
import com.inflearn.toby.exrate.provider.WebApiExRateProvider;
import com.inflearn.toby.payment.ExRateProvider;
import com.inflearn.toby.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class PaymentConfig {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider(), clock());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider(apiTemplate());
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    public ApiTemplate apiTemplate() {
        // 직접 구현체를 지정할 수도 있다.
//        return new ApiTemplate(new SimpleApiExecutor(), new ErApiExRateExtractor());
        return new ApiTemplate();
    }
}
