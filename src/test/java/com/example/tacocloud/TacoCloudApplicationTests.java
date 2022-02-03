package com.example.tacocloud;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)  /*Использование Spring runner
является аннотацией JUnit, обеспечивая тестовый runner, помогающий JUnit в выполнении теста.
Думайте об этом как о применении плагина к JUnit для обеспечения пользовательского поведения тестирования.
В этом случае JUnit получает SpringRunner, тестовый модуль, предоставляемый Spring,
который обеспечивает создание контекста приложения Spring, с которым будет выполняться тест.
*/
@SpringBootTest
class TacoCloudApplicationTests {

    @Test
    void contextLoads() {
    }

}
