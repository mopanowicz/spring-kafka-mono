package com.example.monitor.controller;

import com.example.monitor.document.CounterDocument;
import com.example.monitor.service.CounterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
