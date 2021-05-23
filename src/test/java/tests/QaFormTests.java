package tests;

import java.io.File;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class QaFormTests {
    String name = "Alex ";
    String lastName = "Alexeev";
    String email = "xyz@lxbuzz.xyz";
    String gender = "Male";    String hobby = "Reading";
    String phoneNumber = "4445567899";
    String monthOfBirth = "April";
    String yearOfBirth = "1978";
    String dayOfBirth = "13";
    String subject = "Chemistry";
    String currentAddress = "76 Broolklyh Neights str.";
    String state = "Haryana";
    String city = "Panipat";
    String filePath="src/test/resources/test_pic.png";

    //Set up Logger

    Logger logger= LoggerFactory.getLogger (QaFormTests.class);


    @BeforeAll
    static void setup() {
        Configuration.startMaximized=true;
    }

    @Test
    void fillPracticeFormTest() {

        //setup env

        open("https://demoqa.com/automation-practice-form");
        $(".main-header").shouldHave(text("Practice Form"));

        //perform exec

        $("#firstName").setValue(name);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(byText(gender)).click();
        $("#userNumber").setValue(phoneNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__day--0"+dayOfBirth).click();
        $("#subjectsInput").setValue("c");
        $(byText(subject)).click();
        $(byText(hobby)).click();
        $("#uploadPicture").uploadFile(new File(filePath));
        $("#currentAddress").setValue(currentAddress);
        $("#state").click();
        $(byText(state)).click();
        $("#city").click();
        $(byText(city)).click();
        String pngTestAct = screenshot("scr_act_test");
        $("#submit").click();
        //assert
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $$("tbody tr").filterBy(text("Student name")).shouldHave(texts(name + " " + lastName));
        $$("tbody tr").filterBy(text("Student Email")).shouldHave(texts(email));
        $$("tbody tr").filterBy(text("Gender")).shouldHave(texts(gender));
        $$("tbody tr").filterBy(text("Mobile")).shouldHave(texts(phoneNumber));
        $$("tbody tr").filterBy(text("Date of Birth")).shouldHave(texts(dayOfBirth+" "+monthOfBirth+","+yearOfBirth));
        $$("tbody tr").filterBy(text("Subjects")).shouldHave(texts(subject));
        $$("tbody tr").filterBy(text("Hobbies")).shouldHave(texts(hobby));
        $$("tbody tr").filterBy(text("Picture")).shouldHave(texts("pict.jpg"));
        $$("tbody tr").filterBy(text("Address")).shouldHave(texts(currentAddress));
        $$("tbody tr").filterBy(text("State and City")).shouldHave(texts(state+" "+city));
        // String pngTestAssert = screenshot("scr_assert_test");
        $("button#closeLargeModal").click();
        $("#example-modal-sizes-title-lg").shouldBe(hidden);
        String pngTestAssertClose = screenshot("scr_asserClose_test");

        System.out.println("Test Finished");
        logger.info("Ev OK!");


    }
}
