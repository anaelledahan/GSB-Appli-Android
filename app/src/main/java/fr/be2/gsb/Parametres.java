package fr.be2.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.companion.CompanionDeviceService;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Parametres extends MainActivity {

    SQLHelper database;
    EditText codeVisiteur;
    EditText nom;
    EditText prenom;
    EditText email;
    EditText myPassword;
    EditText urlServeur;
    Button ButtonValider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);
        database= new SQLHelper(this);
        init();
    }


    public void init(){
        Cursor Param = database.fetch_parametres();
        Param.moveToFirst();
        codeVisiteur= findViewById(R.id.codeVisiteur);
        codeVisiteur.setText(Param.getString(Param.getColumnIndex("CODEV")));
        nom= findViewById(R.id.nom);
        nom.setText(Param.getString(Param.getColumnIndex("NOM")));
        prenom= findViewById(R.id.prenom);
        prenom.setText(Param.getString(Param.getColumnIndex("PRENOM")));
        email= findViewById(R.id.email);
        email.setText(Param.getString(Param.getColumnIndex("EMAIL")));
        myPassword= findViewById(R.id.motDePasse);

        urlServeur= findViewById(R.id.urlServeur);
        urlServeur.setText(Param.getString(Param.getColumnIndex("URLSERVEUR")));
        ButtonValider= findViewById(R.id.buttonValider);
    }
    public void clique_retour2(View view) {
        finish();
    }
    public void MonClick2(View v ) {
        switch (v.getId()) {
            case R.id.buttonValider:
                if (codeVisiteur.getText().toString().trim().length() == 0 || nom.getText().toString().trim().length() == 0
                        || prenom.getText().toString().trim().length() == 0 || email.getText().toString().trim().length() == 0 ||
                        myPassword.getText().toString().trim().length() == 0 || urlServeur.getText().toString().trim().length() == 0 ){
                    afficherMessage("Erreur!", "Champ vide");
                    return;
                } else {
                    Integer CodeVisiteur = Integer.parseInt(String.valueOf(codeVisiteur.getText()));
                    String Nom = nom.getText().toString();
                    String Prenom = prenom.getText().toString();
                    String Email = email.getText().toString();
                    String MyPassword = myPassword.getText().toString();
                    String UrlServeur = urlServeur.getText().toString();
                    if(database.update_parametres(CodeVisiteur,Nom, Prenom, Email, MyPassword, UrlServeur)){
                        afficherMessage("Succès", "Valeur ajoutée. " );
                    }
                    return;
                }
        }

    }
}