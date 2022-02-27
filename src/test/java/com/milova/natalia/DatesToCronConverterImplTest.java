package com.milova.natalia;

import com.digdes.school.DatesToCronConvertException;
import org.junit.Before;
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
    public void test_convertToLocalDateTimes_when_invertedDate() throws DatesToCronConvertException {
        List<String> dates = new ArrayList<>();
        dates.add("25-01-2022T08:00:00");
        converter.convert(dates);
    }

    @Test(expected = DatesToCronConvertException.class)
    public void test_convertToLocalDateTimes_when_dateWithoutDelimiter() throws DatesToCronConvertException {
        List<String> dates = new ArrayList<>();
        dates.add("20220125T08:00:00");
        converter.convert(dates);
    }

    @Test(expected = DatesToCronConvertException.class)
    public void test_convertToLocalDateTimes_when_dateTimeWithoutT() throws DatesToCronConvertException {
        List<String> dates = new ArrayList<>();
        dates.add("2022-01-25 08:00:00");
        converter.convert(dates);
    }

    @Test(expected = DatesToCronConvertException.class)
    public void test_convertToLocalDateTimes_when_dateOnly() throws DatesToCronConvertException {
        List<String> dates = new ArrayList<>();
        dates.add("2022-01-25");
        converter.convert(dates);
    }

    @Test(expected = DatesToCronConvertException.class)
    public void test_convertToLocalDateTimes_when_timeOnly() throws DatesToCronConvertException {
        List<String> dates = new ArrayList<>();
        dates.add("08:00:00");
        converter.convert(dates);
    }

    @Test(expected = DatesToCronConvertException.class)
    public void test_convertToLocalDateTimes_when_anotherDelimiter() throws DatesToCronConvertException {
        List<String> dates = new ArrayList<>();
        dates.add("2022.01.25T08.00.00");
        converter.convert(dates);
    }

    @Test(expected = DatesToCronConvertException.class)
    public void test_convertToLocalDateTimes_when_lettersInDate() throws DatesToCronConvertException {
        List<String> dates = new ArrayList<>();
        dates.add("2022-01-AAT08:00:00");
        converter.convert(dates);
    }

    @Test(expected = DatesToCronConvertException.class)
    public void test_convertToLocalDateTimes_when_impossibleTime() throws DatesToCronConvertException {
        List<String> dates = new ArrayList<>();
        dates.add("2022-01-25T35:00:67");
        converter.convert(dates);
    }

    @Test(expected = DatesToCronConvertException.class)
    public void test_convertToLocalDateTimes_when_impossibleDate() throws DatesToCronConvertException {
        List<String> dates = new ArrayList<>();
        dates.add("2022-02-30T08:00:00");
        converter.convert(dates);
    }

    @Test(expected = DatesToCronConvertException.class)
    public void test_convertToLocalDateTimes_when_emptyListOfDates() throws DatesToCronConvertException {
        List<String> dates = new ArrayList<>();
        converter.convert(dates);
    }

    @Test(expected = DatesToCronConvertException.class)
    public void test_convertToLocalDateTimes_when_emptyDatesInList() throws DatesToCronConvertException {
        List<String> dates = new ArrayList<>();
        dates.add("");
        converter.convert(dates);
    }

    @Test(expected = DatesToCronConvertException.class)
    public void test_convertToLocalDateTimes_when_datesIsNull() throws DatesToCronConvertException {
        List<String> dates = null;
        converter.convert(dates);
    }

    @Test
    public void test_convert_when_all() throws DatesToCronConvertException {
        List<String> list = List.of("2022-01-25T08:00:00", "2022-01-25T08:00:01", "2022-01-25T08:00:02", "2022-01-25T08:00:03",
                      "2022-01-25T08:00:04", "2022-01-25T08:00:05", "2022-01-25T08:00:06", "2022-01-25T08:00:07");
        assertEquals("* 0 8 25 1 2", converter.convert(list));
    }

    @Test
    public void test_convert_when_rangeValue() throws DatesToCronConvertException {
        List<String> list = List.of("2022-01-25T08:00:00", "2022-01-25T08:00:01", "2022-01-25T08:00:02", "2022-01-25T08:00:03",
                "2022-01-25T08:00:04", "2022-01-25T08:00:05", "2022-01-25T08:00:06", "2022-01-25T08:00:10");
        assertEquals("0-6,10 0 8 25 1 2", converter.convert(list));
    }

    @Test
    public void test_convert_when_rangeValueRange() throws DatesToCronConvertException {
        List<String> list = List.of("2022-01-25T08:00:00", "2022-01-25T08:00:01", "2022-01-25T08:00:02", "2022-01-25T08:00:03",
                "2022-01-25T08:00:04", "2022-01-25T08:00:08", "2022-01-25T08:00:14", "2022-01-25T08:00:15", "2022-01-25T08:00:16");
        assertEquals("0-4,8,14-16 0 8 25 1 2",converter.convert(list));
    }

    @Test
    public void test_convert_when_valueRangeRange() throws DatesToCronConvertException {
        List<String> list = List.of("2022-01-25T08:00:00", "2022-01-25T08:00:03", "2022-01-25T08:00:04", "2022-01-25T08:00:05",
                "2022-01-25T08:00:05", "2022-01-25T08:00:13", "2022-01-25T08:00:14", "2022-01-25T08:00:15");
        assertEquals("0,3-5,13-15 0 8 25 1 2", converter.convert(list));
    }

    @Test
    public void test_convert_when_rangeWithStep() throws DatesToCronConvertException {
        List<String> list = List.of("2022-01-25T08:00:00", "2022-01-25T08:05:00", "2022-01-25T08:10:00", "2022-01-25T08:15:00",
                "2022-01-25T08:20:00", "2022-01-25T08:25:00", "2022-01-25T08:30:00", "2022-01-25T08:35:00");
        assertEquals("0 0/5 8 25 1 2", converter.convert(list));
    }

    @Test
    public void test_convert_when_rangeWithStepValue() throws DatesToCronConvertException {
        List<String> list = List.of("2022-01-25T08:00:00", "2022-01-25T08:05:00", "2022-01-25T08:10:00", "2022-01-25T08:15:00",
                "2022-01-25T08:20:00", "2022-01-25T08:25:00", "2022-01-25T08:27:00", "2022-01-25T08:29:00");
        assertEquals("0 0,5,10,15,20,25,27,29 8 25 1 2", converter.convert(list));
    }

    @Test
    public void test_convert_when_AllMinutes() throws DatesToCronConvertException {
        List<String> list = List.of("2022-01-25T08:01:10", "2022-01-25T08:02:10", "2022-01-25T08:03:10", "2022-01-25T08:04:10",
                "2022-01-25T08:05:10", "2022-01-25T08:06:10", "2022-01-25T08:07:10", "2022-01-25T08:08:10");
        assertEquals("10 * 8 25 1 2", converter.convert(list));
    }

    @Test
    public void test_convert_when_differentDates() throws DatesToCronConvertException {
        List<String> list = List.of("2022-01-25T08:00:00", "2022-01-29T10:05:00", "2022-01-30T15:34:48", "2022-02-01T22:33:44");
        assertEquals("0,44,48 0,5,33,34 8,10,15,22 1,25,29,30 * 2,6,7", converter.convert(list));
    }
}