package com.example.a202sgi_assignment_tms;

import androidx.room.TypeConverter;

import java.util.ArrayList;

public class ArrayStringConverter {
    @TypeConverter
    public static ArrayList<Integer> fromString(String jsonfile) {
        ArrayList<Integer> list = new ArrayList<>();
        String[] array = jsonfile.split(",");
        for (String s : array) {
            if (!s.isEmpty()) {
                list.add(Integer.parseInt(s));
            }
        }
        return list;
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<Integer> member_list) {
        String members = "";
        for (int i : member_list) {
            members += "," + i;
        }
        return members;
    }
}
