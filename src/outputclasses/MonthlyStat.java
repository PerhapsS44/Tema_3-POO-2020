package outputclasses;

import java.util.ArrayList;

public class MonthlyStat {
    private int month;
    private ArrayList<Long> distributorsIds;

    public MonthlyStat(int month, ArrayList<Long> distributorsIds) {
        this.month = month;
        this.distributorsIds = distributorsIds;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public ArrayList<Long> getDistributorsIds() {
        return distributorsIds;
    }

    public void setDistributorsIds(ArrayList<Long> distributorsIds) {
        this.distributorsIds = distributorsIds;
    }
}
