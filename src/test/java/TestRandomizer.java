import org.apache.commons.lang3.RandomStringUtils;

public class TestRandomizer {

    public static String Email = RandomStringUtils.randomAlphanumeric(10) + "@gmail.com";
    public static String correctPassword = RandomStringUtils.randomAlphanumeric(6, 12);
    public static String incorrectPassword = RandomStringUtils.randomAlphanumeric(0, 6);
    public static String Name = RandomStringUtils.randomAlphabetic(5, 10);
}