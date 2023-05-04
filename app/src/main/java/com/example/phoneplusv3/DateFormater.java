package com.example.phoneplusv3;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormater
{
    public String getDate()
    {
        Date currentDate = new Date();
        String dateFormat = "dd-MM-yyyy"; // Specify your own date format
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        String currentTimeString = formatter.format(currentDate);

        return currentTimeString;

    }
}
