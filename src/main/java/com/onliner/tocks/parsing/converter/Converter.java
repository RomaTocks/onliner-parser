package com.onliner.tocks.parsing.converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Converter
{
    private static final String INTEGER_PATTERN = "\\d+";
    private static final String DOUBLE_PATTERN = "\\d+\\.\\d+";

    public static Integer getIntegersFromString(String string) {
        Pattern pattern = Pattern.compile(INTEGER_PATTERN);
        String preparedString = prepareString(pattern, string);
        if(preparedString.isEmpty()) {
            return null;
        }
        return Integer.valueOf(preparedString);
    }
    public static Double getDoubleFromString(String string) {
        Pattern pattern = Pattern.compile(DOUBLE_PATTERN);
        String preparedString = prepareString(pattern, string);
        if(preparedString.isEmpty()) {
            pattern = Pattern.compile(INTEGER_PATTERN);
            String newPreparedString = prepareString(pattern, string);
            if(newPreparedString.isEmpty()) {
                return null;
            }
            return Double.valueOf(newPreparedString);
        }
        return Double.valueOf(prepareString(pattern,string));
    }
    private static String prepareString(Pattern pattern, String string) {
        Matcher matcher = pattern.matcher(string);
        StringBuilder value = new StringBuilder();
        while (matcher.find())
        {
            value.append(string, matcher.start(), matcher.end());
        }
        return value.toString();
    }
}
