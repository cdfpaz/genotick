package com.alphatica.genotick.genotick;

import com.alphatica.genotick.data.DataSetName;
import com.alphatica.genotick.processor.NotEnoughDataException;

import java.util.ArrayList;
import java.util.List;

public class ProgramData {
    final private List<double[]> data;
    private final DataSetName name;

    public static ProgramData createData(List<double[]> newData, DataSetName name) {
        return new ProgramData(newData,name);
    }

    public static ProgramData createEmptyData(DataSetName name) {
        List<double []> list = new ArrayList<>();
        list.add(new double[0]);
        return createData(list,name);
    }

    private ProgramData(List<double[]> newData, DataSetName name) {
        data = newData;
        this.name = name;
    }

    /**
     * Gets program data
     * @param dataTableIndex number of table to look in
     * @param dataOffsetIndex index in table no. dataTableIndex
     * @return data
     */
    public double getData(int dataTableIndex, int dataOffsetIndex) {
        int tableIndex = normalize(dataTableIndex,data.size());
        assert tableIndex >= 0: "tableIndex";
        if(tableIndex < 0 || tableIndex >= data.size()) {
            System.out.println("TableIndex is " + tableIndex + " data.size = " + data.size() +
                    " normalize: " + normalize(dataTableIndex,data.size()));
            System.exit(0);
        }
        if (dataOffsetIndex >= data.get(tableIndex).length)
            throw new NotEnoughDataException();
        else
            return data.get(tableIndex)[dataOffsetIndex];
    }

    private int normalize(long number, int max) {
        if(number == 0 || max == 1)
            return 0;
        long positive = number > 0 ? number : -number;
        return (int)(positive < max ? positive : positive % max);
    }

    public DataSetName getName() {
        return name;
    }

    public boolean isEmpty() {
        return data.get(0).length == 0;
    }
}
