package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// الواجهة الأولى: استمارة تسجيل المسافر
public class MainActivity extends AppCompatActivity {

    // تعريف عناصر الواجهة
    private EditText etNom, etNaissance, etDestination;
    private RadioGroup rgMotif;
    private Button btnInspecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ربط هذا الكلاس بملف التصميم activity_main.xml
        setContentView(R.layout.activity_main);

        // ربط كل متغير بالعنصر الموافق له في التصميم عبر الـ id
        etNom = findViewById(R.id.et_nom_ib);
        etNaissance = findViewById(R.id.et_naissance_ib);
        etDestination = findViewById(R.id.et_destination_ib);
        rgMotif = findViewById(R.id.rg_motif_ib);
        btnInspecter = findViewById(R.id.btn_inspecter_ib);

        // عند الضغط على الزر نستدعي دالة التحقق والإرسال
        btnInspecter.setOnClickListener(v -> inspecterProfil());
    }

    // دالة: تتحقق من البيانات ثم تفتح الواجهة الثانية
    private void inspecterProfil() {
        // قراءة النصوص وحذف الفراغات الزائدة
        String nom = etNom.getText().toString().trim();
        String naissanceTxt = etNaissance.getText().toString().trim();
        String destinationTxt = etDestination.getText().toString().trim();

        // التحقق من أن الاسم غير فارغ
        if (nom.isEmpty()) {
            Toast.makeText(this, "Veuillez saisir le nom", Toast.LENGTH_SHORT).show();
            return;
        }

        // التحقق من أن السنتين غير فارغتين
        if (naissanceTxt.isEmpty() || destinationTxt.isEmpty()) {
            Toast.makeText(this, "Veuillez saisir les deux années", Toast.LENGTH_SHORT).show();
            return;
        }

        // التحقق من أنه تم اختيار سبب السفر (-1 تعني لا شيء مختار)
        int idChoisi = rgMotif.getCheckedRadioButtonId();
        if (idChoisi == -1) {
            Toast.makeText(this, "Veuillez choisir un motif", Toast.LENGTH_SHORT).show();
            return;
        }

        // تحويل النصوص إلى أعداد صحيحة
        int naissance = Integer.parseInt(naissanceTxt);
        int destination = Integer.parseInt(destinationTxt);

        // التحقق من أن سنة الوجهة أكبر من سنة الميلاد
        if (destination <= naissance) {
            // عرض رسالة الخطأ مباشرة داخل حقل سنة الوجهة
            etDestination.setError("Doit être supérieure à l'année de naissance");
            etDestination.requestFocus();
            return;
        }

        // جلب نص الاختيار (مثلا: Tourisme)
        RadioButton rbChoisi = findViewById(idChoisi);
        String motif = rbChoisi.getText().toString();

        // إنشاء Intent صريح لفتح الواجهة الثانية
        Intent intent = new Intent(this, VerificationActivity.class);

        // إرسال البيانات: المفاتيح تُبنى بدوال ثابتة من الكلاس Cles (ليست مكتوبة يدويا)
        intent.putExtra(Cles.cleNom(Cles.MATRICULE), nom);
        intent.putExtra(Cles.cleNaissance(Cles.MATRICULE), naissance);
        intent.putExtra(Cles.cleDestination(Cles.MATRICULE), destination);
        intent.putExtra(Cles.cleMotif(Cles.MATRICULE), motif);

        // فتح الواجهة الثانية
        startActivity(intent);
    }
}