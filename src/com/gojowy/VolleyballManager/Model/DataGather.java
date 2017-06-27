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
     * @param filePath
     */
    public DataGather(String filePath) {
        try {
            this.filePath = filePath;
            this.file = new File(this.filePath);
            gatherData();
            System.out.println("Data from file "+ filePath +" was correctly loaded");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gather data from file
     * @throws FileNotFoundException
     */
    private void gatherData() throws FileNotFoundException {
        Scanner scan = new Scanner(this.file);
        while (scan.hasNextLine()) {

            this.data.add(scan.nextLine());
        }
        scan.close();
    }

    public List<String> getData() {
        return this.data;
    }

}
