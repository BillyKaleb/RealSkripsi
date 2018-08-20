package com.example.realskripsi;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ASUS A46CM on 2018/06/30.
 */

public class KFoldCrossValidation {

    public void resultKFold(List<List<TFIDFName>> originalData, List<List<List<TFIDFName>>> resultData, int k){
        List<List<TFIDFName>> tempData = new ArrayList<List<TFIDFName>>();
        tempData = originalData;
        Log.d("list", "Side = " + Integer.toString(originalData.size()));
        int a = originalData.size() / k;
        Log.d("list", "After K = " + Integer.toString(a));
        int c = 0;
        for (int v = 0; v < k; v++) {
            List<List<TFIDFName>> calcData = new ArrayList<List<TFIDFName>>();
            calcData.clear();
            for (int z = 0; z < a; z++) {
                c = new Random().nextInt(tempData.size());
                Log.d("list", "Random Result = " + Integer.toString(c));
                calcData.add(tempData.get(c));
                tempData.remove(c);
            }
            resultData.add(calcData);
            Log.d("list", "Size Result = " + Integer.toString(originalData.size()));
            for (int b = 0; b < k; b++){
                Log.d("list", "List " + Integer.toString(v + 1) + " Line " + Integer.toString(b + 1) + " : " + resultData.get(v).get(b).get(0).getName());
            }
        }
    }
}
