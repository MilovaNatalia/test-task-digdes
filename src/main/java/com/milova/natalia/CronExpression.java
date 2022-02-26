package com.milova.natalia;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.HashSet;

public class CronExpression {
    private final HashSet<Integer> seconds;
    private final HashSet<Integer> minutes;
    private final HashSet<Integer> hours;
    private final HashSet<Integer> daysOfMonth;
    private final HashSet<Month> months;
    private final HashSet<DayOfWeek> daysOfWeek;

    public CronExpression() {
        seconds = new HashSet<>();
        minutes = new HashSet<>();
        hours = new HashSet<>();
        daysOfMonth = new HashSet<>();
        months = new HashSet<>();
        daysOfWeek = new HashSet<>();
    }

    public HashSet<Integer> getSeconds() {
        return seconds;
    }

    public HashSet<Integer> getMinutes() {
        return minutes;
    }

    public HashSet<Integer> getHours() {
        return hours;
    }

    public HashSet<Integer> getDaysOfMonth() {
        return daysOfMonth;
    }

    public HashSet<Month> getMonths() {
        return months;
    }

    public HashSet<DayOfWeek> getDaysOfWeek() {
        return daysOfWeek;
    }

    @Override
    public String toString() {

        return null;
    }
}
