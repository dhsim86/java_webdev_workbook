package Lesson05;

import java.util.ListResourceBundle;

/**
 * Created by Dongho on 2017. 3. 11..
 */
public class MyResourceBundle extends ListResourceBundle {

    public Object[][] getContents() {

        return new Object[][] {
            {"OK", "확인"},
            {"Cancel", "취소"},
            {"Reset", "재설정"},
            {"Submit", "제출"}
        };
    }
}
