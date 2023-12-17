package ru.sfedu.simplepsy.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class PseudonymGenerator {
    static Map<Integer, String[]> names = new HashMap<>();
    static {
        names.put(1, new String[]{"Авксентий", "М"});
        names.put(2, new String[]{"Агафодор", "М"});
        names.put(3, new String[]{"Агафон", "М"});
        names.put(4, new String[]{"Агафья", "Ж"});
        names.put(5, new String[]{"Алексий", "М"});
        names.put(6, new String[]{"Анатолий", "М"});
        names.put(7, new String[]{"Ангелина", "Ж"});
        names.put(8, new String[]{"Аникий", "М"});
        names.put(9, new String[]{"Антонин", "М"});
        names.put(10, new String[]{"Антонина", "Ж"});
        names.put(11, new String[]{"Аркадий", "М"});
        names.put(12, new String[]{"Арсений", "М"});
        names.put(13, new String[]{"Артём", "М"});
        names.put(14, new String[]{"Артемий", "М"});
        names.put(15, new String[]{"Борис", "М"});
        names.put(16, new String[]{"Варвара", "Ж"});
        names.put(17, new String[]{"Варлаам", "М"});
        names.put(18, new String[]{"Василий", "М"});
        names.put(19, new String[]{"Василиск", "М"});
        names.put(20, new String[]{"Васой", "М"});
        names.put(21, new String[]{"Вассиан", "М"});
        names.put(22, new String[]{"Вера", "Ж"});
        names.put(23, new String[]{"Виктор", "М"});
        names.put(24, new String[]{"Виталий", "М"});
        names.put(25, new String[]{"Владимир", "М"});
        names.put(26, new String[]{"Владислав", "М"});
        names.put(27, new String[]{"Вячеслав", "М"});
        names.put(28, new String[]{"Галина", "Ж"});
        names.put(29, new String[]{"Геласий", "М"});
        names.put(30, new String[]{"Геннадий", "М"});
        names.put(31, new String[]{"Герман", "М"});
        names.put(32, new String[]{"Григорий", "М"});
        names.put(33, new String[]{"Дамиан", "М"});
        names.put(34, new String[]{"Данакт", "М"});
        names.put(35, new String[]{"Даниил", "М"});
        names.put(36, new String[]{"Донат", "М"});
        names.put(37, new String[]{"Досифей", "М"});
        names.put(38, new String[]{"Евгений", "М"});
        names.put(39, new String[]{"Екатерина", "Ж"});
        names.put(40, new String[]{"Елена", "Ж"});
        names.put(41, new String[]{"Ерофей", "М"});
        names.put(42, new String[]{"Есфирь", "Ж"});
        names.put(43, new String[]{"Илиан", "М"});
        names.put(44, new String[]{"Иоанн", "М"});
        names.put(45, new String[]{"Ираида", "Ж"});
        names.put(46, new String[]{"Ирина", "Ж"});
        names.put(47, new String[]{"Ия", "Ж"});
        names.put(48, new String[]{"Кира", "Ж"});
        names.put(49, new String[]{"Клавдия", "Ж"});
        names.put(50, new String[]{"Констанс", "Ж"});
        names.put(51, new String[]{"Константин", "М"});
        names.put(52, new String[]{"Констанций", "М"});
        names.put(53, new String[]{"Лариса", "Ж"});
        names.put(54, new String[]{"Леонид", "М"});
        names.put(55, new String[]{"Лукиан", "М"});
        names.put(56, new String[]{"Лукий", "М"});
        names.put(57, new String[]{"Лукия", "Ж"});
        names.put(58, new String[]{"Любовь", "Ж"});
        names.put(59, new String[]{"Максимилиан", "М"});
        names.put(60, new String[]{"Маргарита", "Ж"});
        names.put(61, new String[]{"Марина", "Ж"});
        names.put(62, new String[]{"Матрёна", "Ж"});
        names.put(63, new String[]{"Милица", "Ж"});
        names.put(64, new String[]{"Надежда", "Ж"});
        names.put(65, new String[]{"Нектарий", "М"});
        names.put(66, new String[]{"Неонилла", "Ж"});
        names.put(67, new String[]{"Никифор", "М"});
        names.put(68, new String[]{"Николай", "М"});
        names.put(69, new String[]{"Олимпиада", "Ж"});
        names.put(70, new String[]{"Павсикакий", "М"});
        names.put(71, new String[]{"Пахомий", "М"});
        names.put(72, new String[]{"Питирим", "М"});
        names.put(73, new String[]{"Прасковья", "Ж"});
        names.put(74, new String[]{"Прохор", "М"});
        names.put(75, new String[]{"Разумник", "М"});
        names.put(76, new String[]{"Раиса", "Ж"});
        names.put(77, new String[]{"Светлана", "Ж"});
        names.put(78, new String[]{"Серапион", "М"});
        names.put(79, new String[]{"Серафион", "М"});
        names.put(80, new String[]{"Сергий", "М"});
        names.put(81, new String[]{"Созонт", "М"});
        names.put(82, new String[]{"София", "Ж"});
        names.put(83, new String[]{"Тимофей", "М"});
        names.put(84, new String[]{"Трифон", "М"});
        names.put(85, new String[]{"Уалент", "М"});
        names.put(86, new String[]{"Уалентин", "М"});
        names.put(87, new String[]{"Феодор", "М"});
        names.put(88, new String[]{"Феофилакт", "М"});
        names.put(89, new String[]{"Христофор", "М"});
        names.put(90, new String[]{"Юлия", "Ж"});
        names.put(91, new String[]{"Юния", "Ж"});
    }

    static String url = "jdbc:your_database_url";
    static String username = "your_username";
    static String password = "your_password";

    public static void main(String[] args) {
        String problem = "Тревога";
        String gender = "Ж"; // Пол клиента (М - мужской, Ж - женский)
        String pseudonym = generatePseudonym(problem, gender);
        System.out.println("Сгенерированный псевдоним: " + pseudonym);

        savePseudonymToDatabase(pseudonym);
    }

    public static String generatePseudonym(String problem, String gender) {
        String[] filteredNames = filterNamesByGender(names, gender);
        String adjective = getRandomPsychiatricAdjective(gender);

        String name = getRandomElement(filteredNames);
        if (adjective.endsWith("ый")) {
            return adjective + " " + name;
        } else if (adjective.endsWith("ая")) {
            return adjective + " " + name;
        } else {
            return name + " " + adjective;
        }
    }

    private static String[] filterNamesByGender(Map<Integer, String[]> names, String gender) {
        List<String> filteredNames = new ArrayList<>();

        for (String[] name : names.values()) {
            if (name[1].equals(gender)) {
                filteredNames.add(name[0]);
            }
        }

        if (filteredNames.isEmpty()) {
            return new String[0];
        }

        return filteredNames.toArray(new String[filteredNames.size()]);
    }

    private static String getRandomPsychiatricAdjective(String gender) {
        String[] psychiatricAdjectives;
        if (gender.equals("М")) { // Male
            psychiatricAdjectives = new String[]{
                    "Тревожный", "В стрессе", "Депрессивный", "Психосоматичный",
                    "Потерявший близкого", "Брошенный", "с низкой самооценкой",
                    "В панике", "Неудовлетворенный", "Недолюбленный", "Воспитательный",
                    "Одинокий", "Профессиональный", "Впечатлительный", "Пугливый",
                    "утомленный", "Нервный", "Жертвенный", "Изгнанный", "Суетливый",
                    "На пороге"
            };
        } else { // Female
            psychiatricAdjectives = new String[]{
                    "Тревожная", "В стрессе", "Депрессивная", "Психосоматическая",
                    "Потерявшая близкого", "Брошенная", "с низкой самооценкой",
                    "В панике", "Неудовлетворенная", "Недолюбленная", "Воспитательная",
                    "Одинокая", "Профессиональная", "Впечатлительная", "Пугливая",
                    "утомленная", "Нервная", "Жертвенная", "Изгнанная", "Суетливая",
                    "На пороге"
            };
        }

        return getRandomElement(psychiatricAdjectives);
    }

    private static String getRandomElement(String[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }

        int index = new Random().nextInt(array.length);
        return array[index];
    }

    private static void savePseudonymToDatabase(String pseudonym) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO client (pseudonym) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, pseudonym);
            statement.executeUpdate();
            System.out.println("Псевдоним сохранен в базе данных.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
