package edu.iis.mto.time;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import edu.iis.mto.time.Order.State;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class OrderTest {

    private FakeSystemClock fakeSystemClock;
    private Order order;

    @Before
    public void setUp() throws Exception {
        fakeSystemClock = new FakeSystemClock();
        order = new Order(fakeSystemClock);
    }

    @Test(expected = OrderExpiredException.class)
    public void testForOrderExpiredExceptionThrowingForOneDay() {
        fakeSystemClock.setDateTime(new DateTime(2019, 4, 23, 0, 0));
        order.submit();
        fakeSystemClock.setDateTime(new DateTime(2019, 4, 24, 1, 0));
        order.confirm();
    }

    @Test
    public void testCheckingStateAfterOneHour() {
        fakeSystemClock.setDateTime(new DateTime(2019, 4, 23, 0, 0));
        order.submit();
        fakeSystemClock.setDateTime(new DateTime(2019, 4, 23, 1, 0));
        order.confirm();
        assertThat(order.getOrderState(), not(is(State.CANCELLED)));
    }

    @Test(expected = OrderStateException.class)
    public void testForOrderStateExceptionThrowingAfterRealizeMethod() {
        fakeSystemClock.setDateTime(new DateTime(2019, 4, 23, 0, 0));
        order.submit();
        fakeSystemClock.setDateTime(new DateTime(2019, 4, 23, 1, 0));
        order.confirm();
        order.realize();
    }

    @Test
    public void testStateAfterSubmitOrder() {
        fakeSystemClock.setDateTime(new DateTime(2019, 4, 23, 0, 0));
        order.submit();
        assertThat(order.getOrderState(), is(State.SUBMITTED));
    }

    @Test
    public void testStateAfterAddItemOrder() {
        fakeSystemClock.setDateTime(new DateTime(2019, 4, 23, 0, 0));
        order.addItem(new OrderItem());
        assertThat(order.getOrderState(), is(State.CREATED));
    }
}