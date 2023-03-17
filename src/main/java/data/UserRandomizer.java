package data;

import org.apache.commons.lang3.RandomStringUtils;
import pojo.User;

public class UserRandomizer {

    public static User getNewRandomUser() {
        return new User(RandomStringUtils.randomAlphanumeric(6),
                RandomStringUtils.randomAlphanumeric(8) + "@burger.ru",
                RandomStringUtils.randomAlphanumeric(6)); // граничное значение для позитивного теста
    }

    public static User getNewRandomUserPassFive() {
        return new User(RandomStringUtils.randomAlphanumeric(8),
                RandomStringUtils.randomAlphanumeric(8) + "@burger.ru",
                RandomStringUtils.randomAlphanumeric(5)); // для проверки некорректного пароля 5 символов
    }
}
