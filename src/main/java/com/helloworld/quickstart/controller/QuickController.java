package com.helloworld.quickstart.controller;

import com.helloworld.quickstart.dto.ItemDto;
import com.helloworld.quickstart.dto.ResponseDto;
import com.helloworld.quickstart.service.QuickService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class QuickController {
    /* restcontroller >>> rest api 와 관련된 컨트롤러임을 명시 */
    /* lsf4j 는 lombok의 한 기능으로 http로 들어온 값들을 로그에 찍어볼 때 사용 */
    /* 서비스 타입의 객체를 찾아 알아서 생성 new quickService 생성할 필요 없음! */
    @Autowired
    private QuickService quickService;

    @GetMapping("/dummy")
    public String dummy() {
        log.info("dummy");
        return "{}";
    }

    @GetMapping("/dummy2")
    public String dummy2() {
        log.info("dummy");
        return "dummy2";
    }

    @GetMapping("/member")
    public String getMember(@RequestParam(value="empNo", defaultValue = "111") String empNo
    , @RequestParam("year") int year) {
        /* 사번 등 http를 통해 받아오려면 requestParam을 사용 */
        /* request param이 여러 개인 경우 class로 따로 빼서 해도 된다. */
        /* 로그 사용 시 info >>> 가장 기본 레벨 */
        /* 로그 사용 시 debug >>> 에러 확인 레벨 */
        log.info("empNo: {}", empNo);
        log.info("year: {}", year);
        return "ok";
    }

    @GetMapping("/company/{id}")
    public String getCompany(@PathVariable("id") String id) {
        /* pathvariable >>> 경로 변수를 표시하기 위해 메서드에 매개변수에 사용 */
        log.info("id: {}", id);
        return "ok";
    }

    /* dto 객체 전체를 request 객체로 전달 */
    @PostMapping("/item")
    public ResponseDto registerItem(@RequestParam ItemDto item) {
        log.info("item: {}", item);

        boolean b = quickService.registerItem(item);
        if (b == true) {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("ok");
            return responseDto;
        }

        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("fail");
        return responseDto;
    }

    @GetMapping("/item")
    public ItemDto getItem(@RequestParam("id") String id) {
        ItemDto res = quickService.getItemById(id);
        return res;
    }




}
