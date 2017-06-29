package com.gojowy.VolleyballManager.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by GM on 6/27/2017.
 */
public class InputController {

    protected int maxRound;
    protected PrintWriter out;
    protected BufferedReader in;

    public InputController(PrintWriter out, BufferedReader in) {
        this.out = out;
        this.in = in;
    }

    public void proceed() throws IOException {
        while (true) {
            String roundNumber = in.readLine();
            if (isNumberValid(roundNumber)) {
                MainController mainController = new MainController(Integer.parseInt(roundNumber), in, out);
                mainController.proceed();
                break;
            } else if (roundNumber.toUpperCase().equals("QUIT")) {
                break;
            } else {
                showMessage();
            }
        }
    }
    /**
     * shows Error message
     */
    private void showMessage() {
        out.println("Insert right round amount(>5) or QUIT");
    }


    /**
     * Validate data passed by user
     *
     * @return boolean
     */
    private boolean isNumberValid(String value) {
        if (value.matches("\\D{1,}") || value.equals("")) {
            return false;
        } else if (Integer.parseInt(value) > 5) {
            return true;
        } else
            return false;
    }

}