package kz.nelp.codeWordDemo.service;

import kz.nelp.codeWordDemo.model.Codeword;
import kz.nelp.codeWordDemo.repository.CodewordRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static kz.nelp.codeWordDemo.service.CodewordService.DESCRIPTION_INVALID_PARAM;
import static kz.nelp.codeWordDemo.service.CodewordService.LENGTH_LIMIT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CodewordServiceTest {
    private CodewordRepository codewordRepository = Mockito.mock(CodewordRepository.class);
    private CodewordHelper codewordHelper = new CodewordHelper();
    private CodewordService codewordService = new CodewordService(codewordRepository, codewordHelper);

    void getCurrentCodeword(){
        Codeword codeword = new Codeword();
        codeword.setId(1);
        codeword.setCodeword("a0a0");
        when(codewordRepository.findAll()).thenReturn(List.of(codeword));
    }

    @Test
    void limitWithAllWord() {
        getCurrentCodeword();
        final String exampleWord = codewordService.generateCodeword("z9z9");
        assertEquals(exampleWord, "a0a0a0");
    }

    @Test
    void limitWithEndLitter() {
        getCurrentCodeword();
        final String exampleWord = codewordService.generateCodeword("a0z9z9");
        assertEquals(exampleWord, "a1a0a0");
    }

    @Test
    void limitWithEndNum() {
        getCurrentCodeword();
        final String exampleWord = codewordService.generateCodeword("a0b9");
        assertEquals(exampleWord, "a0c0");
    }

    //Тест на генерацию нового значения, при входной строке длиной меньше 4 символов
    @Test
    void limitMinLengthWord() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            codewordService.generateCodeword("a0");
        });
        Assertions.assertEquals(DESCRIPTION_INVALID_PARAM, exception.getMessage());
    }

    //Тест на генерацию нового значения, при неверном шаблоне входного параметра
    @Test
    void limitContainsInWord() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            codewordService.generateCodeword("a0z99");
        });
        Assertions.assertEquals(DESCRIPTION_INVALID_PARAM, exception.getMessage());
    }

    //Тест на добавление значения в бд, которое превышает лимит размера колонки codeword_data.codeword
    @Test
    void limitMaxLengthWord(){
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            codewordService.setCodeword("a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0" +
                                        "a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0" +
                                        "a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0" +
                                        "a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0" +
                                        "a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0a0" +
                                        "a0a0a0");
        });
        Assertions.assertEquals(LENGTH_LIMIT, exception.getMessage());
    }
}