package kz.nelp.codeWordDemo.service;

import org.springframework.stereotype.Component;

@Component
public class CodewordHelper {
    public final static String START_WORD = "a0";
    public String getWord(String currentCodeword){
        char[] arr = currentCodeword.toCharArray();
        int length = currentCodeword.length();
        String resultWord = "";
        boolean isMaxVal = true;

        for(int i=length-1; i>=0; i--){
            //Если isMaxLetterOrNum == false значит буква или цифра увеличилась на 1
            //Значит мы уже получили итоговое значенией, цикл можно прерывать
            if(!isMaxLetterOrNum(i, arr)){
                isMaxVal = false;
                break;
            }
        }

        //Если isMaxVal == true значит начальное слово состояло только из сочетаний типа z9
        //После прохождения по циклу for слово стало состоять только из сочетаний типа a0
        if(isMaxVal){
            resultWord = String.valueOf(arr) + START_WORD;
        }

        //Если resultWord пуст, то буква или цифра увеличилась на 1 без переполнения
        if(resultWord.isEmpty()){
            resultWord = String.valueOf(arr);
        }

        return resultWord;
    }

    //Проверка на достижение границы буквы или цифры
    //Если граница достигнута, происходит переполнение 9 -> 0, z -> a
    private boolean isMaxLetterOrNum(int index, char[] arr){
        int val = arr[index];
        //ascii a-z == 97-122
        if(val >= 97 && val < 122){
            val++;
            arr[index] = (char) val;
            return false;
        }

        if(val == 122){
            arr[index] = 'a';
            return true;
        }

        //ascii 0-9 == 48-57
        if(val >= 48 && val < 57){
            val++;
            arr[index] = (char)val;
            return false;
        }

        if(val == 57){
            arr[index] = '0';
            return true;
        }

        return true;
    }
}
