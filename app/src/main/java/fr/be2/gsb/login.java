package fr.be2.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

public class login extends AppCompatActivity {
        SQLHelper  database;
        String codeVisiteur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        database=new SQLHelper(this);
        Cursor Param= database.fetch_parametres();
        Param.moveToFirst();
        codeVisiteur= Param.getString(Param.getColumnIndex("CODEV"));

        if (Integer.parseInt (codeVisiteur)==0){
            Intent intent =new Intent(getApplicationContext(), Parametres.class);
            startActivity(intent);
    }
}
}
