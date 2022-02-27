package com.milova.natalia;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class CronExpression {
    private final Set<Integer> seconds;
    private final Set<Integer> minutes;
    private final Set<Integer> hours;
    private final Set<Integer> daysOfMonth;
    private final Set<Integer> months;
    private final Set<DayOfWeek> daysOfWeek;

    public CronExpression() {
        seconds = new HashSet<>();
        minutes = new HashSet<>();
        hours = new HashSet<>();
        daysOfMonth = new HashSet<>();
        months = new HashSet<>();
        daysOfWeek = new HashSet<>();
    }

    public Set<Integer> getSeconds() {
        return seconds;
    }

    public Set<Integer> getMinutes() {
        return minutes;
    }

    public Set<Integer> getHours() {
        return hours;
    }

    public Set<Integer> getDaysOfMonth() {
        return daysOfMonth;
    }

    public Set<Integer> getMonths() {
        return months;
    }

    public Set<DayOfWeek> getDaysOfWeek() {
        return daysOfWeek;
    }

    @Override
    public String toString() {
        StringBuilder cron = new StringBuilder();
        cron.append(transformSetOfIntegersToString(this.seconds)).append(" ");
        cron.append(transformSetOfIntegersToString(this.minutes)).append(" ");
        cron.append(transformSetOfIntegersToString(this.hours)).append(" ");
        cron.append(transformSetOfIntegersToString(this.daysOfMonth)).append(" ");
        cron.append(transformSetOfIntegersToString(this.months)).append(" ");
        cron.append(transformSetOfIntegersToString(this.daysOfWeek.stream().map(DayOfWeek::getValue).collect(Collectors.toSet())));
        return cron.toString();
    }

    private String transformSetOfIntegersToString(Set<Integer> set) {
        List<Integer> list = set.stream().sorted().collect(Collectors.toList());
        List<Integer> buffer = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if (list.size() == 1)
            return list.get(0).toString();
        else {
            String expression = checkForStep(list);
            if (!expression.isEmpty())
                return expression;
            for (int i = 0; i < list.size(); i++) {
                buffer.add(list.get(i));
                if (i == list.size() - 1) break;
                if (1 != list.get(i + 1) - list.get(i)) {
                    if (sb.length() > 0) sb.append(",");
                    sb.append(formatBuffer(buffer));
                    buffer.clear();
                }
            }
            if (buffer.size() == list.size())
                sb.append("*");
            else {
                if (buffer.size() != 0) {
                    if (sb.length() > 0) sb.append(",");
                    sb.append(formatBuffer(buffer));
                }
            }
        }
        return sb.toString();
    }

    private String checkForStep(List<Integer> list) {
        int delta = list.get(1) - list.get(0);
        if (delta < 2) return "";
        for (int i = 0; i < list.size() - 1; i++) {
            if (delta != list.get(i + 1) - list.get(i)) {
                return "";
            }
        }
        return String.format("%d/%d", list.get(0), delta);
    }

    private String formatBuffer(List<Integer> buffer) {
        StringBuilder sb = new StringBuilder();
        if (buffer.size() > 2) {
            sb.append(buffer.get(0)).append("-").append(buffer.get(buffer.size() - 1));
        } else if (buffer.size() == 2) {
            sb.append(buffer.get(0)).append(",").append(buffer.get(1));
        } else {
            sb.append(buffer.get(0));
        }
        return sb.toString();
    }

}
