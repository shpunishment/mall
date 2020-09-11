package com.shpun.mall.front.config;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/11 14:50
 */
@Configuration
public class WebConfig {

    @Bean
    public HttpMessageConverters fastJsonConverters() {
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                // 显示为null的成员
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteBigDecimalAsPlain
        );
        // 时间格式
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        // BigDecimal 格式
        fastJsonConfig.setSerializeFilters((ValueFilter) (object, name, value) -> {
            if(value instanceof BigDecimal) {
                return ((BigDecimal)value).toPlainString();
            }
            return value;
        });

        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.ALL);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);

        // 支持text 转string
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        return new HttpMessageConverters(fastJsonHttpMessageConverter, stringHttpMessageConverter);
    }

}
