# Douane Temporelle

Douane Temporelle est une application Android développée en Java qui simule le contrôle des voyageurs dans le temps. L'utilisateur renseigne les informations d'un voyageur, consulte son passeport temporel, puis approuve ou refuse son voyage.

## Fonctionnalités

- Saisie du nom, de l'année de naissance et de l'année de destination.
- Choix du motif : Tourisme, Modification du passé ou Fuite.
- Calcul de l'âge temporel : année de destination moins année de naissance.
- Affichage des informations sous forme de passeport temporel.
- Décision d'approbation ou de refus avec un écran de résultat.
- Possibilité de recommencer avec un nouveau voyageur.

## Parcours utilisateur

1. **Formulaire (`MainActivity`)** : saisir les informations du voyageur et sélectionner un motif.
2. **Vérification (`VerificationActivity`)** : consulter le passeport temporel et choisir d'approuver ou de refuser le saut temporel.
3. **Résultat (`ResultActivity`)** : afficher le verdict et proposer un nouveau contrôle.

## Technologies utilisées

- Java
- XML
- Android Studio
- Android SDK
- Git et GitHub

## Organisation du projet

```text
app/src/main/
├── java/com/example/douanetemporelle/
│   ├── MainActivity.java
│   ├── VerificationActivity.java
│   └── ResultActivity.java
├── res/layout/
│   ├── activity_main.xml
│   ├── activity_verification.xml
│   └── activity_result.xml
└── AndroidManifest.xml
```

## Équipe

Projet académique réalisé en binôme par **Ines Bourghida** et **Motaz Dellai**.
