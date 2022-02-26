package com.milova.natalia;

import com.digdes.school.DatesToCronConvertException;
import com.digdes.school.DatesToCronConverter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DatesToCronConverterImpl implements DatesToCronConverter {
    public DatesToCronConverterImpl() {
    }

    @Override
    public String convert(List<String> list) throws DatesToCronConvertException {
        CronExpression cronExpression = new CronExpression();
        try{
            List<LocalDateTime> localDateTimes = new ArrayList<>(convertToLocalDateTimes(list));
            for (LocalDateTime dateTime: localDateTimes) {
                cronExpression.getSeconds().add(dateTime.getSecond());
                cronExpression.getMinutes().add(dateTime.getMinute());
                cronExpression.getHours().add(dateTime.getHour());
                cronExpression.getDaysOfMonth().add(dateTime.getDayOfMonth());
                cronExpression.getMonths().add(dateTime.getMonth());
                cronExpression.getDaysOfWeek().add(dateTime.getDayOfWeek());
            }
        }
        catch (DateTimeParseException e){
            throw new DatesToCronConvertException();
        }
        return cronExpression.toString();
    }

    @Override
    public String getImplementationInfo() {
        return new Info(this.getClass().getSimpleName(), this.getClass().getPackageName()).toString();
    }

    private List<LocalDateTime> convertToLocalDateTimes(List<String> dates){
        return dates.stream().map(LocalDateTime::parse).sorted().collect(Collectors.toList());
    }
}
