package systemio;

import inputclasses.InitialData;
import inputclasses.MonthlyUpdate;

import java.util.ArrayList;

public final class InputData {
    private int numberOfTurns;
    private InitialData initialData;
    private ArrayList<MonthlyUpdate> monthlyUpdates;

    public InputData() {
    }

    public InputData(final int numberOfTurns, final InitialData initialData,
                     final ArrayList<MonthlyUpdate> monthlyUpdates) {
        this.numberOfTurns = numberOfTurns;
        this.initialData = initialData;
        this.monthlyUpdates = monthlyUpdates;
    }

    public InputData(final InputData inputData) {
        this.numberOfTurns = inputData.numberOfTurns;
        this.initialData = inputData.initialData;
        this.monthlyUpdates = inputData.monthlyUpdates;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public void setNumberOfTurns(final int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public InitialData getInitialData() {
        return initialData;
    }

    public void setInitialData(final InitialData initialData) {
        this.initialData = initialData;
    }

    public ArrayList<MonthlyUpdate> getMonthlyUpdates() {
        return monthlyUpdates;
    }

    public void setMonthlyUpdates(final ArrayList<MonthlyUpdate> monthlyUpdates) {
        this.monthlyUpdates = monthlyUpdates;
    }
}
