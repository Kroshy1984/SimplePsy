package ru.sfedu.simplepsyspecialist.entity;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.sfedu.simplepsyspecialist.entity.nested.Question;
import ru.sfedu.simplepsyspecialist.entity.nested.TypeOfScoring;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Document("Scoring")
public class Scoring {
    private String id;
    private String title;
    private List<Question> questions;
    private TypeOfScoring type;
    private Date date;
//    @Transient
//    private static List<String> userData;
//    public List<String> answers = new ArrayList<>();
    @Transient
    public String customerId;

    public Scoring() {
    }

    public Scoring(List<Question> questions, String title) {
        this.questions = questions;
        this.title = title;
        this.date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    public TypeOfScoring getType() {
        return type;
    }

    public void setType(TypeOfScoring type) {
        this.type = type;
    }

//    public List<String> getAnswers() {
//        return answers;
//    }
//
//    public void setAnswers(List<String> answers) {
//        this.answers = answers;
//    }
//
//    public static List<String> getUserData() {
//        userData = new ArrayList<>();
//        userData.add("Ваше Имя:");
//        userData.add("Ваша фамилия:");
//        userData.add("Возраст:");
//        userData.add("Телефон:");
//        userData.add("E-mail:");
//        userData.add("Семейное положение:");
//        return userData;
//    }
//    public static List<String> getTextQuestions() {
//        textQuestions = new ArrayList<>();
//        textQuestions.add("Каково ваше общее самочувствие? Бывают ли следующие расстройства (сердцебиения, ощущение нехватки воздуха, головные боли, приливы жара, озноб, дрожь в теле)");
//        textQuestions.add("Укажите, какое у вас преимущественно настроение за прошедшие три месяца. Часто ли бывает апатия, тоска, тревога, подавленность, раздражение, как долго длятся эти состояния? Бывают ли немотивированные подъемы настроения и активности, и как долго они длятся?");
//        textQuestions.add("Употребляете ли вы на данный момент наркотические вещества и имели ли опыт их употребления? Как реагировали на них, возникала ли зависимость? Если возникала, то какая продложительность трезвости?");
//        textQuestions.add("Какие заметные изменения произошли в вашей жизни за последний год?");
//        textQuestions.add("Какую значимую информацию о своих родителях вы считаете важным сообщить?");
//        textQuestions.add("Есть ли у вас братья/сестры, какие отношения с ними? Какой вы ребенок по очереди рождения?");
//        textQuestions.add("Какую информацию о вашей семье/детях, вам кажется важным сообщить психологу?");
//        textQuestions.add("Принимаете ли вы обезболивающие препараты, транквилизаторы, нейролептики или антидепрессанты? Как часто, в каких дозах и по назначению какого врача:");
//        textQuestions.add("Употребляете ли вы алкоголь, как часто и в каком количестве? Бывали в вашей жизни периоды злоупотребления:");
//        textQuestions.add("Какова ваша работоспособность, концентрация, внимания, память? Возникали ли из-за этого проблемы на работе, в быту (важная динамика \"было/стало\"):");
//        textQuestions.add("Был ли у вас опыт обращения к психологу/психотерапевту? Если да, то опишите как вы запомнили этот опыт? Почему завершили терапевтические отношения?");
//        textQuestions.add("Есть ли у вас на момент прохождения терапии беременность или заболевания, о которых вы считаете важным сообщить терапевту (астма, эпилепсия, наличие кардиостимулятора, онкология и тп)?");
//        textQuestions.add("Что Вы ожидаете от сотрудничества с психологом? ");
//        textQuestions.add("Что еще Вы хотели бы рассказать о себе?");
//        return textQuestions;
//    }
//    public static List<String> getCheckboxQuestions() {
//        checkboxQuestions = new ArrayList<>();
//        checkboxQuestions.add("Тревога");
//        checkboxQuestions.add("Стресс");
//        checkboxQuestions.add("Депрессия");
//        checkboxQuestions.add("Психосоматические проблемы");
//        checkboxQuestions.add("Потеря близкого");
//        checkboxQuestions.add("Развод");
//        checkboxQuestions.add("Повышение самооценки");
//        checkboxQuestions.add("Панические атаки");
//        checkboxQuestions.add("Сексуальные проблемы");
//        checkboxQuestions.add("Отношения с родителями");
//        checkboxQuestions.add("Трудности с ребенком");
//        checkboxQuestions.add("Проблемы в общении");
//        checkboxQuestions.add("Рабочие вопросы и профориентация");
//        checkboxQuestions.add("Трудные эмоции и чувства");
//        checkboxQuestions.add("Навязчивые мысли, ритуалы");
//        checkboxQuestions.add("Повышенная утомляемость");
//        checkboxQuestions.add("Нервное напряжение и возбудимость");
//        checkboxQuestions.add("Недовольство собственной жизнью и желание ее изменить");
//        checkboxQuestions.add("Чувство изоляции и одиночество");
//        checkboxQuestions.add("Интенсивные изменения в жизни");
//        checkboxQuestions.add("Принятие важного решения");
//        checkboxQuestions.add("Личностные кризисы");
//        checkboxQuestions.add("Кризис взаимоотношений в паре и семье (измена, развод, противоречия)");
//        checkboxQuestions.add("Трудности в общении с партнерами, построении отношений");
//        checkboxQuestions.add("Нестабильность самооценки");
//        checkboxQuestions.add("Были попытки суицида и мысли о нем");
//        checkboxQuestions.add("Нарушения сна");
//        checkboxQuestions.add("Нарушения пищевого поведения");
//        return checkboxQuestions;
//    }
}