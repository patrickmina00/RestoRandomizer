package com.example.owner.restorandomizer;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private List<String> restos = new ArrayList<String>();      /* List of all the available restos */
    private List<String> selectedRestos = new ArrayList<>();    /* List of selected restos from the user */

    private String currentResto;

    /*****Animation for Sliding Menu*******/
    private Animation animUp;
    private Animation animDown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Adding Buttons in
        Button randomizeButton = getRandomizeButton();
        ImageButton addButton = getAddButton();
        ImageButton deleteButton = getDeleteButton();

        //Assigning listeners to buttons and checkbox.
        randomizeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.randomize();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.addResto();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.deleteResto();
            }
        });

        //Adding our default restaurants for app.
        restos.add("dominos");
        restos.add("pizzahut");
        restos.add("nandos");
        restos.add("burgerking");
        restos.add("mcdonalds");
        restos.add("default");
        restos.add("burgerfuel");
        restos.add("chefspalette");
        restos.add("hells");
        restos.add("kfc");
        restos.add("subway");
        restos.add("wendys");
        restos.add("heyramen");

        getLin().setVisibility(View.GONE);
        animUp = AnimationUtils.loadAnimation(this, R.anim.moveright);
        animDown = AnimationUtils.loadAnimation(this, R.anim.moveleft);
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
            switch(view.getId()){
                case R.id.mcheckBox:
                    if(checked) selectedRestos.add("mcdonalds");
                    break;
                case R.id.ncheckBox:
                    if(checked) selectedRestos.add("nandos");
                    break;
                case R.id.bcheckBox:
                    if(checked) selectedRestos.add("burgerking");
                    break;
                case R.id.kcheckBox:
                    if(checked) selectedRestos.add("kfc");
                    break;
                case R.id.bfcheckBox:
                    if(checked) selectedRestos.add("burgerfuel");
                    break;
                case R.id.cpcheckBox:
                    if(checked) selectedRestos.add("chefspalette");
                    break;
                case R.id.dcheckBox:
                    if(checked) selectedRestos.add("dominos");
                    break;
                case R.id.hcheckBox:
                    if(checked) selectedRestos.add("hells");
                    break;
                case R.id.phcheckBox:
                    if(checked) selectedRestos.add("pizzahut");
                    break;
                case R.id.scheckBox:
                    if(checked) selectedRestos.add("subway");
                    break;
                case R.id.wcheckBox:
                    if(checked) selectedRestos.add("wendys");
                    break;
            }
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
        int i = (int) (Math.random() * (selectedRestos.size() - .01));
        //this.currentResto = restos.get(i);
        this.currentResto = selectedRestos.get(i);
        mainImgView.setImageDrawable(fetchImage(this.currentResto));
    }


    public void addResto() {

    }

    /**
     * Deletes the current resto displayed on the app from the resto list.
     * The resto deleted will never be selected ever again.
     */
    public void deleteResto() {
        if (this.restos.contains(this.currentResto)) {
            this.restos.remove(this.currentResto);
            Toast.makeText(this, "Resto has been deleted.", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(this, currentResto, Toast.LENGTH_SHORT).show();
                return ResourcesCompat.getDrawable(getResources(), R.drawable.questionmark, null);
        }
    }

    float startX = 0;
    boolean shown = false;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN : {
                startX = event.getX();
                break ;
            }
            case MotionEvent.ACTION_UP: {
                float endX = event.getX();

                if((Math.abs(endX-startX)) < 10){break;} // if the swipe is too small just ignore

                if (endX > startX && !shown) {
                    System.out.println("Move UP");
                    getLin().setVisibility(View.VISIBLE);
                    getLin().startAnimation(animUp);
                    shown=true;
                }
                else if (endX < startX && shown){

                    getLin().startAnimation(animDown);
                    getLin().setVisibility(View.GONE);
                    shown = false;
                }
            }

        }
        return true;
    }

    public LinearLayout getLin(){
        return (LinearLayout) findViewById(R.id.slider);
    }

    /**
     * Getters for the app buttons.
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

}
