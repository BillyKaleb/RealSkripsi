package com.example.realskripsi;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by ASUS A46CM on 2018/05/27.
 */

public class AssetLoader extends AppCompatActivity {
    public Resources resources;
    public String output;

    ArrayList<ArrayList<String>> parentList = new ArrayList<ArrayList<String>>();
    int parentNumber = 0;
    String parentName;

    public AssetLoader() {

    }

    public ArrayList readAsset(String fileName) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open("ReviewExample.txt")));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                parentNumber++;
                ArrayList<String> childList = new ArrayList<String>();
                mLine = mLine.trim();
                String tok[] = mLine.split(" ");
                for (String s : tok) {
                    childList.add(s);
                }
                parentList.add(childList);
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }

        //return the output stream as a String
        return parentList;
    }
}
