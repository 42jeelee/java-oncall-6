package oncall.model;

import java.util.ArrayList;
import java.util.List;

public enum Holiday {
    JANUARY(new ArrayList<>(List.of(1))),
    FEBRUARY(new ArrayList<>()),
    MARCH(new ArrayList<>(List.of(1))),
    APRIL(new ArrayList<>()),
    MAY(new ArrayList<>(List.of(5))),
    JUNE(new ArrayList<>(List.of(6))),
    JULY(new ArrayList<>()),
    AUGUST(new ArrayList<>(List.of(15))),
    SEPTEMBER(new ArrayList<>()),
    OCTOBER(new ArrayList<>(List.of(3, 9))),
    NOVEMBER(new ArrayList<>()),
    DECEMBER(new ArrayList<>(List.of(25)));

    private final ArrayList<Integer> days;

    Holiday(ArrayList<Integer> days) {
        this.days = days;
    }

    public ArrayList<Integer> getDays() {
        return days;
    }

    private static Holiday getMonthByIndex(int index) {
        for (Holiday holiday : Holiday.values()) {
            if (holiday.ordinal() == index) {
                return holiday;
            }
        }
        return null;
    }

    public static ArrayList<Integer> getHolidaysByIndex(int index) {
        Holiday holiday = getMonthByIndex(index);
        if (holiday == null) {
            throw new IndexOutOfBoundsException("Error: index out of range");
        }
        return holiday.getDays();
    }
}
