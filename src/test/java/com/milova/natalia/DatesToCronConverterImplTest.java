package com.milova.natalia;

import com.digdes.school.DatesToCronConvertException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DatesToCronConverterImplTest {
    private final DatesToCronConverterImpl converter = new DatesToCronConverterImpl();

    @Before
    public void setUp() throws Exception {
    }

    @Test(expected = DatesToCronConvertException.class)
    public void test_convertToLocalDateTimes_then_invertedDate() throws DatesToCronConvertException {
        List<String> dates = new ArrayList<>();
        dates.add("25-01-2022T08:00:00");
        converter.convert(dates);
    }

    @Test(expected = DatesToCronConvertException.class)
    public void test_convertToLocalDateTimes_then_dateWithoutDelimiter() throws DatesToCronConvertException {
        List<String> dates = new ArrayList<>();
        dates.add("20220125T08:00:00");
        converter.convert(dates);
    }

    @Test(expected = DatesToCronConvertException.class)
    public void test_convertToLocalDateTimes_then_dateTimeWithoutT() throws DatesToCronConvertException {
        List<String> dates = new ArrayList<>();
        dates.add("2022-01-25 08:00:00");
        converter.convert(dates);
    }

    @Test(expected = DatesToCronConvertException.class)
    public void test_convertToLocalDateTimes_then_dateOnly() throws DatesToCronConvertException {
        List<String> dates = new ArrayList<>();
        dates.add("2022-01-25");
        converter.convert(dates);
    }

    @Test(expected = DatesToCronConvertException.class)
    public void test_convertToLocalDateTimes_then_timeOnly() throws DatesToCronConvertException {
        List<String> dates = new ArrayList<>();
        dates.add("08:00:00");
        converter.convert(dates);
    }

    @Test(expected = DatesToCronConvertException.class)
    public void test_convertToLocalDateTimes_then_anotherDelimiter() throws DatesToCronConvertException {
        List<String> dates = new ArrayList<>();
        dates.add("2022.01.25T08.00.00");
        converter.convert(dates);
    }

    @Test(expected = DatesToCronConvertException.class)
    public void test_convertToLocalDateTimes_then_lettersInDate() throws DatesToCronConvertException {
        List<String> dates = new ArrayList<>();
        dates.add("2022-01-AAT08:00:00");
        converter.convert(dates);
    }

    @Test(expected = DatesToCronConvertException.class)
    public void test_convertToLocalDateTimes_then_impossibleTime() throws DatesToCronConvertException {
        List<String> dates = new ArrayList<>();
        dates.add("2022-01-25T35:00:67");
        converter.convert(dates);
    }

    @Test(expected = DatesToCronConvertException.class)
    public void test_convertToLocalDateTimes_then_impossibleDate() throws DatesToCronConvertException {
        List<String> dates = new ArrayList<>();
        dates.add("2022-02-30T08:00:00");
        converter.convert(dates);
    }

    @Test(expected = DatesToCronConvertException.class)
    public void test_convertToLocalDateTimes_then_emptyListOfDates() throws DatesToCronConvertException {
        List<String> dates = new ArrayList<>();
        converter.convert(dates);
    }
}