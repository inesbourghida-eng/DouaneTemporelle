package com.example.douanetemporelle;

// فئة مساعدة: تبني مفاتيح putExtra انطلاقا من رقم التسجيل
// المفاتيح غير مكتوبة مباشرة في الكود، بل تُبنى بواسطة دوال ثابتة (static)
public class Cles {

    // رقم التسجيل: آخر 4 أرقام من بطاقة الطالب (يُعرَّف مرة واحدة فقط)
    public static final String MATRICULE = "8961";

    // مفتاح اسم المسافر، مثال: "8961_NOM_VOYAGEUR"
    public static String cleNom(String matricule) {
        return matricule + "_NOM_VOYAGEUR";
    }

    // مفتاح سنة الميلاد
    public static String cleNaissance(String matricule) {
        return matricule + "_ANNEE_NAISSANCE";
    }

    // مفتاح سنة الوجهة
    public static String cleDestination(String matricule) {
        return matricule + "_ANNEE_DESTINATION";
    }

    // مفتاح سبب السفر
    public static String cleMotif(String matricule) {
        return matricule + "_MOTIF_VOYAGE";
    }

    // مفتاح القرار النهائي (true = موافقة، false = رفض)
    public static String cleVerdict(String matricule) {
        return matricule + "_VERDICT";
    }
}