package com.gojowy.VolleyballManager.Model;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by GM on 6/1/2017.
 */
public class DataGather {
    protected String filePath;
    protected List<String> data = new ArrayList<>();
    protected File file;

    /**
     * Prepare to gather data
     * @param filePath Path to the file
     */
    public DataGather(String filePath) {
        this.filePath = filePath;
        this.file = new File(this.filePath);
        showMessage(filePath);
    }


        /**
         * Gather data from file
         * @throws FileNotFoundException throw Exception when file is not found
         */

    public void gatherData() throws FileNotFoundException {
        try {
            Scanner scan = new Scanner(this.file);
            while (scan.hasNextLine()) {
                this.data.add(scan.nextLine());
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void showMessage(String filePath) {
        System.out.println("Data from file " + filePath + " was correctly loaded");
    }

    public List<String> getData() {
        return this.data;
    }

}
