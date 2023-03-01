package kz.nelp.codeWordDemo.service;

import kz.nelp.codeWordDemo.model.Codeword;
import kz.nelp.codeWordDemo.repository.CodewordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class CodewordService {
    public final static String DESCRIPTION_INVALID_PARAM = "The length of the param must be" +
            " at least 4 characters" +
            " and contain the letter a-z and the number 0-9. Example: a0b9";

    public final static String LENGTH_LIMIT = "param length limit exceeded";

    public final static String NULL_RECORD = "the record is not exist";
    private final CodewordRepository codewordRepository;
    private final CodewordHelper codewordHelper;

    public Codeword getCurrentCodeword(){
        List<Codeword> codewords = codewordRepository.findAll();
        if(codewords.get(0) == null) throw new IllegalArgumentException(NULL_RECORD);
        return codewords.get(0);
    }

    @Transactional
    public void setCodeword(String codeword){
        if(codeword.length() > 255) throw new IllegalArgumentException(LENGTH_LIMIT);
        Codeword codewordData = getCurrentCodeword();
        codewordData.setCodeword(codeword);
        codewordRepository.save(codewordData);
    }

    public String generateCodeword(String word){
        String regex = "^(?=.{4,})([a-z][0-9])+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(word);
        if(!matcher.find()) throw new IllegalArgumentException(DESCRIPTION_INVALID_PARAM);
        String resultWord = codewordHelper.getWord(word);
        setCodeword(resultWord);
        return resultWord;
    }

    public String getNextCodeword(){
        Codeword codeword = getCurrentCodeword();
        if(codeword.getCodeword() == null || codeword.getCodeword().isEmpty()) throw new IllegalArgumentException(DESCRIPTION_INVALID_PARAM);
        String resultWord = codewordHelper.getWord(codeword.getCodeword());
        setCodeword(resultWord);
        return resultWord;
    }
}
