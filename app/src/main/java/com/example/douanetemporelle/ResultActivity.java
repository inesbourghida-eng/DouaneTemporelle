package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

// hethi el wajiha ethalitha: el 9arar el nehayi (a5dhar = mwafa9a, a7mar = rafdh)
public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // nrabtou el classe m3a el design activity_result.xml
        setContentView(R.layout.activity_result);

        // nrabtou el variables bel 3anaser mta3ha fel XML
        LinearLayout layout = findViewById(R.id.ll_resultat_ib);
        TextView tvMessage = findViewById(R.id.tv_message_ib);
        Button btnNouveau = findViewById(R.id.btn_nouveau_ib);

        // na9raw el verdict (boolean) w el esm elli jeyin men el wajiha ethnya
        boolean approuve = getIntent().getBooleanExtra(Cles.cleVerdict(Cles.MATRICULE), false);
        String nom = getIntent().getStringExtra(Cles.cleNom(Cles.MATRICULE));

        if (approuve) {
            // mwafa9a: 5alfiya 5adhra w message "Bon voyage"
            layout.setBackgroundColor(ContextCompat.getColor(this, R.color.vert_approuve));
            tvMessage.setText("Bon voyage " + nom);
        } else {
            // rafdh: 5alfiya 7amra w message el i3tiqal ezzmeni
            layout.setBackgroundColor(ContextCompat.getColor(this, R.color.rouge_refuse));
            tvMessage.setText(nom + " est en état d'arrestation temporelle");
        }

        // zerr "Nouveau voyageur": nraj3ou lel wajiha el loula w nesakrou el wajihat el 9dom
        btnNouveau.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            // CLEAR_TOP yfas5 el wajihat elli fou9 MainActivity, NEW_TASK ybda pile jdida
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            // nesakrou el wajiha el 7alia
            finish();
        });
    }
}