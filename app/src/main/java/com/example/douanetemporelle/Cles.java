package com.example.douanetemporelle;

// hethi classe mta3 mosa3ada: tebni les cles mta3 putExtra bel matricule
// el cles mouch maktouba b yedd fel code, tetbna b methodes static
public class Cles {

    // el matricule: a5er 4 arkem men carte etudiant, n7ottouh mara wa7da bark
    public static final String MATRICULE = "8961";

    // cle mta3 esm el msafer, mithel: 8961_NOM_VOYAGEUR
    public static String cleNom(String matricule) {
        return matricule + "_NOM_VOYAGEUR";
    }

    // cle mta3 3am el wiladha
    public static String cleNaissance(String matricule) {
        return matricule + "_ANNEE_NAISSANCE";
    }

    // cle mta3 3am el wejha (win bech yosfor)
    public static String cleDestination(String matricule) {
        return matricule + "_ANNEE_DESTINATION";
    }

    // cle mta3 sabab el safar
    public static String cleMotif(String matricule) {
        return matricule + "_MOTIF_VOYAGE";
    }

    // cle mta3 el 9arar el nehayi (true = mwafa9a, false = rafdh)
    public static String cleVerdict(String matricule) {
        return matricule + "_VERDICT";
    }
}