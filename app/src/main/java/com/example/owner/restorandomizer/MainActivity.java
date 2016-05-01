package com.example.owner.restorandomizer;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements MyAdapter.ItemSelectedListner {
    private List<Restos> restos = new ArrayList<Restos>();
//  private List<Restos> selected = new ArrayList<Restos>();
    private Restos currentResto;
    private final Context context = this;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    /*****
     * Animation for Sliding Menu
     *******/
    private Animation animUp;
    private Animation animDown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LinearLayout slider = (LinearLayout) findViewById(R.id.slider);
        slider.bringToFront();

        //Adding RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(this.restos, this);
        mRecyclerView.setAdapter(mAdapter);




        //Adding Buttons in
        Button randomizeButton = getRandomizeButton();
        ImageButton addButton = getAddButton();
        ImageButton deleteButton = getDeleteButton();

        //Assigning listeners to buttons.
        randomizeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.randomize();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            //Setting up prompts for user to input new Resto name
            //http://inducesmile.com/android-snippets/android-snippet-prompt-user-input-with-an-alertdialog/
            public void onClick(View v) {
                AlertDialog.Builder inputAlert = new AlertDialog.Builder(context);
                inputAlert.setTitle("RestoRandomizer");
                inputAlert.setMessage("Input Resto Name");
                final EditText editText = new EditText(context);
                inputAlert.setView(editText);

                inputAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        if (editText != null) {
                            String userInput = editText.getText().toString();
                            MainActivity.this.addResto(userInput);
                        } else {
                            dialog.dismiss();
                        }
                    }
                });

                inputAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = inputAlert.create();
                alertDialog.show();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.deleteResto();
            }
        });

        //Adding our default restaurants for app.
        restos.add(new Restos("dominos",true));
        restos.add(new Restos("pizzahut",true));
        restos.add(new Restos("nandos",true));
        restos.add(new Restos("burgerking",true));
        restos.add(new Restos("mcdonalds",true));
        restos.add(new Restos("default",true));
        restos.add(new Restos("burgerfuel",true));
        restos.add(new Restos("chefspalette",true));
        restos.add(new Restos("hells",true));
        restos.add(new Restos("kfc",true));
        restos.add(new Restos("subway",true));
        restos.add(new Restos("wendys",true));
        restos.add(new Restos("heyramen",true));

        getLin().setVisibility(View.GONE);
        animUp = AnimationUtils.loadAnimation(this, R.anim.moveright);
        animDown = AnimationUtils.loadAnimation(this, R.anim.moveleft);
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

    /**
     * Randomize Button functionality. Uses a random number generator to fetch a string on the restos
     * arraylist and calls fetchImage to get the image from the xml file.
     */
    public void randomize() {
        ImageView mainImgView = (ImageView) findViewById(R.id.mainImage);
        int i = (int) (Math.random() * (getCheckedList().size() - .01));
        this.currentResto = getCheckedList().get(i);
        mainImgView.setImageDrawable(fetchImage(this.currentResto.getRestoName()));
    }


    /**
     * Adds a Resto name by prompting a user for a string input. The resto name is added to the
     * resto list and towards the randomizer choices.
     */
    public void addResto(String input) {
        Toast.makeText(this, "Resto Added!", Toast.LENGTH_SHORT).show();
        restos.add(new Restos(input,true));
    }

    /**
     * Deletes the current resto displayed on the app from the resto list.
     * The resto deleted will never be selected ever again.
     */
    public void deleteResto() {
        if (this.restos.contains(this.currentResto)) {
            this.restos.remove(this.currentResto);
            Toast.makeText(this, "Resto has been deleted.", Toast.LENGTH_SHORT).show();
            mAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "Resto has already been removed.", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Uses a switch to return the image for a given restaurant string from android resources.
     *
     * @param restoName
     * @return image drawable
     */
    public Drawable fetchImage(String restoName) {

        switch (restoName) {

            case "dominos":
                return ResourcesCompat.getDrawable(getResources(), R.drawable.dominos_logo, null);

            case "pizzahut":
                return ResourcesCompat.getDrawable(getResources(), R.drawable.pizzahut_logo, null);

            case "nandos":
                return ResourcesCompat.getDrawable(getResources(), R.drawable.nandos_logo, null);

            case "burgerking":
                return ResourcesCompat.getDrawable(getResources(), R.drawable.burgerking_logo, null);

            case "mcdonalds":
                return ResourcesCompat.getDrawable(getResources(), R.drawable.mcdonalds_logo, null);

            case "burgerfuel":
                return ResourcesCompat.getDrawable(getResources(), R.drawable.burgerfuel_logo, null);

            case "chefspalette":
                return ResourcesCompat.getDrawable(getResources(), R.drawable.chefspalette_logo, null);

            case "hells":
                return ResourcesCompat.getDrawable(getResources(), R.drawable.hells_logo, null);

            case "kfc":
                return ResourcesCompat.getDrawable(getResources(), R.drawable.kfc_logo, null);

            case "subway":
                return ResourcesCompat.getDrawable(getResources(), R.drawable.subway_logo, null);

            case "wendys":
                return ResourcesCompat.getDrawable(getResources(), R.drawable.wendys_logo, null);

            default:
                Toast.makeText(this, currentResto.getRestoName(), Toast.LENGTH_SHORT).show();
                return ResourcesCompat.getDrawable(getResources(), R.drawable.questionmark, null);
        }
    }

    float startX = 0;
    boolean shown = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                startX = event.getX();
                break;
            }
            case MotionEvent.ACTION_UP: {
                float endX = event.getX();

                if ((Math.abs(endX - startX)) < 10) {
                    break;
                } // if the swipe is too small just ignore

                if (endX > startX && !shown) {
                    System.out.println("Move UP");
                    getLin().setVisibility(View.VISIBLE);
                    getLin().startAnimation(animUp);
                    shown = true;
                } else if (endX < startX && shown) {

                    getLin().startAnimation(animDown);
                    getLin().setVisibility(View.GONE);
                    shown = false;
                }
            }

        }
        return true;
    }

    public LinearLayout getLin() {
        return (LinearLayout) findViewById(R.id.slider);
    }

    /**
     * Getters for the app buttons.
     *
     * @return
     */
    public Button getRandomizeButton() {
        return (Button) findViewById(R.id.randomize);
    }

    public ImageButton getAddButton() {
        return (ImageButton) findViewById(R.id.add);
    }

    public ImageButton getDeleteButton() {
        return (ImageButton) findViewById(R.id.delete);
    }

    public ArrayList<Restos> getCheckedList(){
        ArrayList<Restos> temp= new ArrayList<Restos>();
        for(Restos r: restos){
            if(r.getChecked()){
                temp.add(r);
            }
        }
        return temp;
    }

    @Override
    public void onItemSelected(Restos clicked, boolean isChecked) {
        if(isChecked){
            clicked.setChecked(true);
        }
        if(!isChecked){
           clicked.setChecked(false);
        }
    }
}
