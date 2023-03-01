package kz.nelp.codeWordDemo.controller;

import kz.nelp.codeWordDemo.model.Codeword;
import kz.nelp.codeWordDemo.service.CodewordService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/codeword")
@AllArgsConstructor
@Slf4j
public class CodewordController {
    private final CodewordService codewordService;

    //Генерация уникального кода табло через входной параметр
    @PutMapping(value = "/generate", consumes = "application/json")
    public String generateCodeword(@RequestBody Codeword codeword){
        return codewordService.generateCodeword(codeword.getCodeword());
    }

    //Получение следующего значения
    @GetMapping("/next")
    public String getNextCodeword(){
        return codewordService.getNextCodeword();
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handle(IllegalArgumentException e){
        log.error(e.getMessage());
        return e.getMessage();
    }
}
