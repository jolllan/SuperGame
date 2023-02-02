package com.example.supergame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.media.MediaPlayer;



public class MainActivity extends AppCompatActivity

{

    private EditText ChampSaisie;
    private Button Bouton;
    private TextView response;
    private ProgressBar ProgressBarScreen;
    private TextView historyView;

    private int randomNbr;
    private int scoreBar;
    //AlertDialog.Builder builder = new AlertDialog.Builder(this);
    MediaPlayer mediaPlayer;









    private View.OnClickListener BtnIsOk = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.i("INFO","onClick bouton: "); //envoie une information au log.
            String txtBuff = ChampSaisie.getText().toString();
            Log.i("INFO", "valeur cherchée : "+ randomNbr);

            //si le score est sup ou égal à 10 ne rien faire
            if (scoreBar >= 10)
            {
                response.setText("Vous ne pouvez plus jouer");
            }
            else
            {
                if (txtBuff.equals("")) return;
                else
                {
                    //Convertir string en int
                    int nbrInt = Integer.parseInt(txtBuff);
                    if (nbrInt > randomNbr)
                    {
                        response.setText("Plus petit");
                    }
                    else if(nbrInt == randomNbr)
                    {
                        response.setText("Bravo !");
                        congratulation();
                    }
                    else{
                        response.setText("Plus grand");
                    }
                    historyView.append(txtBuff + "\n");
                }
                scoreBar++;
            }
            ProgressBarScreen.incrementProgressBy(1);
            ChampSaisie.setText("");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChampSaisie = findViewById(R.id.ChampSaisie);
        Bouton = findViewById(R.id.bouton);
        response = findViewById(R.id.response);
        ProgressBarScreen = findViewById(R.id.progressBarScreen);
        historyView = findViewById(R.id.historyView);
        Bouton.setOnClickListener(BtnIsOk);
        mediaPlayer = MediaPlayer.create(this, R.raw.ambiance);

        init();
    }



    private void init()
    {
        scoreBar = 0;
        randomNbr = (int)(Math.random() * (100-1));

        ChampSaisie.setText("");
        historyView.setText("");
        response.setText("");
        Log.i("INFO", "Valeur cherché : " + randomNbr);
        ProgressBarScreen.setProgress(0);

        mediaPlayer.start();
    }

    private void congratulation()
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.app_name).setMessage(getString(R.string.DialogBox, scoreBar));
        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                init();
            }
        });
        builder.setMessage("Vous avez gagné !");
        AlertDialog retryAlert = builder.create();
        retryAlert.show();

    }





}
