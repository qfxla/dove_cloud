import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author run
 * @since 2021/3/22 20:00
 */
public class RegexTest {

    @Test
    public void test() {
        String regex = "/login|/register|/captcha|/retrieve";
        String content = "http://localsfafa:8080/logi";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        System.out.println(matcher.find());
    }
}
