package com.example.justin.blackjack;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.view.ViewGroup.LayoutParams;

import java.util.Random;


public class BlackJack extends ActionBarActivity {

    String[] deck = {
            "ace","2","3","4","5","6","7","8","9","10","jack","queen","king",
            "ace","2","3","4","5","6","7","8","9","10","jack","queen","king",
            "ace","2","3","4","5","6","7","8","9","10","jack","queen","king",
            "ace","2","3","4","5","6","7","8","9","10","jack","queen","king"
    };
    int playerScore;
    int dealerScore;
    String[] dHand;
    String[] pHand;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack);
        Button newGame = (Button)findViewById(R.id.newGameButton);
        Button hitMe = (Button)findViewById(R.id.hitButton);
        Button stay = (Button)findViewById(R.id.stayButton);
        final TextView dealer = new TextView(this);
        final TextView player = new TextView(this);
        final Random randomGenerator = new Random();

        newGame.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                if(dealer.getText()==null) {


                    TableRow dealerHand = (TableRow) findViewById(R.id.dealerHand);
                    TableRow playerHand = (TableRow) findViewById(R.id.playerHand);

                    dealer.setText("");
                    player.setText("");
                    dealerHand.addView(dealer);
                    playerHand.addView(player);
                    dealer.append(deck[randomGenerator.nextInt(51)] + " " + deck[randomGenerator.nextInt(51)]);
                    player.append(deck[randomGenerator.nextInt(51)] + " " + deck[randomGenerator.nextInt(51)]);
                    //Dealer verification to stay or hit.
                   // if(dealerScore<16){
                       dHand = dealer.getText().toString().split(" ");
                        for(int i=0; i<dHand.length;i++){
                            if(dHand[i].equals("queen")||dHand[i].equals("jack")||dHand[i].equals("king")){
                                dealerScore+=10;
                                Log.d("Score:",Integer.toString(playerScore));
                            }
                            else if(dHand[i].equals("ace")){
                                if(dealerScore+11>21){
                                    dealerScore+=1;
                                    Log.d("Score:",Integer.toString(dealerScore));
                                }
                                else{
                                    dealerScore+=11;
                                    Log.d("Score:",Integer.toString(dealerScore));
                                }
                            }
                            else {
                                dealerScore += Integer.parseInt(dHand[i]);
                                Log.d("Score:",Integer.toString(dealerScore));
                            }
                        //}

                    }
                }else{
                    dealer.setText("");
                    player.setText("");
                    TableRow dealerHand = (TableRow) findViewById(R.id.dealerHand);
                    TableRow playerHand = (TableRow) findViewById(R.id.playerHand);
                    dealerHand.removeView(dealer);
                    playerHand.removeView(player);


                    dealerHand.addView(dealer);
                    playerHand.addView(player);
                    dealer.append(deck[randomGenerator.nextInt(51)] + " " + deck[randomGenerator.nextInt(51)]);
                    player.append(deck[randomGenerator.nextInt(51)] + " " + deck[randomGenerator.nextInt(51)]);
                    pHand = player.getText().toString().split(" ");
                    for(int i=0; i<pHand.length;i++){
                        if(pHand[i].equals("queen")||pHand[i].equals("jack")||pHand[i].equals("king")){
                            playerScore+=10;
                            Log.d("Score:",Integer.toString(playerScore));
                        }
                        else if(pHand[i].equals("ace")){
                            if(playerScore+11>21){
                                playerScore+=1;
                                Log.d("Score:",Integer.toString(playerScore));
                            }
                            else{
                                playerScore+=11;
                                Log.d("Score:",Integer.toString(playerScore));
                            }
                        }
                        else {
                            playerScore += Integer.parseInt(pHand[i]);
                            Log.d("Score:",Integer.toString(playerScore));
                        }
                    }
                    dHand = dealer.getText().toString().split(" ");
                    for(int i=0; i<dHand.length;i++) {
                        if (dHand[i].equals("queen") || dHand[i].equals("jack") || dHand[i].equals("king")) {
                            dealerScore += 10;
                            Log.d("Score:", Integer.toString(playerScore));
                        } else if (dHand[i].equals("ace")) {
                            if (dealerScore + 11 > 21) {
                                dealerScore += 1;
                                Log.d("Score:", Integer.toString(dealerScore));
                            } else {
                                dealerScore += 11;
                                Log.d("Score:", Integer.toString(dealerScore));
                            }
                        } else {
                            dealerScore += Integer.parseInt(dHand[i]);
                            Log.d("Score:", Integer.toString(dealerScore));
                        }
                    }
                }

            }
        });
        hitMe.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                player.append( " "+deck[randomGenerator.nextInt(51)]);
                //player loss verification
                playerScore=0;

                pHand = player.getText().toString().split(" ");
                for(int i=0; i<pHand.length;i++){
                    if(pHand[i].equals("queen")||pHand[i].equals("jack")||pHand[i].equals("king")){
                        playerScore+=10;
                        Log.d("Score:",Integer.toString(playerScore));
                    }
                    else if(pHand[i].equals("ace")){
                        if(playerScore+11>21){
                            playerScore+=1;
                            Log.d("Score:",Integer.toString(playerScore));
                        }
                        else{
                            playerScore+=11;
                            Log.d("Score:",Integer.toString(playerScore));
                        }
                    }
                    else {
                        playerScore += Integer.parseInt(pHand[i]);
                        Log.d("Score:",Integer.toString(playerScore));
                    }
                }
                if(playerScore>21){
                    //alert for loss
                    AlertDialog.Builder loss = new AlertDialog.Builder(BlackJack.this);
                    loss.setMessage("You Lost Play Again?");
                    loss.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            playerScore=0;
                            dealerScore=0;
                            dealer.setText("");
                            player.setText("");
                            dealer.append(deck[randomGenerator.nextInt(51)] + " " + deck[randomGenerator.nextInt(51)]);
                            player.append(deck[randomGenerator.nextInt(51)] + " " + deck[randomGenerator.nextInt(51)]);
                        }
                    })
                    .setNegativeButton("No",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    loss.show();
                }
            }
            });
        stay.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                //verify if player is ahead if true dealer goes
                Log.d("Scoredd:",Integer.toString(dealerScore));
                Log.d("Scoredd:",Integer.toString(playerScore));
                while(dealerScore<=playerScore){

                    dealerScore=0;
                    dealer.append( " "+deck[randomGenerator.nextInt(51)]);
                    String[] dHand = dealer.getText().toString().split(" ");
                    for(int i=0; i<dHand.length;i++){
                        if(dHand[i].equals("queen")||dHand[i].equals("jack")||dHand[i].equals("king")){
                            dealerScore+=10;
                            Log.d("Score:",Integer.toString(dealerScore));
                        }
                        else if(dHand[i].equals("ace")){
                            if(dealerScore+11>21){
                                dealerScore+=1;
                                Log.d("Score:",Integer.toString(dealerScore));
                            }
                            else{
                                dealerScore+=11;
                                Log.d("Score:",Integer.toString(dealerScore));
                            }
                        }
                        else {
                            dealerScore += Integer.parseInt(dHand[i]);
                            Log.d("Score:",Integer.toString(dealerScore));
                        }
                    }
                    if(dealerScore>21){
                        //alert for loss
                        AlertDialog.Builder win = new AlertDialog.Builder(BlackJack.this);
                        win.setMessage("You Won Play Again?");
                        win.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                playerScore=0;
                                dealerScore=0;
                                dealer.setText("");
                                player.setText("");
                                dealer.append(deck[randomGenerator.nextInt(51)] + " " + deck[randomGenerator.nextInt(51)]);
                                player.append(deck[randomGenerator.nextInt(51)] + " " + deck[randomGenerator.nextInt(51)]);

                            }
                        })
                                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                });
                        win.show();

                    }
                    else if(dealerScore>playerScore&&dealerScore<=21){
                        AlertDialog.Builder loss = new AlertDialog.Builder(BlackJack.this);
                        loss.setMessage("You Lost Play Again?");
                        loss.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                playerScore= 0;
                                dealerScore= 0;
                                dealer.setText("");
                                player.setText("");
                                dealer.append(deck[randomGenerator.nextInt(51)] + " " + deck[randomGenerator.nextInt(51)]);
                                player.append(deck[randomGenerator.nextInt(51)] + " " + deck[randomGenerator.nextInt(51)]);
                            }
                        })
                                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                });
                        loss.show();

                    }
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_black_jack, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
