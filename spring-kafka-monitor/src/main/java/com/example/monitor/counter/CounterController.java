package com.example.monitor.counter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CounterController {

    private final CounterService counterService;

    @GetMapping("/get-all")
    @ResponseBody
    List<CounterDocument> getAll() {
        return counterService.getAll();
    }

    @GetMapping("/reset-all")
    @ResponseBody
    List<CounterDocument>  resetAll() {
        counterService.reset("event");
        counterService.reset("eventConfirmation");
        return counterService.getAll();
    }
}
