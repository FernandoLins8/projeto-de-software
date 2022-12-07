package Models;

import java.util.Calendar;

public class Period {
    private Calendar startingDate;

    private Calendar endingDate;

    Period(Calendar startingDate, Calendar endingDate) {
        // Fix month decreased on initialization (index starting at 0)
//        startingDate.add(Calendar.MONTH, 1);
//        endingDate.add(Calendar.MONTH, 1);

        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public String toString() {
        return String.format(
            "Início.: %s | Término.: %s",
            this.getFormattedStartingDate(),
            this.getFormattedEndingDate()
        );
    }

    public String getFormattedStartingDate() {
        return String.format(
            "%s/%s/%s",
            this.startingDate.get(Calendar.DATE),
            this.startingDate.get(Calendar.MONTH)+1,
            this.startingDate.get(Calendar.YEAR)
        );
    }

    public String getFormattedEndingDate() {
        return String.format(
            "%s/%s/%s",
            this.endingDate.get(Calendar.DATE),
            this.endingDate.get(Calendar.MONTH)+1,
            this.endingDate.get(Calendar.YEAR)
        );
    }

    public Calendar getStartingDate() {
        return startingDate;
    }

    public Calendar getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Calendar endingDate) {
        this.endingDate = endingDate;
    }
}
