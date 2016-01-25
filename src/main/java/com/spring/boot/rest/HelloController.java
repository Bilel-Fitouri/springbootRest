package com.spring.boot.rest; /**
 * Created by bfitouri on 22/01/16.
 */
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class HelloController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/hello", method=RequestMethod.GET)
    public @ResponseBody Greeting sayHello(@RequestParam(value="name", required=false, defaultValue="Stranger") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public ResponseEntity getAuthorization() {
        final String redirectAuthorizeUrl = "http://localhost:8080/hello";
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("location", redirectAuthorizeUrl);
        return new ResponseEntity(httpHeaders, HttpStatus.SEE_OTHER);
    }

}