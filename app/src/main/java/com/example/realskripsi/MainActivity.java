package com.example.realskripsi;

import android.os.Environment;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnRawE, btnRawC, btnNormE, btnNormC;
    int RawE, RawC, NormE, NormC;
    EditText txtInput;
    String editText;
    TextView txtContent;
    String ret = "";
    private String filepath = "MyFileStorage";
    String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Matrix";
    File sdcard = Environment.getExternalStorageDirectory();
    File file = new File(sdcard, "file.txt");
    StringBuilder text = new StringBuilder();
    String filename = "trial.txt";
    String test = "pleas add redeem option app pleas";

    // List compiled Data Training
    List<List<List<TFIDFName>>> trainListN = new ArrayList<List<List<TFIDFName>>>();
    List<List<List<TFIDFName>>> trainListR = new ArrayList<List<List<TFIDFName>>>();
    List<List<List<TFIDFName>>> trainListB = new ArrayList<List<List<TFIDFName>>>();
    List<List<List<TFIDFName>>> trainListN2 = new ArrayList<List<List<TFIDFName>>>();
    List<List<List<TFIDFName>>> trainListR2 = new ArrayList<List<List<TFIDFName>>>();
    List<List<List<TFIDFName>>> trainListB2 = new ArrayList<List<List<TFIDFName>>>();
    List<String> testList = new ArrayList<String>();
    List<TFIDFName> testValue = new ArrayList<TFIDFName>();
    List<String> dataTest = new ArrayList<String>();
    List<String> dataTest2 = new ArrayList<String>();
    List<String> dataTest3 = new ArrayList<String>();
    List<String> dataTestPD = new ArrayList<String>();
    List<String> dataTestIS = new ArrayList<String>();
    List<String> dataTestIG = new ArrayList<String>();
    List<String> dataTestFR = new ArrayList<String>();

    // List which include separated text
    List<List<String>> PDiscovery = new ArrayList<List<String>>();
    List<List<String>> PSeeking = new ArrayList<List<String>>();
    List<List<String>> PGiving = new ArrayList<List<String>>();
    List<List<String>> PRequest = new ArrayList<List<String>>();
    List<List<String>> PPositive = new ArrayList<List<String>>();
    List<List<String>> PNegative = new ArrayList<List<String>>();

    List<List<String>> POriginal = new ArrayList<List<String>>();
    List<String> PStopword = new ArrayList<String>();
    List<List<String>> PResult = new ArrayList<List<String>>();

    //List which include object with word and TFIDF value inside
    List<List<TFIDFName>> tfidfDiscoveryR = new ArrayList<List<TFIDFName>>();
    List<List<TFIDFName>> tfidfDiscoveryN = new ArrayList<List<TFIDFName>>();
    List<List<TFIDFName>> tfidfDiscoveryB = new ArrayList<List<TFIDFName>>();
    List<List<TFIDFName>> tfidfSeekingR = new ArrayList<List<TFIDFName>>();
    List<List<TFIDFName>> tfidfSeekingN = new ArrayList<List<TFIDFName>>();
    List<List<TFIDFName>> tfidfSeekingB = new ArrayList<List<TFIDFName>>();
    List<List<TFIDFName>> tfidfGivingR = new ArrayList<List<TFIDFName>>();
    List<List<TFIDFName>> tfidfGivingN = new ArrayList<List<TFIDFName>>();
    List<List<TFIDFName>> tfidfGivingB = new ArrayList<List<TFIDFName>>();
    List<List<TFIDFName>> tfidfRequestR = new ArrayList<List<TFIDFName>>();
    List<List<TFIDFName>> tfidfRequestN = new ArrayList<List<TFIDFName>>();
    List<List<TFIDFName>> tfidfRequestB = new ArrayList<List<TFIDFName>>();
    List<List<TFIDFName>> tfidfPositiveR = new ArrayList<List<TFIDFName>>();
    List<List<TFIDFName>> tfidfPositiveN = new ArrayList<List<TFIDFName>>();
    List<List<TFIDFName>> tfidfPositiveB = new ArrayList<List<TFIDFName>>();
    List<List<TFIDFName>> tfidfNegativeR = new ArrayList<List<TFIDFName>>();
    List<List<TFIDFName>> tfidfNegativeN = new ArrayList<List<TFIDFName>>();
    List<List<TFIDFName>> tfidfNegativeB = new ArrayList<List<TFIDFName>>();

    List<List<TFIDFName>> tfidfDiscoveryFoldN = new ArrayList<List<TFIDFName>>();
    List<List<TFIDFName>> tfidfDiscoveryFoldResultN = new ArrayList<List<TFIDFName>>();
    List<List<List<TFIDFName>>> foldPositiveN = new ArrayList<List<List<TFIDFName>>>();
    List<List<List<TFIDFName>>> foldNegativetN = new ArrayList<List<List<TFIDFName>>>();
    List<List<List<TFIDFName>>> foldPositiveR = new ArrayList<List<List<TFIDFName>>>();
    List<List<List<TFIDFName>>> foldNegativetR = new ArrayList<List<List<TFIDFName>>>();
    List<List<List<TFIDFName>>> foldPositiveB = new ArrayList<List<List<TFIDFName>>>();
    List<List<List<TFIDFName>>> foldNegativetB = new ArrayList<List<List<TFIDFName>>>();

    String result;
    int percentResult = 0;
    int percentResult2 = 0;
    int discoveryResultDiscovery = 0;
    int seekingResultDiscovery = 0;
    int givingResultDiscovery = 0;
    int requestResultDiscovery = 0;
    int discoveryResultSeeking = 0;
    int seekingResultSeeking = 0;
    int givingResultSeeking = 0;
    int requestResultSeeking = 0;
    int discoveryResultGiving = 0;
    int seekingResultGiving = 0;
    int givingResultGiving = 0;
    int requestResultGiving = 0;
    int discoveryResultRequest = 0;
    int seekingResultRequest = 0;
    int givingResultRequest = 0;
    int requestResultRequest = 0;

    long startTime = SystemClock.elapsedRealtime();

    //Compiled List
    List<List<String>> IDFList = new ArrayList<List<String>>();
    List<List<String>> IDFList2 = new ArrayList<List<String>>();

    TFIDFName name = new TFIDFName();
    double x = 0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RawTFIDF rawTFIDF = new RawTFIDF();
        final NormTFIDF normTFIDF = new NormTFIDF();
        final BrunoTFIDF brunoTFIDF = new BrunoTFIDF();
        final KNNEuclidean knnEuclidean = new KNNEuclidean();
        final KNNCosine knnCosine = new KNNCosine();
        final KFoldCrossValidation kFold = new KFoldCrossValidation();

        txtContent = (TextView) findViewById(R.id.txtContent);
        txtInput = (EditText) findViewById(R.id.txtInput);

        long readStartTime = System.nanoTime();
        readAsset(PDiscovery, "ProblemDiscovery.txt");
        readAsset(PSeeking, "InformationSeeking.txt");
        readAsset(PGiving, "InformationGiving.txt");
        readAsset(PRequest, "FeatureRequest.txt");

        //readAsset(PPositive, "Positive.txt");
        //readAsset(PNegative, "Negative.txt");
        //readAsset(POriginal, "OriginalStopword.txt");
        //readAssetStopword(PStopword, "Stopword.txt");
        long readEndTime = System.nanoTime();
        final long readDuration = (readEndTime - readStartTime);

        //readAssetnoLine(dataTest, "DataTest.txt");
        readAssetnoLine(dataTest2, "NegativeTest.txt");
        readAssetnoLine(dataTest3, "PositiveTest.txt");
        readAssetnoLine(dataTestPD, "TrainingTest1.txt");
        readAssetnoLine(dataTestIS, "TrainingTest2.txt");
        readAssetnoLine(dataTestIG, "TrainingTest3.txt");
        readAssetnoLine(dataTestFR, "TrainingTest4.txt");
        //readAsset(TPositive, "PositiveTest.txt");
        //readAssetnoLine(TPositivenoLine, "PositiveTest.txt");
        //readAsset(PGiving, "InformationGiving.txt");
        //readAsset(PRequest, "FeatureRequest.txt");

        IDFList.addAll(PDiscovery);
        IDFList.addAll(PSeeking);
        IDFList.addAll(PGiving);
        IDFList.addAll(PRequest);

        IDFList2.addAll(PPositive);
        IDFList2.addAll(PNegative);

        long normStartTime = System.nanoTime();
        normTFIDF.resultTFIDF(PDiscovery, tfidfDiscoveryN, IDFList);
        normTFIDF.resultTFIDF(PSeeking, tfidfSeekingN, IDFList);
        normTFIDF.resultTFIDF(PGiving, tfidfGivingN, IDFList);
        normTFIDF.resultTFIDF(PRequest, tfidfRequestN, IDFList);

        normTFIDF.resultTFIDF(PPositive, tfidfPositiveN, IDFList2);
        normTFIDF.resultTFIDF(PNegative, tfidfNegativeN, IDFList2);
        long normEndTime = System.nanoTime();
        final long normDuration = (normEndTime - normStartTime);

        long rawStartTime = System.nanoTime();
        rawTFIDF.resultTFIDF(PDiscovery, tfidfDiscoveryR, IDFList);
        rawTFIDF.resultTFIDF(PSeeking, tfidfSeekingR, IDFList);
        rawTFIDF.resultTFIDF(PGiving, tfidfGivingR, IDFList);
        rawTFIDF.resultTFIDF(PRequest, tfidfRequestR, IDFList);

        rawTFIDF.resultTFIDF(PPositive, tfidfPositiveR, IDFList2);
        rawTFIDF.resultTFIDF(PNegative, tfidfNegativeR, IDFList2);
        long rawEndTime = System.nanoTime();
        final long rawDuration = (rawEndTime - rawStartTime);

        long brunoStartTime = System.nanoTime();
        /*brunoTFIDF.resultTFIDF(PDiscovery, tfidfDiscoveryB, IDFList);
        brunoTFIDF.resultTFIDF(PSeeking, tfidfSeekingB, IDFList);
        brunoTFIDF.resultTFIDF(PGiving, tfidfGivingB, IDFList);
        brunoTFIDF.resultTFIDF(PRequest, tfidfRequestB, IDFList);*/

        //brunoTFIDF.resultTFIDF(PPositive, tfidfPositiveB, IDFList2);
        //brunoTFIDF.resultTFIDF(PNegative, tfidfNegativeB, IDFList2);
        long brunoEndTime = System.nanoTime();
        final long brunoDuration = (brunoEndTime - brunoStartTime);

        List<List<List<TFIDFName>>> tempFoldPositiveN = new ArrayList<List<List<TFIDFName>>>();
        List<List<List<TFIDFName>>> tempFoldNegativeN = new ArrayList<List<List<TFIDFName>>>();
        List<List<TFIDFName>> validFoldPositiveN = new ArrayList<List<TFIDFName>>();
        List<List<TFIDFName>> validFoldNegativeN = new ArrayList<List<TFIDFName>>();
        List<List<List<TFIDFName>>> poolFoldN = new ArrayList<List<List<TFIDFName>>>();
        List<List<List<TFIDFName>>> poolFoldNegativeN = new ArrayList<List<List<TFIDFName>>>();

        trainListN.add(tfidfDiscoveryN);
        trainListN.add(tfidfSeekingN);
        trainListN.add(tfidfGivingN);
        trainListN.add(tfidfRequestN);
        trainListR.add(tfidfDiscoveryR);
        trainListR.add(tfidfSeekingR);
        trainListR.add(tfidfGivingR);
        trainListR.add(tfidfRequestR);

        /*trainListB.add(tfidfDiscoveryB);
        trainListB.add(tfidfSeekingB);
        trainListB.add(tfidfGivingB);
        trainListB.add(tfidfRequestB);*/

        trainListN2.add(tfidfPositiveN);
        trainListN2.add(tfidfNegativeN);

        trainListR2.add(tfidfPositiveR);
        trainListR2.add(tfidfNegativeR);

        /*trainListB2.add(tfidfPositiveB);
        trainListB2.add(tfidfNegativeB);*/

        btnRawE = (Button) findViewById(R.id.btnRawE);
        btnRawE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int z = 11; z <= 39; z += 2) {
                    discoveryResultDiscovery = 0;
                    seekingResultDiscovery = 0;
                    givingResultDiscovery = 0;
                    requestResultDiscovery = 0;
                    discoveryResultSeeking = 0;
                    seekingResultSeeking = 0;
                    givingResultSeeking = 0;
                    requestResultSeeking = 0;
                    discoveryResultGiving = 0;
                    seekingResultGiving = 0;
                    givingResultGiving = 0;
                    requestResultGiving = 0;
                    discoveryResultRequest = 0;
                    seekingResultRequest = 0;
                    givingResultRequest = 0;
                    requestResultRequest = 0;
                    for (int x = 0; x < 30; x++) {
                        testList.clear();
                        testValue.clear();
                        brokeLine(testList, dataTestPD.get(x));
                        rawTFIDF.smallerTFIDF(testList, testValue, IDFList);
                        //long rEucStartTime = System.nanoTime();
                        result = knnCosine.KNN(test, testList, testValue, z, trainListR, false);
                        if (result.equals("Problem Discovery")) {
                            discoveryResultDiscovery++;
                        } else if (result.equals("Information Seeking")) {
                            seekingResultDiscovery++;
                        } else if (result.equals("Information Giving")) {
                            givingResultDiscovery++;
                        } else if (result.equals("Feature Request")) {
                            requestResultDiscovery++;
                        }
                        //long rEucEndTime = System.nanoTime();
                        //long rEucDuration = (rEucEndTime - rEucStartTime);

                        testList.clear();
                        testValue.clear();
                        brokeLine(testList, dataTestIS.get(x));
                        rawTFIDF.smallerTFIDF(testList, testValue, IDFList);
                        //long rCosStartTime = System.nanoTime();
                        result = knnCosine.KNN(test, testList, testValue, z, trainListR, false);
                        if (result.equals("Problem Discovery")) {
                            discoveryResultSeeking++;
                        } else if (result.equals("Information Seeking")) {
                            seekingResultSeeking++;
                        } else if (result.equals("Information Giving")) {
                            givingResultSeeking++;
                        } else if (result.equals("Feature Request")) {
                            requestResultSeeking++;
                        }
                        //long rCosEndTime = System.nanoTime();
                        // long rCosDuration = (rCosEndTime - rCosStartTime);

                        testList.clear();
                        testValue.clear();
                        brokeLine(testList, dataTestIG.get(x));
                        rawTFIDF.smallerTFIDF(testList, testValue, IDFList);
                        //long nEucStartTime = System.nanoTime();
                        result = knnCosine.KNN(test, testList, testValue, z, trainListR, false);
                        if (result.equals("Problem Discovery")) {
                            discoveryResultGiving++;
                        } else if (result.equals("Information Seeking")) {
                            seekingResultGiving++;
                        } else if (result.equals("Information Giving")) {
                            givingResultGiving++;
                        } else if (result.equals("Feature Request")) {
                            requestResultGiving++;
                        }
                        //long nEucEndTime = System.nanoTime();
                        //long nEucDuration = (nEucEndTime - nEucStartTime);

                        testList.clear();
                        testValue.clear();
                        brokeLine(testList, dataTestFR.get(x));
                        rawTFIDF.smallerTFIDF(testList, testValue, IDFList);
                        //long nCosStartTime = System.nanoTime();
                        result = knnCosine.KNN(test, testList, testValue, z, trainListR, false);
                        if (result.equals("Problem Discovery")) {
                            discoveryResultRequest++;
                        } else if (result.equals("Information Seeking")) {
                            seekingResultRequest++;
                        } else if (result.equals("Information Giving")) {
                            givingResultRequest++;
                        } else if (result.equals("Feature Request")) {
                            requestResultRequest++;
                        }
                        //long nCosEndTime = System.nanoTime();
                        //long nCosDuration = (nCosEndTime - nCosStartTime);
                    }
                    Log.d("endResult", "=======================================================================");
                    Log.d("endResult", "K = " + Integer.toString(z));
                    Log.d("endResult", "Value Problem Discovery = " + discoveryResultDiscovery + " / " + seekingResultDiscovery + " / " + givingResultDiscovery + " / " + requestResultDiscovery);
                    discoveryResultDiscovery = (discoveryResultDiscovery * 10) / 3;
                    Log.d("endResult", "Accuracy Problem Discovery = " + Integer.toString(discoveryResultDiscovery) + "%");
                    Log.d("endResult", "Value Information Seeking = " + discoveryResultSeeking + " / " + seekingResultSeeking + " / " + givingResultSeeking + " / " + requestResultSeeking);
                    seekingResultSeeking = (seekingResultSeeking * 10) / 3;
                    Log.d("endResult", "Accuracy Information Seeking = " + Integer.toString(seekingResultSeeking) + "%");
                    Log.d("endResult", "Value Information Giving = " + discoveryResultGiving + " / " + seekingResultGiving + " / " + givingResultGiving + " / " + requestResultGiving);
                    givingResultGiving = (givingResultGiving * 10) / 3;
                    Log.d("endResult", "Accuracy Information Giving = " + Integer.toString(givingResultGiving) + "%");
                    Log.d("endResult", "Value Feature Request = " + discoveryResultRequest + " / " + seekingResultRequest + " / " + givingResultRequest + " / " + requestResultRequest);
                    requestResultRequest = (requestResultRequest * 10) / 3;
                    Log.d("endResult", "Accuracy Feature Request = " + Integer.toString(requestResultRequest) + "%");
                    Log.d("endResult", "Accuracy Total = " + Integer.toString((discoveryResultDiscovery + seekingResultSeeking + givingResultGiving + requestResultRequest) / 4) + "%");

                }
                /*Log.d("list", "Read Duration : " + Long.toString(readDuration));
                Log.d("list", "Norm TFIDF Duration : " + Long.toString(normDuration));
                Log.d("list", "Raw TFIDF Duration : " + Long.toString(rawDuration));
                Log.d("list", "Raw Euc Duration : " + Long.toString(rEucDuration));
                Log.d("list", "Raw Cosine Duration : " + Long.toString(rCosDuration));
                Log.d("list", "Norm Euc Duration : " + Long.toString(nEucDuration));
                Log.d("list", "Norm Cosine Duration : " + Long.toString(nCosDuration));*/
            }
        });

        btnRawC = (Button) findViewById(R.id.btnRawC);
        btnRawC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int k = 20;
                Log.d("endResult", "=======================================================================");
                Log.d("endResult", "Raw TF-IDF + KNN Cosine");
                testList.clear();
                testValue.clear();
                brokeLine(testList, test);
                rawTFIDF.smallerTFIDF(testList, testValue, IDFList);
                result = knnCosine.KNN(test, testList, testValue, k, trainListR, false);

                Log.d("endResult", "=======================================================================");
                Log.d("endResult", "Raw TF-IDF + KNN Euclidean");
                testList.clear();
                testValue.clear();
                brokeLine(testList, test);
                rawTFIDF.smallerTFIDF(testList, testValue, IDFList);
                result = knnEuclidean.KNN(test, testList, testValue, k, trainListR, false);

                Log.d("endResult", "=======================================================================");
                Log.d("endResult", "Norm TF-IDF + KNN Cosine");
                testList.clear();
                testValue.clear();
                brokeLine(testList, test);
                normTFIDF.smallerTFIDF(testList, testValue, IDFList);
                result = knnCosine.KNN(test, testList, testValue, k, trainListN, false);

                Log.d("endResult", "=======================================================================");
                Log.d("endResult", "Norm TF-IDF + KNN Euclidean");
                testList.clear();
                testValue.clear();
                brokeLine(testList, test);
                normTFIDF.smallerTFIDF(testList, testValue, IDFList);
                result = knnEuclidean.KNN(test, testList, testValue, k, trainListN, false);
            }




                /*
                for (int x = 0; x < 30; x++){
                    Log.d("list", "Line " + Integer.toString(x + 1) + " : " + dataTest2.get(x));
                    testList.clear();
                    testValue.clear();
                    brokeLine(testList, dataTest2.get(x));
                    normTFIDF.smallerTFIDF(testList, testValue, IDFList2);
                    knnEuclidean.KNN(dataTest2.get(x), testList, testValue, 15, trainListR2, true);
                }*/
            // }
        });

        btnNormE = (Button) findViewById(R.id.btnNormE);
        btnNormE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*testList.clear();
                testValue.clear();
                brokeLine(testList, test);
                normTFIDF.smallerTFIDF(testList, testValue, IDFList);
                knnEuclidean.KNN(test, testList, testValue, 15, trainListN, false);*/

                int ori = POriginal.size();
                int oriInside;
                int stop = PStopword.size();
                String compare;
                String stopCompare;


                for (int x = 0; x < ori; x++) {
                    List<String> PResultInside = new ArrayList<String>();
                    PResultInside.clear();
                    oriInside = POriginal.get(x).size();
                    for (int z = 0; z < oriInside; z++) {
                        compare = POriginal.get(x).get(z);
                        if (PStopword.contains(compare) == false) {
                            PResultInside.add(compare);
                        }
                    }
                    PResult.add(PResultInside);
                    if (x == 0) {
                        Log.d("list", "Size 0 = " + Integer.toString(PResult.get(0).size()));
                    } else if (x == 1) {
                        Log.d("list", "Size 0 = " + Integer.toString(PResult.get(0).size()));
                        Log.d("list", "Size 1 = " + Integer.toString(PResult.get(1).size()));
                    }
                    //Log.d("list", "Size = " + Integer.toString(PResult.size()));
                }

                Log.d("list", "Size = " + Integer.toString(PResult.get(0).size()));
                Log.d("list", "Size = " + Integer.toString(PResult.get(1).size()));
                Log.d("list", "Size = " + Integer.toString(PResult.get(2).size()));


                int ori2 = PResult.size();
                int oriInside2;

                for (int a = 0; a < ori2; a++) {
                    oriInside2 = PResult.get(a).size();
                    for (int s = 0; s < oriInside2; s++) {
                        result += PResult.get(a).get(s) + " ";
                    }
                    Log.d("list", "Result = " + result);
                    result = "";
                }

                Log.d("list", "Result = " + result);

            }
        });

        btnNormC = (Button) findViewById(R.id.btnNormC);
        btnNormC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Integer> kFold = new ArrayList<Integer>();
                kFold.add(21);
                kFold.add(25);
                kFold.add(31);

                //for (int z = 5; z <= 33; z += 2) {
                for (int c = 0; c < 3; c++) {
                    int z = kFold.get(c);

                    Log.d("endResult", "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    Log.d("endResult", "K = " + Integer.toString(z));
                    Log.d("endResult", "=======================================================================");
                    Log.d("endResult", "Raw Euclidean");
                    discoveryResultDiscovery = 0;
                    seekingResultDiscovery = 0;
                    givingResultDiscovery = 0;
                    requestResultDiscovery = 0;
                    discoveryResultSeeking = 0;
                    seekingResultSeeking = 0;
                    givingResultSeeking = 0;
                    requestResultSeeking = 0;
                    discoveryResultGiving = 0;
                    seekingResultGiving = 0;
                    givingResultGiving = 0;
                    requestResultGiving = 0;
                    discoveryResultRequest = 0;
                    seekingResultRequest = 0;
                    givingResultRequest = 0;
                    requestResultRequest = 0;
                    for (int x = 0; x < 10; x++) {
                        Log.d("list", "Raw Euclidean");
                        testList.clear();
                        testValue.clear();
                        brokeLine(testList, dataTestPD.get(x));
                        rawTFIDF.smallerTFIDF(testList, testValue, IDFList);
                        //long rEucStartTime = System.nanoTime();
                        result = knnEuclidean.KNN(test, testList, testValue, z, trainListR, false);
                        if (result.equals("Problem Discovery")) {
                            discoveryResultDiscovery++;
                        } else if (result.equals("Information Seeking")) {
                            seekingResultDiscovery++;
                        } else if (result.equals("Information Giving")) {
                            givingResultDiscovery++;
                        } else if (result.equals("Feature Request")) {
                            requestResultDiscovery++;
                        }
                        //long rEucEndTime = System.nanoTime();
                        //long rEucDuration = (rEucEndTime - rEucStartTime);

                        Log.d("list", "Raw Cosine");
                        testList.clear();
                        testValue.clear();
                        brokeLine(testList, dataTestIS.get(x));
                        rawTFIDF.smallerTFIDF(testList, testValue, IDFList);
                        //long rCosStartTime = System.nanoTime();
                        result = knnEuclidean.KNN(test, testList, testValue, z, trainListR, false);
                        if (result.equals("Problem Discovery")) {
                            discoveryResultSeeking++;
                        } else if (result.equals("Information Seeking")) {
                            seekingResultSeeking++;
                        } else if (result.equals("Information Giving")) {
                            givingResultSeeking++;
                        } else if (result.equals("Feature Request")) {
                            requestResultSeeking++;
                        }
                        //long rCosEndTime = System.nanoTime();
                        // long rCosDuration = (rCosEndTime - rCosStartTime);

                        Log.d("list", "Norm Euclidean");
                        testList.clear();
                        testValue.clear();
                        brokeLine(testList, dataTestIG.get(x));
                        rawTFIDF.smallerTFIDF(testList, testValue, IDFList);
                        //long nEucStartTime = System.nanoTime();
                        result = knnEuclidean.KNN(test, testList, testValue, z, trainListR, false);
                        if (result.equals("Problem Discovery")) {
                            discoveryResultGiving++;
                        } else if (result.equals("Information Seeking")) {
                            seekingResultGiving++;
                        } else if (result.equals("Information Giving")) {
                            givingResultGiving++;
                        } else if (result.equals("Feature Request")) {
                            requestResultGiving++;
                        }
                        //long nEucEndTime = System.nanoTime();
                        //long nEucDuration = (nEucEndTime - nEucStartTime);

                        Log.d("list", "Norm Cosine");
                        testList.clear();
                        testValue.clear();
                        brokeLine(testList, dataTestFR.get(x));
                        rawTFIDF.smallerTFIDF(testList, testValue, IDFList);
                        //long nCosStartTime = System.nanoTime();
                        result = knnEuclidean.KNN(test, testList, testValue, z, trainListR, false);
                        if (result.equals("Problem Discovery")) {
                            discoveryResultRequest++;
                        } else if (result.equals("Information Seeking")) {
                            seekingResultRequest++;
                        } else if (result.equals("Information Giving")) {
                            givingResultRequest++;
                        } else if (result.equals("Feature Request")) {
                            requestResultRequest++;
                        }
                        //long nCosEndTime = System.nanoTime();
                        //long nCosDuration = (nCosEndTime - nCosStartTime);
                    }
                    Log.d("endResult", "=======================================================================");
                    Log.d("endResult", "Value Problem Discovery = " + discoveryResultDiscovery + " / " + seekingResultDiscovery + " / " + givingResultDiscovery + " / " + requestResultDiscovery);
                    discoveryResultDiscovery = (discoveryResultDiscovery * 10);
                    Log.d("endResult", "Accuracy Problem Discovery = " + Integer.toString(discoveryResultDiscovery) + "%");
                    Log.d("endResult", "Value Information Seeking = " + discoveryResultSeeking + " / " + seekingResultSeeking + " / " + givingResultSeeking + " / " + requestResultSeeking);
                    seekingResultSeeking = (seekingResultSeeking * 10);
                    Log.d("endResult", "Accuracy Information Seeking = " + Integer.toString(seekingResultSeeking) + "%");
                    Log.d("endResult", "Value Information Giving = " + discoveryResultGiving + " / " + seekingResultGiving + " / " + givingResultGiving + " / " + requestResultGiving);
                    givingResultGiving = (givingResultGiving * 10);
                    Log.d("endResult", "Accuracy Information Giving = " + Integer.toString(givingResultGiving) + "%");
                    Log.d("endResult", "Value Feature Request = " + discoveryResultRequest + " / " + seekingResultRequest + " / " + givingResultRequest + " / " + requestResultRequest);
                    requestResultRequest = (requestResultRequest * 10);
                    Log.d("endResult", "Accuracy Feature Request = " + Integer.toString(requestResultRequest) + "%");
                    Log.d("endResult", "Accuracy Total = " + Integer.toString((discoveryResultDiscovery + seekingResultSeeking + givingResultGiving + requestResultRequest) / 4) + "%");


                    //////////////
                    Log.d("endResult", "=======================================================================");
                    Log.d("endResult", "Raw Cosine");
                    discoveryResultDiscovery = 0;
                    seekingResultDiscovery = 0;
                    givingResultDiscovery = 0;
                    requestResultDiscovery = 0;
                    discoveryResultSeeking = 0;
                    seekingResultSeeking = 0;
                    givingResultSeeking = 0;
                    requestResultSeeking = 0;
                    discoveryResultGiving = 0;
                    seekingResultGiving = 0;
                    givingResultGiving = 0;
                    requestResultGiving = 0;
                    discoveryResultRequest = 0;
                    seekingResultRequest = 0;
                    givingResultRequest = 0;
                    requestResultRequest = 0;
                    for (int x = 0; x < 10; x++) {
                        Log.d("list", "Raw Euclidean");
                        testList.clear();
                        testValue.clear();
                        brokeLine(testList, dataTestPD.get(x));
                        rawTFIDF.smallerTFIDF(testList, testValue, IDFList);
                        //long rEucStartTime = System.nanoTime();
                        result = knnCosine.KNN(test, testList, testValue, z, trainListR, false);
                        if (result.equals("Problem Discovery")) {
                            discoveryResultDiscovery++;
                        } else if (result.equals("Information Seeking")) {
                            seekingResultDiscovery++;
                        } else if (result.equals("Information Giving")) {
                            givingResultDiscovery++;
                        } else if (result.equals("Feature Request")) {
                            requestResultDiscovery++;
                        }
                        //long rEucEndTime = System.nanoTime();
                        //long rEucDuration = (rEucEndTime - rEucStartTime);

                        Log.d("list", "Raw Cosine");
                        testList.clear();
                        testValue.clear();
                        brokeLine(testList, dataTestIS.get(x));
                        rawTFIDF.smallerTFIDF(testList, testValue, IDFList);
                        //long rCosStartTime = System.nanoTime();
                        result = knnCosine.KNN(test, testList, testValue, z, trainListR, false);
                        if (result.equals("Problem Discovery")) {
                            discoveryResultSeeking++;
                        } else if (result.equals("Information Seeking")) {
                            seekingResultSeeking++;
                        } else if (result.equals("Information Giving")) {
                            givingResultSeeking++;
                        } else if (result.equals("Feature Request")) {
                            requestResultSeeking++;
                        }
                        //long rCosEndTime = System.nanoTime();
                        // long rCosDuration = (rCosEndTime - rCosStartTime);

                        Log.d("list", "Norm Euclidean");
                        testList.clear();
                        testValue.clear();
                        brokeLine(testList, dataTestIG.get(x));
                        rawTFIDF.smallerTFIDF(testList, testValue, IDFList);
                        //long nEucStartTime = System.nanoTime();
                        result = knnCosine.KNN(test, testList, testValue, z, trainListR, false);
                        if (result.equals("Problem Discovery")) {
                            discoveryResultGiving++;
                        } else if (result.equals("Information Seeking")) {
                            seekingResultGiving++;
                        } else if (result.equals("Information Giving")) {
                            givingResultGiving++;
                        } else if (result.equals("Feature Request")) {
                            requestResultGiving++;
                        }
                        //long nEucEndTime = System.nanoTime();
                        //long nEucDuration = (nEucEndTime - nEucStartTime);

                        Log.d("list", "Norm Cosine");
                        testList.clear();
                        testValue.clear();
                        brokeLine(testList, dataTestFR.get(x));
                        rawTFIDF.smallerTFIDF(testList, testValue, IDFList);
                        //long nCosStartTime = System.nanoTime();
                        result = knnCosine.KNN(test, testList, testValue, z, trainListR, false);
                        if (result.equals("Problem Discovery")) {
                            discoveryResultRequest++;
                        } else if (result.equals("Information Seeking")) {
                            seekingResultRequest++;
                        } else if (result.equals("Information Giving")) {
                            givingResultRequest++;
                        } else if (result.equals("Feature Request")) {
                            requestResultRequest++;
                        }
                        //long nCosEndTime = System.nanoTime();
                        //long nCosDuration = (nCosEndTime - nCosStartTime);
                    }
                    Log.d("endResult", "=======================================================================");
                    Log.d("endResult", "Value Problem Discovery = " + discoveryResultDiscovery + " / " + seekingResultDiscovery + " / " + givingResultDiscovery + " / " + requestResultDiscovery);
                    discoveryResultDiscovery = (discoveryResultDiscovery * 10);
                    Log.d("endResult", "Accuracy Problem Discovery = " + Integer.toString(discoveryResultDiscovery) + "%");
                    Log.d("endResult", "Value Information Seeking = " + discoveryResultSeeking + " / " + seekingResultSeeking + " / " + givingResultSeeking + " / " + requestResultSeeking);
                    seekingResultSeeking = (seekingResultSeeking * 10);
                    Log.d("endResult", "Accuracy Information Seeking = " + Integer.toString(seekingResultSeeking) + "%");
                    Log.d("endResult", "Value Information Giving = " + discoveryResultGiving + " / " + seekingResultGiving + " / " + givingResultGiving + " / " + requestResultGiving);
                    givingResultGiving = (givingResultGiving * 10);
                    Log.d("endResult", "Accuracy Information Giving = " + Integer.toString(givingResultGiving) + "%");
                    Log.d("endResult", "Value Feature Request = " + discoveryResultRequest + " / " + seekingResultRequest + " / " + givingResultRequest + " / " + requestResultRequest);
                    requestResultRequest = (requestResultRequest * 10);
                    Log.d("endResult", "Accuracy Feature Request = " + Integer.toString(requestResultRequest) + "%");
                    Log.d("endResult", "Accuracy Total = " + Integer.toString((discoveryResultDiscovery + seekingResultSeeking + givingResultGiving + requestResultRequest) / 4) + "%");


                    ////////////////////
                    Log.d("endResult", "=======================================================================");
                    Log.d("endResult", "Norm Euclidean");
                    discoveryResultDiscovery = 0;
                    seekingResultDiscovery = 0;
                    givingResultDiscovery = 0;
                    requestResultDiscovery = 0;
                    discoveryResultSeeking = 0;
                    seekingResultSeeking = 0;
                    givingResultSeeking = 0;
                    requestResultSeeking = 0;
                    discoveryResultGiving = 0;
                    seekingResultGiving = 0;
                    givingResultGiving = 0;
                    requestResultGiving = 0;
                    discoveryResultRequest = 0;
                    seekingResultRequest = 0;
                    givingResultRequest = 0;
                    requestResultRequest = 0;
                    for (int x = 0; x < 10; x++) {
                        Log.d("list", "Raw Euclidean");
                        testList.clear();
                        testValue.clear();
                        brokeLine(testList, dataTestPD.get(x));
                        normTFIDF.smallerTFIDF(testList, testValue, IDFList);
                        //long rEucStartTime = System.nanoTime();
                        result = knnEuclidean.KNN(test, testList, testValue, z, trainListN, false);
                        if (result.equals("Problem Discovery")) {
                            discoveryResultDiscovery++;
                        } else if (result.equals("Information Seeking")) {
                            seekingResultDiscovery++;
                        } else if (result.equals("Information Giving")) {
                            givingResultDiscovery++;
                        } else if (result.equals("Feature Request")) {
                            requestResultDiscovery++;
                        }
                        //long rEucEndTime = System.nanoTime();
                        //long rEucDuration = (rEucEndTime - rEucStartTime);

                        Log.d("list", "Raw Cosine");
                        testList.clear();
                        testValue.clear();
                        brokeLine(testList, dataTestIS.get(x));
                        normTFIDF.smallerTFIDF(testList, testValue, IDFList);
                        //long rCosStartTime = System.nanoTime();
                        result = knnEuclidean.KNN(test, testList, testValue, z, trainListN, false);
                        if (result.equals("Problem Discovery")) {
                            discoveryResultSeeking++;
                        } else if (result.equals("Information Seeking")) {
                            seekingResultSeeking++;
                        } else if (result.equals("Information Giving")) {
                            givingResultSeeking++;
                        } else if (result.equals("Feature Request")) {
                            requestResultSeeking++;
                        }
                        //long rCosEndTime = System.nanoTime();
                        // long rCosDuration = (rCosEndTime - rCosStartTime);

                        Log.d("list", "Norm Euclidean");
                        testList.clear();
                        testValue.clear();
                        brokeLine(testList, dataTestIG.get(x));
                        normTFIDF.smallerTFIDF(testList, testValue, IDFList);
                        //long nEucStartTime = System.nanoTime();
                        result = knnEuclidean.KNN(test, testList, testValue, z, trainListN, false);
                        if (result.equals("Problem Discovery")) {
                            discoveryResultGiving++;
                        } else if (result.equals("Information Seeking")) {
                            seekingResultGiving++;
                        } else if (result.equals("Information Giving")) {
                            givingResultGiving++;
                        } else if (result.equals("Feature Request")) {
                            requestResultGiving++;
                        }
                        //long nEucEndTime = System.nanoTime();
                        //long nEucDuration = (nEucEndTime - nEucStartTime);

                        Log.d("list", "Norm Cosine");
                        testList.clear();
                        testValue.clear();
                        brokeLine(testList, dataTestFR.get(x));
                        normTFIDF.smallerTFIDF(testList, testValue, IDFList);
                        //long nCosStartTime = System.nanoTime();
                        result = knnEuclidean.KNN(test, testList, testValue, z, trainListN, false);
                        if (result.equals("Problem Discovery")) {
                            discoveryResultRequest++;
                        } else if (result.equals("Information Seeking")) {
                            seekingResultRequest++;
                        } else if (result.equals("Information Giving")) {
                            givingResultRequest++;
                        } else if (result.equals("Feature Request")) {
                            requestResultRequest++;
                        }
                        //long nCosEndTime = System.nanoTime();
                        //long nCosDuration = (nCosEndTime - nCosStartTime);
                    }
                    Log.d("endResult", "=======================================================================");
                    Log.d("endResult", "Value Problem Discovery = " + discoveryResultDiscovery + " / " + seekingResultDiscovery + " / " + givingResultDiscovery + " / " + requestResultDiscovery);
                    discoveryResultDiscovery = (discoveryResultDiscovery * 10);
                    Log.d("endResult", "Accuracy Problem Discovery = " + Integer.toString(discoveryResultDiscovery) + "%");
                    Log.d("endResult", "Value Information Seeking = " + discoveryResultSeeking + " / " + seekingResultSeeking + " / " + givingResultSeeking + " / " + requestResultSeeking);
                    seekingResultSeeking = (seekingResultSeeking * 10);
                    Log.d("endResult", "Accuracy Information Seeking = " + Integer.toString(seekingResultSeeking) + "%");
                    Log.d("endResult", "Value Information Giving = " + discoveryResultGiving + " / " + seekingResultGiving + " / " + givingResultGiving + " / " + requestResultGiving);
                    givingResultGiving = (givingResultGiving * 10);
                    Log.d("endResult", "Accuracy Information Giving = " + Integer.toString(givingResultGiving) + "%");
                    Log.d("endResult", "Value Feature Request = " + discoveryResultRequest + " / " + seekingResultRequest + " / " + givingResultRequest + " / " + requestResultRequest);
                    requestResultRequest = (requestResultRequest * 10);
                    Log.d("endResult", "Accuracy Feature Request = " + Integer.toString(requestResultRequest) + "%");
                    Log.d("endResult", "Accuracy Total = " + Integer.toString((discoveryResultDiscovery + seekingResultSeeking + givingResultGiving + requestResultRequest) / 4) + "%");


                    /////////////
                    Log.d("endResult", "=======================================================================");
                    Log.d("endResult", "Norm Cosine");
                    discoveryResultDiscovery = 0;
                    seekingResultDiscovery = 0;
                    givingResultDiscovery = 0;
                    requestResultDiscovery = 0;
                    discoveryResultSeeking = 0;
                    seekingResultSeeking = 0;
                    givingResultSeeking = 0;
                    requestResultSeeking = 0;
                    discoveryResultGiving = 0;
                    seekingResultGiving = 0;
                    givingResultGiving = 0;
                    requestResultGiving = 0;
                    discoveryResultRequest = 0;
                    seekingResultRequest = 0;
                    givingResultRequest = 0;
                    requestResultRequest = 0;
                    for (int x = 0; x < 10; x++) {
                        Log.d("list", "Raw Euclidean");
                        testList.clear();
                        testValue.clear();
                        brokeLine(testList, dataTestPD.get(x));
                        normTFIDF.smallerTFIDF(testList, testValue, IDFList);
                        //long rEucStartTime = System.nanoTime();
                        result = knnCosine.KNN(test, testList, testValue, z, trainListN, false);
                        if (result.equals("Problem Discovery")) {
                            discoveryResultDiscovery++;
                        } else if (result.equals("Information Seeking")) {
                            seekingResultDiscovery++;
                        } else if (result.equals("Information Giving")) {
                            givingResultDiscovery++;
                        } else if (result.equals("Feature Request")) {
                            requestResultDiscovery++;
                        }
                        //long rEucEndTime = System.nanoTime();
                        //long rEucDuration = (rEucEndTime - rEucStartTime);

                        Log.d("list", "Raw Cosine");
                        testList.clear();
                        testValue.clear();
                        brokeLine(testList, dataTestIS.get(x));
                        normTFIDF.smallerTFIDF(testList, testValue, IDFList);
                        //long rCosStartTime = System.nanoTime();
                        result = knnCosine.KNN(test, testList, testValue, z, trainListN, false);
                        if (result.equals("Problem Discovery")) {
                            discoveryResultSeeking++;
                        } else if (result.equals("Information Seeking")) {
                            seekingResultSeeking++;
                        } else if (result.equals("Information Giving")) {
                            givingResultSeeking++;
                        } else if (result.equals("Feature Request")) {
                            requestResultSeeking++;
                        }
                        //long rCosEndTime = System.nanoTime();
                        // long rCosDuration = (rCosEndTime - rCosStartTime);

                        Log.d("list", "Norm Euclidean");
                        testList.clear();
                        testValue.clear();
                        brokeLine(testList, dataTestIG.get(x));
                        normTFIDF.smallerTFIDF(testList, testValue, IDFList);
                        //long nEucStartTime = System.nanoTime();
                        result = knnCosine.KNN(test, testList, testValue, z, trainListN, false);
                        if (result.equals("Problem Discovery")) {
                            discoveryResultGiving++;
                        } else if (result.equals("Information Seeking")) {
                            seekingResultGiving++;
                        } else if (result.equals("Information Giving")) {
                            givingResultGiving++;
                        } else if (result.equals("Feature Request")) {
                            requestResultGiving++;
                        }
                        //long nEucEndTime = System.nanoTime();
                        //long nEucDuration = (nEucEndTime - nEucStartTime);

                        Log.d("list", "Norm Cosine");
                        testList.clear();
                        testValue.clear();
                        brokeLine(testList, dataTestFR.get(x));
                        normTFIDF.smallerTFIDF(testList, testValue, IDFList);
                        //long nCosStartTime = System.nanoTime();
                        result = knnCosine.KNN(test, testList, testValue, z, trainListN, false);
                        if (result.equals("Problem Discovery")) {
                            discoveryResultRequest++;
                        } else if (result.equals("Information Seeking")) {
                            seekingResultRequest++;
                        } else if (result.equals("Information Giving")) {
                            givingResultRequest++;
                        } else if (result.equals("Feature Request")) {
                            requestResultRequest++;
                        }
                        //long nCosEndTime = System.nanoTime();
                        //long nCosDuration = (nCosEndTime - nCosStartTime);
                    }
                    Log.d("endResult", "=======================================================================");
                    Log.d("endResult", "Value Problem Discovery = " + discoveryResultDiscovery + " / " + seekingResultDiscovery + " / " + givingResultDiscovery + " / " + requestResultDiscovery);
                    discoveryResultDiscovery = (discoveryResultDiscovery * 10);
                    Log.d("endResult", "Accuracy Problem Discovery = " + Integer.toString(discoveryResultDiscovery) + "%");
                    Log.d("endResult", "Value Information Seeking = " + discoveryResultSeeking + " / " + seekingResultSeeking + " / " + givingResultSeeking + " / " + requestResultSeeking);
                    seekingResultSeeking = (seekingResultSeeking * 10);
                    Log.d("endResult", "Accuracy Information Seeking = " + Integer.toString(seekingResultSeeking) + "%");
                    Log.d("endResult", "Value Information Giving = " + discoveryResultGiving + " / " + seekingResultGiving + " / " + givingResultGiving + " / " + requestResultGiving);
                    givingResultGiving = (givingResultGiving * 10);
                    Log.d("endResult", "Accuracy Information Giving = " + Integer.toString(givingResultGiving) + "%");
                    Log.d("endResult", "Value Feature Request = " + discoveryResultRequest + " / " + seekingResultRequest + " / " + givingResultRequest + " / " + requestResultRequest);
                    requestResultRequest = (requestResultRequest * 10);
                    Log.d("endResult", "Accuracy Feature Request = " + Integer.toString(requestResultRequest) + "%");
                    Log.d("endResult", "Accuracy Total = " + Integer.toString((discoveryResultDiscovery + seekingResultSeeking + givingResultGiving + requestResultRequest) / 4) + "%");

                }
            }
        });

    }

    public List brokeLine(List<String> parentList, String text) {
        text = text.trim();
        String token[] = text.split("[\\p{Punct}\\s]+");
        for (String s : token) {
            parentList.add(s);
        }
        return parentList;
    }

// Method to read texts

    public List readAsset(List<List<String>> parentList, String fileName) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open(fileName)));

            // Reading, usually loop until end of file reading
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> childList = new ArrayList<String>();
                line = line.trim();
                String token[] = line.split("[\\p{Punct}\\s]+");
                for (String s : token) {
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
        return parentList;
    }

    public List readAssetnoLine(List<String> parentList, String fileName) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open(fileName)));

            // Reading, usually loop until end of file reading
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> childList = new ArrayList<String>();
                line = line.trim();
                parentList.add(line);
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
        return parentList;
    }

    public List readAssetStopword(List<String> parentList, String fileName) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open(fileName)));

            // Reading, usually loop until end of file reading
            String line;
            while ((line = reader.readLine()) != null) {
                parentList.add(line);
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
        return parentList;
    }

    // Printing to Log
    public void printResult(List<List<String>> parentList) {
        for (int i = 0; i < parentList.size(); i++)
            Log.d("list", parentList.get(i).toString());
    }
}
