package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {

    // تصريح العناصر الرسومية
    private TextView tvNom_ib, tvNaissance_ib, tvDestination_ib,
            tvAge_ib, tvMotif_ib, tvAlerte_ib;
    private Button btnApprouver_ib, btnRefuser_ib;

    // البيانات المستلمة من الشاشة الأولى
    private String nom;
    private String motif;
    private int naissance;
    private int destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        // ربط العناصر بملف XML عبر المعرفات
        tvNom_ib = findViewById(R.id.tvNom_ib);
        tvNaissance_ib = findViewById(R.id.tvNaissance_ib);
        tvDestination_ib = findViewById(R.id.tvDestination_ib);
        tvAge_ib = findViewById(R.id.tvAge_ib);
        tvMotif_ib = findViewById(R.id.tvMotif_ib);
        tvAlerte_ib = findViewById(R.id.tvAlerte_ib);
        btnApprouver_ib = findViewById(R.id.btnApprouver_ib);
        btnRefuser_ib = findViewById(R.id.btnRefuser_ib);

        // قراءة البيانات من الـ Intent: المفاتيح تحتاج رقم التسجيل كمعامل
        Intent intent = getIntent();
        nom = intent.getStringExtra(Cles.cleNom(Cles.MATRICULE));
        motif = intent.getStringExtra(Cles.cleMotif(Cles.MATRICULE));
        naissance = intent.getIntExtra(Cles.cleNaissance(Cles.MATRICULE), 0);
        destination = intent.getIntExtra(Cles.cleDestination(Cles.MATRICULE), 0);

        // حساب العمر الزمني = سنة الوجهة - سنة الولادة
        int ageTemporel = destination - naissance;

        // عرض المعلومات على شكل جواز سفر
        tvNom_ib.setText("Nom : " + nom);
        tvNaissance_ib.setText("Naissance : " + naissance);
        tvDestination_ib.setText("Destination : " + destination);
        tvAge_ib.setText("Age temporel : " + ageTemporel + " ans");
        tvMotif_ib.setText("Motif : " + motif);

        // القاعدة: إذا كان السبب "تعديل الماضي" والعمر الزمني عدد أولي
        // يتم تعطيل زر الموافقة مباشرة عند إنشاء الشاشة
        if ("Modification du passé".equals(motif) && estPremier(ageTemporel)) {
            btnApprouver_ib.setEnabled(false);
            tvAlerte_ib.setVisibility(View.VISIBLE);
        }

        // زر الموافقة: يرسل القرار true
        btnApprouver_ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ouvrirResultat(true);
            }
        });

        // زر الرفض: يرسل القرار false
        btnRefuser_ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ouvrirResultat(false);
            }
        });
    }

    // دالة تفتح شاشة النتيجة وترسل القرار (boolean) والاسم
    private void ouvrirResultat(boolean verdict) {
        Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);
        // إرسال القرار والاسم إلى شاشة النتيجة بنفس المفاتيح
        intent.putExtra(Cles.cleVerdict(Cles.MATRICULE), verdict);
        intent.putExtra(Cles.cleNom(Cles.MATRICULE), nom);
        startActivity(intent);
    }

    // دالة تتحقق هل العدد أولي (يقبل القسمة على 1 ونفسه فقط)
    private boolean estPremier(int n) {
        // الأعداد الأصغر من 2 ليست أولية
        if (n < 2) {
            return false;
        }
        // نجرب القواسم من 2 إلى الجذر التربيعي للعدد
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false; // وجدنا قاسماً، إذن ليس أولياً
            }
        }
        return true; // لم نجد أي قاسم، إذن هو أولي
    }
}