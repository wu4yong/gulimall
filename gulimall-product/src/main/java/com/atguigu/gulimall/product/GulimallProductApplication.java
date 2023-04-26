package com.atguigu.gulimall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 1、整合MyBatis-Plus
 *      1）、导入依赖
 *      <dependency>
 *             <groupId>com.baomidou</groupId>
 *             <artifactId>mybatis-plus-boot-starter</artifactId>
 *             <version>3.2.0</version>
 *      </dependency>
 *      2）、配置
 *          1、配置数据源；
 *              1）、导入数据库的驱动。https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-versions.html
 *              2）、在application.yml配置数据源相关信息
 *          2、配置MyBatis-Plus；
 *              1）、使用@MapperScan
 *              2）、告诉MyBatis-Plus，sql映射文件位置
 *
 * 2、逻辑删除
 *  1）、配置全局的逻辑删除规则（省略）
 *  2）、配置逻辑删除的组件Bean（省略）
 *  3）、给Bean加上逻辑删除注解@TableLogic
 *
 * 3、JSR303
 *   1）、给Bean添加校验注解:javax.validation.constraints，并定义自己的message提示
 *   2)、开启校验功能@Valid
 *      效果：校验错误以后会有默认的响应；
 *   3）、给校验的bean后紧跟一个BindingResult，就可以获取到校验的结果
 *   4）、分组校验（多场景的复杂校验）
 *         1)、	@NotBlank(message = "品牌名必须提交",groups = {AddGroup.class,UpdateGroup.class})
 *          给校验注解标注什么情况需要进行校验
 *         2）、@Validated({AddGroup.class})
 *         3)、默认没有指定分组的校验注解@NotBlank，在分组校验情况@Validated({AddGroup.class})下不生效，只会在@Validated生效；
 *
 *   5）、自定义校验
 *      1）、编写一个自定义的校验注解
 *      2）、编写一个自定义的校验器 ConstraintValidator
 *      3）、关联自定义的校验器和自定义的校验注解
 *      @Documented
 * @Constraint(validatedBy = { ListValueConstraintValidator.class【可以指定多个不同的校验器，适配不同类型的校验】 })
 * @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
 * @Retention(RUNTIME)
 * public @interface ListValue {
 *
 * 4、统一的异常处理
 * @ControllerAdvice
 *  1）、编写异常处理类，使用@ControllerAdvice。
 *  2）、使用@ExceptionHandler标注方法可以处理的异常。
 *
 *  5、模板引擎
 *  1）、thymeleaf-starter：关闭缓存
 *  2）、静态资源都放在static文件夹下就可以按照路径直接访问
 *  3）、页面放在template下，直接访问
 *      springboot，访问项目的时候，默认会找index
 *          源码：WebMvcAutoConfiguration
 *              ---ResourceProperties
 *
 *  4）、页面修改不重启服务器实时更新
 *      1）、引入dev-tools
 *      2）、修改玩页面 controller  shift f9 重新自动编译下页面
 *  6、整合redis
 *  1）、引入data-redis-starter
 *  2）、简单配置redis的host等信息
 *  3）、使用springboot自动配置好的stringRedisTemplate操作
 *
 *  7、整合Redisson作为分布式锁功能框架
 *  1）、引入依赖
 *         <dependency>
 *             <groupId>org.redisson</groupId>
 *             <artifactId>redisson</artifactId>
 *             <version>3.12.0</version>
 *         </dependency>
 *  2）、配置Redisson 参考：https://github.com/redisson/redisson/wiki/2.-Configuration
 *
 * 8、整合SpringCache简化缓存开发
 * 1）、引入依赖
 *      spring-boot-starter-cache
 *      spring-boot-starter-data-redis
 *  2）、写配置
 *      （1）自动配置了哪些
 *      CacheAutoConfiguration会导入RedisCacheConfiguration
 *          自动配好了缓存管理器：RedisCacheManager
 *      （2）、配置使用Redis使用缓存
 *          spring.cache.type=redis
 *  3）、测试使用缓存
 *      @Cacheable: Triggers cache population.触发将数据保存到缓存的操作
 *      @CacheEvict: Triggers cache eviction.触发将数据保从缓存删除的操作
 *      @CachePut: Updates the cache without interfering with the method execution.不影响方法执行更新缓存
 *      @Caching: Regroups multiple cache operations to be applied on a method.组合以上多个操作
 *      @CacheConfig: Shares some common cache-related settings at class-level.在类级别共享缓存配置
 *      1）、开启缓存 @EnableCaching
 *      2）、只需要使用注解就能完成缓存操作
 *
 *      1、每一个需要缓存的数据我们都来指定要放到那个名字的缓存。【缓存的分区(按照业务类型分)】
 *      *  2、@Cacheable(value = {"category"}
 *      *      1）代表当前方法的结果需要缓存，如果缓存中有，方法都不用调用
 *      *      2）如果缓存中没有，会调用方法。最后将方法的结果放入缓存
 *      *  3、默认行为
 *      *     1）、如果缓存中有，方法不再调用
 *      *     2）、key是默认生成的:缓存的名字::SimpleKey::[](自动生成key值)
 *      *     3）、缓存的value值，默认使用jdk序列化机制，将序列化的数据存到redis中
 *      *     4）、默认时间是 -1：
 *      *    自定义操作：
 *      *      1）key的生成 指定生成缓存的key：key属性指定，接收一个Spel
 *      *      2）指定缓存的数据的存活时间: 配置文件中修改存活时间 例：spring.cache.redis.time-to-live=3600000（毫秒为单位）
 *      *      3）将数据保存为json格式
 *
 *
 *
 *
 *
 */

//@EnableCaching      //开启缓存功能 移入配置
@EnableFeignClients(basePackages = "com.atguigu.gulimall.product.feign")
@MapperScan("com.atguigu.gulimall.product.dao")//添加mybatis文件扫码
@EnableDiscoveryClient
@SpringBootApplication
public class GulimallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallProductApplication.class, args);
    }

}
