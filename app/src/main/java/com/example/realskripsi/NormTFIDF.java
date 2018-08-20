package com.example.realskripsi;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS A46CM on 2018/05/28.
 */

public class NormTFIDF {

    // 2nd version, Augmented TF to eliminate longer word term
    public double augmentedtf(List<String> doc, String term) {
        double result = 0;
        double maxResult = 0;
        for (String word : doc) {
            if (term.equalsIgnoreCase(word))
                result++;
        }
        for (String word : doc) {
            int n = 0;
            for (String wordCompare : doc)
                // Looking for same text
                if (word.equalsIgnoreCase(wordCompare)) {
                    n++;
                }
            if (n > maxResult) {
                maxResult = n;
            }
        }
        //Log.d("list", "Result :" + Double.toString(0.4 + (0.6 * result / maxResult)));
        return 0.4 + (0.6 * result / maxResult);
    }

    public double idf(List<List<String>> docs, String term) {
        double n = 0;
        for (List<String> doc : docs) {
            for (String word : doc) {
                if (term.equalsIgnoreCase(word)) {
                    n++;
                    break;
                }
            }
        }
        if (n == 0) {
            return 0;
        } else {
            return Math.log(docs.size() / n);
        }
    }

    public double tfIdf(List<String> doc, List<List<String>> docs, String term) {
        return augmentedtf(doc, term) * idf(docs, term);
    }

    public void resultTFIDF(List<List<String>> parentList, List<List<TFIDFName>> tfidfParent, List<List<String>> IDFList) {
        // For for ParentList
        for (int i = 0; i < parentList.size(); i++) {
            List<String> compareText = new ArrayList<String>();
            compareText.clear();
            List<TFIDFName> tfidfChild = new ArrayList<>();
            // For for Childlist, include creating new TFIDFName object
            for (int j = 0; j < parentList.get(i).size(); j++) {
                String content = parentList.get(i).get(j);
                if (!compareText.contains(content)) {
                    compareText.add(content);
                    double tfidf = tfIdf(parentList.get(i), IDFList, content);
                    tfidfChild.add(new TFIDFName(content, tfidf));
                }
            }
            tfidfParent.add(tfidfChild);
        }

        // Printout, delete OK
        /*for (int i = 0; i < tfidfParent.get(0).size(); i++) {
            Log.d("list", tfidfParent.get(0).get(i).getName() + ", " + Double.toString(tfidfParent.get(0).get(i).getValue()));
        }
        for (int i = 0; i < tfidfParent.get(1).size(); i++) {
            Log.d("list", tfidfParent.get(0).get(i).getName() + ", " + Double.toString(tfidfParent.get(1).get(i).getValue()));
        }*/
    }

    public void smallerTFIDF(List<String> parentList, List<TFIDFName> tfidfParent, List<List<String>> IDFList) {
        List<String> compareTextSmall = new ArrayList<String>();
        // For for ParentList
        TFIDFName tfidfName = new TFIDFName();
        // For for Childlist, include creating new TFIDFName object
        compareTextSmall.clear();
        for (int j = 0; j < parentList.size(); j++) {
            String content = parentList.get(j);
            if (!compareTextSmall.contains(content)) {
                compareTextSmall.add(content);
                double tfidf = tfIdf(parentList, IDFList, content);
                tfidfName = new TFIDFName(content, tfidf);
                //Log.d("list", Double.toString(tfidfName.getValue()));
                tfidfParent.add(tfidfName);
            }
        }

        // Printout, delete OK
        /*for (int i = 0; i < tfidfParent.size(); i++) {
            Log.d("list", tfidfParent.get(i).getName() + ", " + Double.toString(tfidfParent.get(0).getValue()));
        }*/
    }
}
