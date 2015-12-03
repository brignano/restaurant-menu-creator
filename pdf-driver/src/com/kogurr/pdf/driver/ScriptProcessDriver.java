package com.kogurr.pdf.driver;

import com.kogurr.pdf.driver.objects.Menu;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public enum ScriptProcessDriver {

    INSTANCE;

    private final static String PDF_DIRECTORY = "U:\\Code\\Java\\restaurant-menu-creator\\generated-pdfs\\";
    private final static String SCRIPT_PATH   = "U:\\Code\\Java\\restaurant-menu-creator\\scripts\\TemplateMaker.pl";

    // TODO: Return URL to file on completion
    public void makeMenu(String templateName, String fileName, Menu menu) {
        try {
            Process process = Runtime.getRuntime().exec(
                    "perl " + SCRIPT_PATH + " --template " + templateName
                    + " --file \"" + PDF_DIRECTORY + fileName + "\" "
                    + menu.buildString()
            );

            InputStream in = process.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = read.readLine()) != null) {
                System.out.println(line);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
