package com.inflearn.toby;

import com.inflearn.toby.data.JdbcOrderRepository;
import com.inflearn.toby.order.OrderRepository;
import com.inflearn.toby.order.OrderService;
import com.inflearn.toby.order.OrderServiceImpl;
import com.inflearn.toby.order.OrderServiceTxProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {

    @Bean
    public OrderService orderService(PlatformTransactionManager transactionManager, OrderRepository orderRepository) {
        return new OrderServiceTxProxy(new OrderServiceImpl(orderRepository), transactionManager);
    }

    @Bean
    public OrderRepository orderRepository(DataSource dataSource) {
        return new JdbcOrderRepository(dataSource);
    }

}
