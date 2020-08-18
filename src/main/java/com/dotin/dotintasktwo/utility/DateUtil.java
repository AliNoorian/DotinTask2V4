package com.dotin.dotintasktwo.utility;

import com.dotin.dotintasktwo.model.Leave;


import java.util.List;

public class DateUtil {

    private boolean isOverLap;

    public DateUtil() {
    }

    public boolean checkDate(List<Leave> leaves, String fromDateLeave, String toDateLeave) {

        String[] fromPart = fromDateLeave.split(" ");
        String[] fromDatePart = fromPart[0].split("-");
        String[] fromTimePart = fromPart[1].split(":");

        System.out.println();
        System.out.println("==============================================");
        System.out.println(fromPart[0]);
        System.out.println("==============================================");
        System.out.println();
        int fromYear = Integer.parseInt(fromDatePart[0]);
        int fromMonth = Integer.parseInt(fromDatePart[1]);
        int fromDay = Integer.parseInt(fromDatePart[2]);
        int fromHour = Integer.parseInt(fromTimePart[0]);
        int fromMinutes = Integer.parseInt(fromTimePart[1]);


        String[] toPart = toDateLeave.split(" ");
        String[] toDatePart = toPart[0].split("-");
        String[] toTimePart = toPart[1].split(":");

        int toYear = Integer.parseInt(toDatePart[0]);
        int toMonth = Integer.parseInt(toDatePart[1]);
        int toDay = Integer.parseInt(toDatePart[2]);
        int toHour = Integer.parseInt(toTimePart[0]);
        int toMinutes = Integer.parseInt(toTimePart[1]);

        for (Leave leave : leaves) {
            String fromLeave = leave.getLeaveFrom();
            String toLeave = leave.getLeaveTo();

            String[] fromLeavePart = fromLeave.split(" ");
            String[] fromLeaveDatePart = fromLeavePart[0].split("-");
            String[] fromLeaveTimePart = fromLeavePart[1].split(":");

            int fromLeaveYear = Integer.parseInt(fromLeaveDatePart[0]);
            int fromLeaveMonth = Integer.parseInt(fromLeaveDatePart[1]);
            int fromLeaveDay = Integer.parseInt(fromLeaveDatePart[2]);
            int fromLeaveHour = Integer.parseInt(fromLeaveTimePart[0]);
            int fromLeaveMinutes = Integer.parseInt(fromLeaveTimePart[1]);


            String[] toLeavePart = toLeave.split(" ");
            String[] toLeaveDatePart = toLeavePart[0].split("-");
            String[] toLeaveTimePart = toLeavePart[1].split(":");

            int toLeaveYear = Integer.parseInt(toLeaveDatePart[0]);
            int toLeaveMonth = Integer.parseInt(toLeaveDatePart[1]);
            int toLeaveDay = Integer.parseInt(toLeaveDatePart[2]);
            int toLeaveHour = Integer.parseInt(toLeaveTimePart[0]);
            int toLeaveMinutes = Integer.parseInt(toLeaveTimePart[1]);

            if (toYear < fromLeaveYear || toMonth < fromLeaveMonth || toDay < fromLeaveDay) {
                isOverLap = false;
            } else if (fromYear > toLeaveYear || fromMonth > toLeaveMonth || fromDay > toLeaveDay) {
                isOverLap = false;
            } else if (fromYear == fromLeaveYear || fromMonth == fromLeaveMonth || fromDay == fromLeaveDay ||
                    toYear == toLeaveYear || toMonth == toLeaveMonth || toDay == fromLeaveDay) {
                if (toHour < fromLeaveHour || fromHour > toLeaveHour) {
                    isOverLap = false;
                } else if (fromHour == fromLeaveHour || toHour == fromLeaveHour) {
                    if (toMinutes < fromLeaveMinutes || fromMinutes > toLeaveMinutes) {
                        isOverLap = false;
                    }
                }

            } else {
                isOverLap = true;
            }

        }

        return isOverLap;
    }


}
