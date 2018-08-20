package com.example.realskripsi;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ASUS A46CM on 2018/05/30.
 */

public class KNNEuclidean {

    // Comparator, used in Collections.sort
    private class KNNComparator implements Comparator<TFIDFDistance> {

        @Override
        public int compare(TFIDFDistance object1, TFIDFDistance object2) {
            return object1.getValue() < object2.getValue() ? -1 : object1.getValue() == object2.getValue() ? 0 : 1;
        }
    }

    public String KNN(String testKNN, List<String> KNNArray, List<TFIDFName> KNNtfidf, int k, List<List<List<TFIDFName>>> parent, Boolean isPositive) {
        KNNEuclidean calculator = new KNNEuclidean();
        List<TFIDFDistance> KNNDistance = new ArrayList<TFIDFDistance>();
        KNNDistance.clear();
        List<List<TFIDFName>> test = new ArrayList<List<TFIDFName>>();
        double n = 0;
        test.clear();
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

        int check = 0;
        double distance = 0;
        Boolean checker = false;
        int discovery = 0, seeking = 0, giving = 0, request = 0, positive = 0, negative = 0;

        // Counting Distance for each parent
        for (int a = 0; a < parent.size(); a++) {
            test = parent.get(a);
            for (int i = 0; i < test.size(); i++) {
                // Resetting Distance every new iteration
                check += test.get(i).size();
                distance = 0;
                // Checker to get out from next loop
                checker = false;
                for (int x = 0; x < KNNtfidf.size(); x++) {
                    List<String> reverseCompare = new ArrayList<String>();
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
                            distance += Math.pow(KNNtfidf.get(x).getValue() - test.get(i).get(j).getValue(), 2);
                            reverseCompare.add(KNNtfidf.get(x).getName());
                            //Log.d("list", "GET ONE");
                            //Log.d("list", Double.toString(Math.pow(KNNtfidf.get(x).getValue() - test.get(i).get(j).getValue(), 2)));
                            break;
                        } else if (j == test.get(i).size() - 1) {
                            // Put 0 as value if can't found the same one from text
                            distance += Math.pow(0 - test.get(i).get(j).getValue(), 2);
                            //Log.d("list", "FAILED TO FIND ONE");
                            //Log.d("list", Double.toString(Math.pow(test.get(i).get(j).getValue() - 0, 2)));
                        }
                    }
                    for (int c = 0; c < KNNtfidf.size(); c++) {
                        String reverseComparison = KNNtfidf.get(c).getName();
                        if (!reverseCompare.contains(reverseComparison)) {
                            distance += Math.pow(0 - KNNtfidf.get(c).getValue(), 2);
                        }
                    }
                    reverseCompare.clear();
                }
                //Euclidean Ver
                // Sqrt the result, then put everything into KNNDistance
                // Log.d("list", Double.toString(distance));
                distance = Math.sqrt(distance);
                // Log.d("list", Double.toString(distance));
                name = "Parent " + String.valueOf(a + 1);
           //     Log.d("endResult", "Result = " + Double.toString(distance));
                KNNDistance.add(new TFIDFDistance(name, distance, a));
            }
        }
        // Sort the List, the lower the value, higher it's place supposed to be
        //Euclidean Ver
        Collections.sort(KNNDistance, new KNNComparator());
        for (int x = 0; x < k; x++) {
            //Log.d("list", KNNDistance.get(x).getName());
            //Log.d("list", "Value: " + Double.toString(KNNDistance.get(x).getValue()));
        }

        //Euclidean Ver

        if (isPositive == false) {
            Boolean finalChecker = false;
            int kChecker = k;
            while (!finalChecker) {
                List<TFIDFDistance> SelectedDistance = new ArrayList<TFIDFDistance>(k);
                SelectedDistance.clear();
                discovery = 0;
                seeking = 0;
                giving = 0;
                request = 0;
                // Delete OK
                for (int i = 0; i < kChecker; i++) {
                    SelectedDistance.add(KNNDistance.get(i));
                    // Increase the value of category everytime each new item
                    if (KNNDistance.get(i).getParent() == 0) {
                        discovery++;
                    } else if (KNNDistance.get(i).getParent() == 1) {
                        seeking++;
                    } else if (KNNDistance.get(i).getParent() == 2) {
                        giving++;
                    } else if (KNNDistance.get(i).getParent() == 3) {
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
                List<TFIDFDistance> SelectedDistance = new ArrayList<TFIDFDistance>(k);
                SelectedDistance.clear();
                positive = 0;
                negative = 0;
                // Delete OK
                for (int i = 0; i < kChecker; i++) {
                    //Log.d("list", "Once!");
                    SelectedDistance.add(KNNDistance.get(i));
                    // Increase the value of category everytime each new item
                    if (KNNDistance.get(i).getParent() == 0) {
                        positive++;
                    } else if (KNNDistance.get(i).getParent() == 1) {
                        negative++;
                    }
                }
                // Decide which category has higher value
                finalChecker = true;
                if (positive > negative) {
                    result = "Positive";
                } else if (negative > positive) {
                    result = "Negative";
                } else if (negative == positive) {
                    kChecker -= 1;
                    finalChecker = false;
                }
            }
            // Return Category
            Log.d("list", "Positive: " + positive);
            Log.d("list", "Negative: " + negative);
        }
        Log.d("list", "End Result: " + result);
        Log.d("list", "Size: " + Integer.toString(KNNDistance.size()));
        //Log.d("list", "N Loop: " + Double.toString(n));
        //Log.d("list", "N Loop: " + Integer.toString(check));
        return result;
    }
}
