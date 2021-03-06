## jsonrpc

### high-performance RPC framework. 


jsonrpc is a high-performance, Java based open source RPC framework. 

#### example

**_Maven dependency_**

```
<dependency>
    <groupId>com.github.xincao9</groupId>
    <artifactId>jsonrpc-spring-boot-starter</artifactId>
    <version>1.2.1</version>
</dependency>
```

**_object_**

```

public class Say {

    private Integer id;
    private String body;

    public Say(Integer id, String body) {
        this.id = id;
        this.body = body;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this, SerializerFeature.DisableCircularReferenceDetect);
    }
}
```

**_interface_**

```
public interface SayService {

    Say perform(Say say);
}
```

**_service_**

```
public class SayServiceImpl implements SayService {

    @Override
    public Say perform(Say say) {
        return say;
    }

}
```

**_service provider_**

```
@SpringBootApplication
@EnableJsonRPC(server = true)
public class ApplicationProvider {

    @Autowired
    private JsonRPCServer jsonRPCServer;

    @Bean
    public SayService sayService () {
        SayService sayService = new SayServiceImpl();
        jsonRPCServer.register(sayService);
        return sayService;
    }

    public static void main(String... args) {
        SpringApplication.run(ApplicationProvider.class, args);
    }
}
```

**_service consumer_**

```
@SpringBootApplication
@EnableJsonRPC(client = true)
public class ApplicationConsumer {

    @Autowired
    private JsonRPCClient jsonRPCClient;
    @Autowired
    private SayService sayService;

    @Bean
    public SayService sayService() {
        return jsonRPCClient.proxy(SayService.class);
    }

    public static void main(String... args) {
        SpringApplication.run(ApplicationConsumer.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return (String... args) -> {
            for (int no = 0; no < 100; no++) {
                String value = RandomStringUtils.randomAscii(128);
                Say say = new Say(no, value);
                System.out.println(sayService.perform(say));
            }
        };
    }

}
```
**_application.properties_**

```
## consumer
jsonrpc.client.serverList=localhost:12306
jsonrpc.client.connectionTimeoutMS=5000
jsonrpc.client.invokeTimeoutMS=1000

## provider
jsonrpc.server.port=12306
jsonrpc.server.ioThreadBoss=1
jsonrpc.server.ioThreadWorker=4
```

**_benchmark_**

```
1.Get the pressure measurement component
wget https://oss.sonatype.org/service/local/repositories/releases/content/com/github/xincao9/jsonrpc-benchmark/1.2.1/jsonrpc-benchmark-1.2.1.jar
2.Start service provider
java -Drole=provider -jar jsonrpc-benchmark-1.2.jar
3.Start service consumer
java -Drole=consumer -jar jsonrpc-benchmark-1.2.jar
4.Service providers that simulate IO-intensive applications perform stress tests (blocking time is pseudo-random at 0 to 50 ms)
wrk -c 128 -t 10 -d 30s 'http://localhost:8080/sleep'
5.Service providers that simulate computationally intensive applications perform stress tests (handling pseudo-random, Fibonacci numbers between 0 and 16)
wrk -c 128 -t 10 -d 30s 'http://localhost:8080/fibonacci_sequence'
```

**_tips_**

* Welcome to see detailed examples [examples](https://github.com/xincao9/jsonrpc/tree/master/jsonrpc-sample)
* Not only supports the boot mode of springboot
* Native boot mode, the default configuration file is named config.properties
* @EnableJsonRPC(server = true, client = true) Indicates that the service is a consumer even if the provider

#### Contact

* [issues](https://github.com/xincao9/jsonrpc/issues)
* https://issues.sonatype.org/browse/OSSRH-47112
* xincao9@gmail.com
