package com.example.realskripsi;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS A46CM on 2018/05/27.
 */

public class RawTFIDF {

    public double tf(List<String> doc, String term) {
        double result = 0;
        for (String word : doc) {
            if (term.equalsIgnoreCase(word))
                result++;
        }
        //Log.d("list", "Result :" + Double.toString(result / doc.size()));
        return result / doc.size();
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
        return tf(doc, term) * idf(docs, term);
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
        /*for (int i = 0; i < tfidfParent.get(0).size(); i++) {
            Log.d("list", tfidfParent.get(0).get(i).getName() + ", " + Double.toString(tfidfParent.get(0).get(i).getValue()));
        }
        for (int i = 0; i < tfidfParent.get(1).size(); i++) {
            Log.d("list", tfidfParent.get(0).get(i).getName() + ", " + Double.toString(tfidfParent.get(1).get(i).getValue()));
        }*/
    }
}

