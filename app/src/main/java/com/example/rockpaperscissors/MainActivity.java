package com.example.rockpaperscissors;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void chooseRock ( View view ) {
        checkWinner( "rock" );
    }

    public void choosePaper ( View view ) {
        checkWinner( "paper" );
    }

    public void chooseScissors ( View view ) {
        checkWinner( "scissors" );
    }

    private String chooseAppsMove() {
        String[] moveOptions = {"rock", "paper", "scissors"};
        int randomNumber = new Random().nextInt(3);

        ImageView imgAppsChoice = findViewById(R.id.img_apps_choice);
        String appsMove = moveOptions[randomNumber];
        switch (appsMove){
            case "rock":
                imgAppsChoice.setImageResource(R.drawable.rock);
                break;
            case "paper":
                imgAppsChoice.setImageResource(R.drawable.paper);
                break;
            case "scissors":
                imgAppsChoice.setImageResource(R.drawable.scissors);
                break;
        }
        return appsMove;
    }

    private void checkWinner( String userChoice ) {
        String appsMove = chooseAppsMove();
        TextView textResult = findViewById(R.id.text_game_result);

        if ( //app wins
            (appsMove.equals("rock") && userChoice.equals("scissors")) ||
            (appsMove.equals("paper") && userChoice.equals("rock"))  ||
            (appsMove.equals("scissors") && userChoice.equals("paper"))
        ){
            textResult.setText("You lose - the app wins");
            textResult.setTextColor(getResources().getColor(R.color.red));
        } else if ( //user wins
             (userChoice.equals("rock") && appsMove.equals("scissors")) ||
             (userChoice.equals("paper") && appsMove.equals("rock"))  ||
             (userChoice.equals("scissors") && appsMove.equals("paper"))
        ) {
            textResult.setText("You win - the app loses");
            textResult.setTextColor(getResources().getColor(R.color.green));
        } else { //game draw
            textResult.setText("It's a draw");
            textResult.setTextColor(getResources().getColor(R.color.black));
        }

    }

    public void restart (View view) {
        ImageView imgAppsChoice = findViewById(R.id.img_apps_choice);
        imgAppsChoice.setImageResource(R.drawable.standard);

        TextView textResult = findViewById(R.id.text_game_result);
        textResult.setTextColor(getResources().getColor(R.color.black));
        textResult.setText("...");

    }

}