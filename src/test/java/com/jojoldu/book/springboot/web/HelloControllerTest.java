package com.jojoldu.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 1. 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킵니다.
// 2. 여기서는 SpringRunner라는 스프링 실행자를 사용합니다.
// 3. 즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 합니다.
@RunWith(SpringRunner.class)
// 1. 여러 스프링 테스트 어노테이션 중 Web(Spring MVC)에 집중할 수 있는 어노테이션 입니다.
// 2. 단, @Service, @Component, @Repository 등은 사용할 수 없습니다.
// 3. 여기서는 컨트롤러만 사용하기 때문에 선언합니다.
@WebMvcTest(controllers = HelloController.class, secure = false)
public class HelloControllerTest {

    @Autowired
    // 웹 API를  테스트할 때 사용합니다.
    // 스프링 MVC 테스트의 시작점입니다.
    // 이 클래스를 통해 HTTP GET, POST 등에 대한 APi 테스트를 할 수 있습니다.
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMVC를 통해 /hello 주소로 HTTP GET 요청을 합니다.
                .andExpect(status().isOk()) // 여기선 OK 즉, 200인지 아닌지를 검증합니다.
                .andExpect(content().string(hello)); // Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증합니다.
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                        get("/hello/dto")
                                .param("name", name)
                                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
                // JSON 응답값을 필드별로 검증할 수 있는 메소드입니다.
                // $를 기준으로 필드명을 명시합니다.
                // 여기서는 name과 amount를 검증하니 $.name, $.amount로 검증합니다.
    }
}
