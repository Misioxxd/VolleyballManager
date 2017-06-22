package com.gojowy.VolleyballManager.Controller;

import com.sun.org.apache.bcel.internal.util.ClassPath;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by GM on 6/1/2017.
 */
public class FileController {
    protected String filePath;
    protected List<String> data = new ArrayList<>();
    protected File file;

    public FileController(String filePath) {
        try {
            this.filePath = filePath;
            this.file = new File(this.filePath);
            gatherData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

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
