package com.example.realskripsi;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ASUS A46CM on 2018/05/30.
 */

public class KNNCosine {

    private class KNNComparatorCosine implements Comparator<TFIDFDistance> {

        @Override
        public int compare(TFIDFDistance object1, TFIDFDistance object2) {
            return object1.getValue() > object2.getValue() ? -1 : object1.getValue() == object2.getValue() ? 0 : 1;
        }
    }

    public String KNN(String testKNN, List<String> KNNArray, List<TFIDFName> KNNtfidf, int k, List<List<List<TFIDFName>>> parent, Boolean isPositive) {
        KNNEuclidean calculator = new KNNEuclidean();
        List<TFIDFDistance> KNNDistanceCosine = new ArrayList<TFIDFDistance>();
        double n = 0;
        KNNDistanceCosine.clear();
        List<List<TFIDFName>> test = new ArrayList<List<TFIDFName>>();
        String result = "Empty";
        String name = "";
        // Triming text, put into KNNArray
        /*List<String> KNNArray = new ArrayList<String>();
        testKNN = testKNN.trim();
        String token[] = testKNN.split("[\\p{Punct}\\s]+");
        for (String s : token) {
            KNNArray.add(s);
        }*/

        /*List<TFIDFName> KNNtfidf = new ArrayList<>();
        // TFIDF for KNN, can't call method
        for (int i = 0; i < KNNArray.size(); i++) {
            String content = KNNArray.get(i);
            double tfidf = calculator.tfIdf(KNNArray, IDFList, content);
            KNNtfidf.add(new TFIDFName(content, tfidf));
        }*/


        double distanceCosine = 0;
        double dividerCosineTrain = 0;
        double dividerCosineTest = 0;
        Boolean checker = false;
        int discovery = 0, seeking = 0, giving = 0, request = 0, positive = 0, negative = 0;

        // Counting Distance for each parent
        for (int a = 0; a < parent.size(); a++) {
            test = parent.get(a);
            for (int i = 0; i < test.size(); i++) {
                // Resetting Distance every new iteration
                distanceCosine = 0;
                dividerCosineTrain = 0;
                dividerCosineTest = 0;
                // Checker to get out from next loop
                checker = false;
                for (int x = 0; x < KNNtfidf.size(); x++) {
                    if (checker) {
                        break;
                    }
                    String z = KNNtfidf.get(x).getName();
                    for (int j = 0; j < test.get(i).size(); j++) {
                        //Log.d("list", "GO");
                        String comparison = test.get(i).get(j).getName();
                        // Looking for same text
                        n++;
                        if (z.equals(comparison)) {
                            distanceCosine += KNNtfidf.get(x).getValue() * test.get(i).get(j).getValue();
                            //Log.d("list", "GET ONE");
                            //Log.d("list", Double.toString(Math.pow(KNNtfidf.get(x).getValue() - test.get(i).get(j).getValue(), 2)));
                            break;
                        }
                    }
                }
                //Cosine Ver
                for (int j = 0; j < test.get(i).size(); j++) {
                    dividerCosineTrain += Math.pow(test.get(i).get(j).getValue(), 2);
                }
                dividerCosineTrain = Math.sqrt(dividerCosineTrain);
                for (int x = 0; x < KNNtfidf.size(); x++) {
                    dividerCosineTest += Math.pow(KNNtfidf.get(x).getValue(), 2);
                }
                dividerCosineTest = Math.sqrt(dividerCosineTest);
                distanceCosine = distanceCosine / (dividerCosineTest * dividerCosineTrain);
                name = "Parent " + String.valueOf(a + 1);
                //Log.d("endResult", "Result = " + Double.toString(distanceCosine));
                KNNDistanceCosine.add(new TFIDFDistance(name, distanceCosine, a));
            }
        }
        // Sort the List, the lower the value, higher it's place supposed to be
        //Cosine Ver
        Collections.sort(KNNDistanceCosine, new KNNComparatorCosine());
        for (int x = 0; x < k; x++) {
            if (KNNDistanceCosine.get(x).getValue() != 0) {
                //Log.d("endResult", KNNDistanceCosine.get(x).getName());
                //Log.d("endResult", "Value: " + Double.toString(KNNDistanceCosine.get(x).getValue()));
            }
        }

        //Cosine Ver

        if (isPositive == false) {
            Boolean finalChecker = false;
            int kChecker = k;
            while (!finalChecker) {
                List<TFIDFDistance> SelectedDistanceCosine = new ArrayList<TFIDFDistance>(k);
                SelectedDistanceCosine.clear();
                discovery = 0;
                seeking = 0;
                giving = 0;
                request = 0;
                // Delete OK
                for (int i = 0; i < kChecker; i++) {
                    SelectedDistanceCosine.add(KNNDistanceCosine.get(i));
                    // Increase the value of category everytime each new item
                    if (KNNDistanceCosine.get(i).getParent() == 0) {
                        discovery++;
                    } else if (KNNDistanceCosine.get(i).getParent() == 1) {
                        seeking++;
                    } else if (KNNDistanceCosine.get(i).getParent() == 2) {
                        giving++;
                    } else if (KNNDistanceCosine.get(i).getParent() == 3) {
                        request++;
                    }
                }
                // Decide which category has higher value

                finalChecker = true;
                if (discovery > seeking && discovery > giving && discovery > request) {
                    result = "Problem Discovery";
                } else if (seeking > giving && seeking > request && seeking > discovery) {
                    result = "Information Seeking";
                } else if (giving > request && giving > seeking && giving > discovery) {
                    result = "Information Giving";
                } else if (request > seeking && request > giving && request > discovery) {
                    result = "Feature Request";
                } else {
                    kChecker -= 1;
                    finalChecker = false;
                }
            }
            // Return Category
            Log.d("list", "Disovery: " + discovery);
            Log.d("list", "Seeking: " + seeking);
            Log.d("list", "Giving: " + giving);
            Log.d("list", "Request: " + request);
        } else if (isPositive == true) {
            Boolean finalChecker = false;
            int kChecker = k;
            while (!finalChecker) {
                List<TFIDFDistance> SelectedDistanceCosine = new ArrayList<TFIDFDistance>(k);
                SelectedDistanceCosine.clear();
                positive = 0;
                negative = 0;
                // Delete OK
                for (int i = 0; i < kChecker; i++) {
                    SelectedDistanceCosine.add(KNNDistanceCosine.get(i));
                    // Increase the value of category everytime each new item
                    if (KNNDistanceCosine.get(i).getParent() == 0) {
                        positive++;
                    } else if (KNNDistanceCosine.get(i).getParent() == 1) {
                        negative++;
                    }
                    // Decide which category has higher value
                }
                finalChecker = true;
                if (positive > negative) {
                    result = "Positive";
                } else if (negative > positive) {
                    result = "Negative";
                } else {
                    kChecker -= 1;
                    finalChecker = false;
                }
            }
            // Return Category
            Log.d("list", "Positive: " + positive);
            Log.d("list", "Negative: " + negative);
        }
        Log.d("list", "Request: " + request);
        Log.d("list", "End Result: " + result);
        Log.d("list", "N Loop: " + Double.toString(n));
        return result;
    }
}

