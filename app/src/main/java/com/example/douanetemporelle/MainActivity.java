package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// hethi el wajiha el loula: formulaire mta3 tasjil el msafer
public class MainActivity extends AppCompatActivity {

    // n3arrfou 3anaser el wajiha (el champs w el zerr)
    private EditText etNom, etNaissance, etDestination;
    private RadioGroup rgMotif;
    private Button btnInspecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // nrabtou el classe hethi m3a el design activity_main.xml
        setContentView(R.layout.activity_main);

        // nrabtou kol variable bel element mta3ha fel XML 3an tari9 el id
        etNom = findViewById(R.id.et_nom_ib);
        etNaissance = findViewById(R.id.et_naissance_ib);
        etDestination = findViewById(R.id.et_destination_ib);
        rgMotif = findViewById(R.id.rg_motif_ib);
        btnInspecter = findViewById(R.id.btn_inspecter_ib);

        // ki el user yenzel 3al zerr, n3ayto lel methode inspecterProfil
        btnInspecter.setOnClickListener(v -> inspecterProfil());
    }

    // methode: tchecki el donnees w ba3d tel7el el wajiha ethnya
    private void inspecterProfil() {
        // na9raw el ktiba w nfas5ou el fraghat el zayda bel trim()
        String nom = etNom.getText().toString().trim();
        String naissanceTxt = etNaissance.getText().toString().trim();
        String destinationTxt = etDestination.getText().toString().trim();

        // ken el 2ism farragh: n3rdhou message w nwa9fou (return)
        if (nom.isEmpty()) {
            Toast.makeText(this, "Veuillez saisir le nom", Toast.LENGTH_SHORT).show();
            return;
        }

        // ken el 3amin (sna) farghin: message w nwa9fou
        if (naissanceTxt.isEmpty() || destinationTxt.isEmpty()) {
            Toast.makeText(this, "Veuillez saisir les deux années", Toast.LENGTH_SHORT).show();
            return;
        }

        // -1 ya3ni ma7adech tchekka 3al motif
        int idChoisi = rgMotif.getCheckedRadioButtonId();
        if (idChoisi == -1) {
            Toast.makeText(this, "Veuillez choisir un motif", Toast.LENGTH_SHORT).show();
            return;
        }

        // n7awlou el ktiba l ar9am sa7i7a (int) bel Integer.parseInt
        int naissance = Integer.parseInt(naissanceTxt);
        int destination = Integer.parseInt(destinationTxt);

        // 3am el wejha lazem ykoun akbar men 3am el wiladha
        if (destination <= naissance) {
            // n3rdhou el 8alta direct fel 7a9l mta3 el wejha
            etDestination.setError("Doit être supérieure à l'année de naissance");
            etDestination.requestFocus();
            return;
        }

        // njibou el ktiba mta3 el motif elli tchekka (mithel: Tourisme)
        RadioButton rbChoisi = findViewById(idChoisi);
        String motif = rbChoisi.getText().toString();

        // Intent explicite: nsamriw direct el classe mta3 el wajiha ethnya
        Intent intent = new Intent(this, VerificationActivity.class);

        // nab3thou el donnees: el cles mta3 Cles (mouch maktouba b yedd)
        intent.putExtra(Cles.cleNom(Cles.MATRICULE), nom);
        intent.putExtra(Cles.cleNaissance(Cles.MATRICULE), naissance);
        intent.putExtra(Cles.cleDestination(Cles.MATRICULE), destination);
        intent.putExtra(Cles.cleMotif(Cles.MATRICULE), motif);

        // nel7ellou el wajiha ethnya
        startActivity(intent);
    }
}