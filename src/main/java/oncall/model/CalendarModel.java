package oncall.model;

public class CalendarModel {
    final private static int[] MONTH_LAST_DAYS = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    final private int month;
    private int day;
    private KoDay koDay;

    public CalendarModel(int month, KoDay koDay) {
        this.month = month;
        this.day = 1;
        this.koDay = koDay;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String getKoDay() {
        return koDay.name();
    }

    public boolean nextDay() {
        if (day + 1 > MONTH_LAST_DAYS[month - 1]) {
            return false;
        }
        this.day++;
        this.koDay = KoDay.values()[(this.koDay.ordinal() + 1) % KoDay.values().length];
        return true;
    }

    public int getLastDay(int month) {
        return MONTH_LAST_DAYS[month - 1];
    }

    public boolean isCurrentHoliday() {
        return koDay == KoDay.토 || koDay == KoDay.일 ||
            Holiday.getHolidaysByIndex(month - 1).contains(day);
    }

    public String currentToString() {
        String str = month + "월 " + day + "일 " + koDay.name();
        if (isCurrentHoliday() && koDay != KoDay.토 && koDay != KoDay.일) {
            str += "(휴일)";
        }
        return str;
    }
}
