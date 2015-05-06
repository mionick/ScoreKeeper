package com.mionick.scorekeeper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends Activity {

    ImageView upButton;
    ImageView downButton;
    TextView numPlayersTV;
    int numPlayers;
    LayoutInflater inflater;
    LinearLayout playerLL;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numPlayers = 1;

        context = getApplicationContext();
        numPlayersTV = (TextView) findViewById(R.id.numPlayersTV);
        upButton = (ImageView) findViewById(R.id.up);
        downButton = (ImageView) findViewById(R.id.down);
        playerLL = (LinearLayout) findViewById(R.id.playerLL);
        inflater = (LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);

        LinearLayout childLL;

        //ON CLICK LISTENERS
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numPlayers++;
                numPlayersTV.setText(Integer.toString(numPlayers));
                playerLL.addView(createChild());
            }
        });

        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( numPlayers > 1 ) {
                    numPlayers--;
                    numPlayersTV.setText(Integer.toString(numPlayers));
                    playerLL.removeViewAt(playerLL.getChildCount() - 1);
                }
            }
        });

        //SETUP
        playerLL.addView(createChild());



    }

    LinearLayout createChild(){
        final LinearLayout childLL = (LinearLayout) inflater.inflate(R.layout.player_layout, null);
        final EditText score = (EditText) childLL.findViewById(R.id.score);
        final EditText change = (EditText) childLL.findViewById(R.id.change);

        ((Button) childLL.findViewById(R.id.add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cur_score;
                int change_score;

                try {
                    cur_score = Integer.parseInt(score.getText().toString());
                } catch (NumberFormatException e ) {
                    cur_score = 0;
                }
                try {
                    change_score = Integer.parseInt(change.getText().toString());
                } catch (NumberFormatException e) {
                    change_score = 0;
                }
                score.setText(Integer.toString(cur_score + change_score));
                change.setText("");
            }
        });

        ((Button) childLL.findViewById(R.id.subtract)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cur_score;
                int change_score;

                try {
                    cur_score = Integer.parseInt(score.getText().toString());
                } catch (NumberFormatException e ) {
                    cur_score = 0;
                }
                try {
                    change_score = Integer.parseInt(change.getText().toString());
                } catch (NumberFormatException e) {
                    change_score = 0;
                }

                score.setText(Integer.toString(cur_score-change_score));
                change.setText("0");
            }
        });

        return childLL;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
