package com.example.a202sgi_assignment_tms;

import androidx.room.TypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TDCDateConverter {
    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @TypeConverter
    public static Date fromDate(String value) {
        if (value != null) {
            try {
                return df.parse(value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return null;
        }
    }

    @TypeConverter
    public static String toDate(Date value) {

        return value == null ? null : df.format(value);
    }
}
