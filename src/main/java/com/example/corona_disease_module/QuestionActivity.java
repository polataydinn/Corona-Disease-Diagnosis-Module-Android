package com.example.corona_disease_module;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity{

    String[] hastaliklar = {"Korona","Soğuk Algınlığı","Grip","Alerji"};
    AdView adView,adView1;
    InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        adView = findViewById(R.id.bannerAd);
        MobileAds.initialize(this,"ca-app-pub-2195346132205819~1296471857");

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-2195346132205819/6026054359");
        interstitialAd.loadAd(new AdRequest.Builder().build());

        Spinner spinner = findViewById(R.id.spinner);
        Spinner spinner1 = findViewById(R.id.spinner1);
        Spinner spinner2 = findViewById(R.id.spinner2);
        Spinner spinner3 = findViewById(R.id.spinner3);
        Spinner spinner4 = findViewById(R.id.spinner4);
        Spinner spinner5 = findViewById(R.id.spinner5);

        Button continueButton = findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CONST.atesResult = spinner.getSelectedItemPosition();
                CONST.yorgunlukResult = spinner1.getSelectedItemPosition();
                CONST.kuruOksurukResult = spinner2.getSelectedItemPosition();
                CONST.solunuResult = spinner3.getSelectedItemPosition();
                CONST.oksurukResult = spinner4.getSelectedItemPosition();
                CONST.agriResult = spinner5.getSelectedItemPosition();
                setContentView(R.layout.activity_questions2);
                adView1 = findViewById(R.id.bannerAd1);
                adView1.loadAd(adRequest);
                Button resultButton = findViewById(R.id.getResultsButton);
                resultButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Spinner spinner6 = findViewById(R.id.spinner6);
                        Spinner spinner7 = findViewById(R.id.spinner7);
                        Spinner spinner8 = findViewById(R.id.spinner8);
                        Spinner spinner9 = findViewById(R.id.spinner9);
                        Spinner spinner10 = findViewById(R.id.spinner10);
                        Spinner spinner11 = findViewById(R.id.spinner11);
                        CONST.hapsirmaResult = spinner6.getSelectedItemPosition();
                        CONST.burunAkintiResult = spinner7.getSelectedItemPosition();
                        CONST.burunTikanikResult = spinner8.getSelectedItemPosition();
                        CONST.sulanmaResult = spinner9.getSelectedItemPosition();
                        CONST.bogazAgriResult = spinner10.getSelectedItemPosition();
                        CONST.ishalResult = spinner11.getSelectedItemPosition();
                        interstitialAd.show();
                        setContentView(R.layout.result_layout);
                        koronaTani();
                        sogukTani();
                        gripTani();
                        alerjiTani();
                        float[] hastalikOranlar = {CONST.koronaYuzde,CONST.sogukAlginligiYuzde,CONST.gripYuzde,CONST.alerjiYuzde};
                        pastaGoster(hastalikOranlar);

                    }
                });
            }
        });



    }

    public void koronaTani(){
        DiseaseRate.koronaTaniOrani(CONST.atesResult,
                CONST.yorgunlukResult,
                CONST.kuruOksurukResult,
                CONST.solunuResult,
                CONST.oksurukResult,
                CONST.agriResult,
                CONST.hapsirmaResult,
                CONST.burunAkintiResult,
                CONST.burunTikanikResult,
                CONST.sulanmaResult,
                CONST.bogazAgriResult,
                CONST.ishalResult);
        CONST.koronaYuzde = CONST.hastalikTaniOran/12;
        CONST.hastalikTaniOran = 0;
    }
    public  void sogukTani(){
        DiseaseRate.sogukAlginligiTaniOrani(CONST.atesResult,
                CONST.yorgunlukResult,
                CONST.kuruOksurukResult,
                CONST.solunuResult,
                CONST.oksurukResult,
                CONST.agriResult,
                CONST.hapsirmaResult,
                CONST.burunAkintiResult,
                CONST.burunTikanikResult,
                CONST.sulanmaResult,
                CONST.bogazAgriResult,
                CONST.ishalResult);
        CONST.sogukAlginligiYuzde = CONST.hastalikTaniOran/12;
        CONST.hastalikTaniOran = 0;
    }

    public void gripTani(){
        DiseaseRate.gripTaniOrani(CONST.atesResult,
                CONST.yorgunlukResult,
                CONST.kuruOksurukResult,
                CONST.solunuResult,
                CONST.oksurukResult,
                CONST.agriResult,
                CONST.hapsirmaResult,
                CONST.burunAkintiResult,
                CONST.burunTikanikResult,
                CONST.sulanmaResult,
                CONST.bogazAgriResult,
                CONST.ishalResult);
        CONST.gripYuzde = CONST.hastalikTaniOran/12;
        CONST.hastalikTaniOran = 0;
    }

    public void alerjiTani(){
        DiseaseRate.alerjiTaniOrani(CONST.atesResult,
                CONST.yorgunlukResult,
                CONST.kuruOksurukResult,
                CONST.solunuResult,
                CONST.oksurukResult,
                CONST.agriResult,
                CONST.hapsirmaResult,
                CONST.burunAkintiResult,
                CONST.burunTikanikResult,
                CONST.sulanmaResult,
                CONST.bogazAgriResult,
                CONST.ishalResult);
        CONST.alerjiYuzde = CONST.hastalikTaniOran/12;
        CONST.hastalikTaniOran = 0;
    }

    public void pastaGoster(float[] hastalikOran){
        AnyChartView anyChartView = findViewById(R.id.anyChart);
        Pie pie = AnyChart.pie();
        List<DataEntry> pastaVeri = new ArrayList<>();

        for(int i=0;i<hastaliklar.length;i++){
            pastaVeri.add(new ValueDataEntry(hastaliklar[i],hastalikOran[i]));
        }
        pie.data(pastaVeri);
        anyChartView.setChart(pie);
    }


}