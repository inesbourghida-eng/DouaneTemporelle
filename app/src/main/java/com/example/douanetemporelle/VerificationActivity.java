package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// hethi el wajiha ethnya: el passeport ezzmeni (scanner mta3 el paradoxe)
public class VerificationActivity extends AppCompatActivity {

    // n3arrfou 3anaser el wajiha
    private TextView tvNom, tvNaissance, tvDestination, tvAge, tvMotif, tvAlerte;
    private Button btnApprouver, btnRefuser;

    // nesta7a9ou esm el msafer baad bech nab3thouh lel wajiha ethalitha
    private String nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // nrabtou el classe m3a el design activity_verification.xml
        setContentView(R.layout.activity_verification);

        // nrabtou kol variable bel element mta3ha fel XML
        tvNom = findViewById(R.id.tv_nom_ib);
        tvNaissance = findViewById(R.id.tv_naissance_ib);
        tvDestination = findViewById(R.id.tv_destination_ib);
        tvAge = findViewById(R.id.tv_age_ib);
        tvMotif = findViewById(R.id.tv_motif_ib);
        tvAlerte = findViewById(R.id.tv_alerte_ib);
        btnApprouver = findViewById(R.id.btn_approuver_ib);
        btnRefuser = findViewById(R.id.btn_refuser_ib);

        // na9raw el donnees elli jeyin men el wajiha el loula b nafs el cles
        Intent intent = getIntent();
        nom = intent.getStringExtra(Cles.cleNom(Cles.MATRICULE));
        int naissance = intent.getIntExtra(Cles.cleNaissance(Cles.MATRICULE), 0);
        int destination = intent.getIntExtra(Cles.cleDestination(Cles.MATRICULE), 0);
        String motif = intent.getStringExtra(Cles.cleMotif(Cles.MATRICULE));

        // el 3omr ezzmeni = 3am el wejha - 3am el wiladha
        int age = destination - naissance;

        // n7ottou el ktiba fel passeport
        tvNom.setText("Nom : " + nom);
        tvNaissance.setText("Naissance : " + naissance);
        tvDestination.setText("Destination : " + destination);
        tvAge.setText("Âge temporel : " + age + " ans");
        tvMotif.setText("Motif : " + motif);

        // ken el motif "Modification du passe" w el far9 3adad awwali (premier):
        // nbatlou zerr el mwafa9a men awwel ma tet7al el wajiha
        if (motif != null && motif.startsWith("Modification") && estPremier(age)) {
            btnApprouver.setEnabled(false);
            tvAlerte.setVisibility(View.VISIBLE);
        }

        // zerr el mwafa9a yab3eth verdict = true, zerr el rafdh yab3eth false
        btnApprouver.setOnClickListener(v -> ouvrirVerdict(true));
        btnRefuser.setOnClickListener(v -> ouvrirVerdict(false));
    }

    // methode: tfaker ken el 3adad awwali (premier) wala le
    private boolean estPremier(int n) {
        // el 3adad el sghir men 2 mouch awwali
        if (n < 2) {
            return false;
        }
        // nbahthou 3la 9assem men 2 7atta jedhr el 3adad (i * i <= n)
        for (int i = 2; i * i <= n; i++) {
            // ken yet9asem, yab9a mouch awwali
            if (n % i == 0) {
                return false;
            }
        }
        // ma l9ina 7ata 9assem: el 3adad awwali
        return true;
    }

    // methode: tel7el el wajiha ethalitha w tab3eth el verdict w el esm
    private void ouvrirVerdict(boolean approuve) {
        Intent intent = new Intent(this, ResultActivity.class);
        // nab3thou el verdict (boolean) w esm el msafer b el cles mta3 Cles
        intent.putExtra(Cles.cleVerdict(Cles.MATRICULE), approuve);
        intent.putExtra(Cles.cleNom(Cles.MATRICULE), nom);
        startActivity(intent);
    }
}