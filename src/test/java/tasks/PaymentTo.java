package tasks;

import helper.HelperMethods;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SendKeys;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;
import pages.HomePageElements;
import pages.PaymentPageElements;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class PaymentTo implements Task {

    @Override
    @Step("{0} logins to the eribank")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(HomePageElements.PAYMENT_BTN, isClickable()).forNoMoreThan(3).seconds(),
                Click.on(HomePageElements.PAYMENT_BTN),
                WaitUntil.the(PaymentPageElements.COUNTRY_TEXT, isClickable()).forNoMoreThan(3).seconds(),
                Click.on(PaymentPageElements.PHONE_TEXT),
                SendKeys.of(HelperMethods.getRandomPhone()).into(PaymentPageElements.PHONE_TEXT),
                Click.on(PaymentPageElements.NAME_TEXT),
                SendKeys.of(HelperMethods.getRandomFirstname()).into(PaymentPageElements.NAME_TEXT),
                SendKeys.of(String.valueOf(HelperMethods.getRandomPrice())).into(PaymentPageElements.AMOUNT),
                Click.on(PaymentPageElements.COUNTRY_BTN),
                Click.on(PaymentPageElements.getCountry(HelperMethods.getRandomCountry())),
                Click.on(PaymentPageElements.SEND_PAYMENT_BTN),
                WaitUntil.the(PaymentPageElements.SENDPAYMENTYES_BUTTON, isClickable()).forNoMoreThan(3).seconds(),
                Click.on(PaymentPageElements.SENDPAYMENTYES_BUTTON)
        );

    }

    public static PaymentTo info(Integer country) {
        return instrumented(PaymentTo.class,country);
    }
}
