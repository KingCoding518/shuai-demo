import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: KingCoding
 * @Date: 2025/5/20
 * @Description:
 */

public class TestShuai {

    @Test
    public void test() {
        List<String> list = Arrays.asList("a", "b", "c");

        // list.forEach(System.out::println);
        list.forEach(k -> {
            System.out.println(k);
        });
    }

}
