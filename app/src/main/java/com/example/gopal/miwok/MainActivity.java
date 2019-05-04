package com.example.gopal.miwok;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView number = findViewById(R.id.number_activity);
        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent numberActivity = new Intent(MainActivity.this,NumbersActivity.class);
                startActivity(numberActivity);
            }
        });
        TextView family = findViewById(R.id.family_activity);
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent familyActivity = new Intent(MainActivity.this,FamilyActivity.class);
                startActivity(familyActivity);
            }
        });
        TextView color = findViewById(R.id.colors_activity);
        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent colorActivity = new Intent(MainActivity.this,ColorsActivity.class);
                startActivity(colorActivity);
            }
        });
        TextView phrase = findViewById(R.id.phrases_activity);
        phrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phraseActivity = new Intent(MainActivity.this,PhrasesActivity.class);
                startActivity(phraseActivity);
            }
        });

    }


    @Override
    public void onBackPressed() {

        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the positive and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.exit_title);
        builder.setPositiveButton(R.string.positive_exit_action, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                finish();

            }
        });
        builder.setNegativeButton(R.string.negative_exit_action, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
