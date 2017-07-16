package com.example.raul.myboomnavdrawer.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raul.myboomnavdrawer.Adapter.SearchAdapter;
import com.example.raul.myboomnavdrawer.Adapter.SlidingImage_Adapter;
import com.example.raul.myboomnavdrawer.CirclePageIndicator;
import com.example.raul.myboomnavdrawer.Fragment.NoticeFragment;
import com.example.raul.myboomnavdrawer.Fragment.ReminderFragment;
import com.example.raul.myboomnavdrawer.Fragment.TimeTableFragment;
import com.example.raul.myboomnavdrawer.R;
import com.example.raul.myboomnavdrawer.databinding.ActivityMainBinding;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    SearchAdapter adapter;
    List arrayList = new ArrayList();
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES = {R.drawable.school, R.drawable.cafe, R.drawable.classroom,
            R.drawable.home1, R.drawable.lab, R.drawable.library, R.drawable.playground};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {
            Intent intent=new  Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            return true;
        }else if(id==R.id.action_logout){
            finish();
        }
        return true;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
       // setContentView(R.layout.activity_main);
        arrayList.add("Home page");
        arrayList.add("Notice Board");
        arrayList.add("Extra Curricular Activities");
        arrayList.add("School Bus");
        arrayList.add("Reminders");
        arrayList.add("Calendar");
        arrayList.add("Time table");
        adapter= new SearchAdapter(arrayList);
        activityMainBinding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                activityMainBinding.listView.setAdapter(adapter);
                activityMainBinding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(MainActivity.this,"clicked"+i,Toast.LENGTH_LONG).show();
                        Fragment fragment = null;
                        if(i==1){
                            fragment = new NoticeFragment();
                        } else if(i == 2){
                           // fragment = new ExtraCurricularFragment();
                        } else if (i == 3){
                            //fragment = new BusFragment();
                        } else if ( i == 4){fragment = new ReminderFragment();}
                        else if (i == 5){
                            //fragment = new CalenderFragment();
                        }else if (i ==6){fragment = new TimeTableFragment();}
                        else if(i == 0){fragment = new NoticeFragment();}

                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frameLayoutMain, fragment, fragment.toString());
                        fragmentTransaction.addToBackStack(fragment.toString());
                        fragmentTransaction.commit();
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        activityMainBinding.search.setActivated(true);
        activityMainBinding.search.setQueryHint("Type your search keyword here");
        activityMainBinding.search.onActionViewExpanded();
        activityMainBinding.search.setIconified(false);
        activityMainBinding.search.clearFocus();
        ActionBar mActionBar = getSupportActionBar();
        assert mActionBar != null;
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View actionBar = mInflater.inflate(R.layout.custom_action_bar, null);
        TextView mTitleTextView = (TextView) actionBar.findViewById(R.id.title_text);
        mTitleTextView.setText("Parents App");
        mActionBar.setCustomView(actionBar);
        mActionBar.setDisplayShowCustomEnabled(true);
        ((Toolbar) actionBar.getParent()).setContentInsetsAbsolute(0, 0);

       // BoomMenuButton leftBmb = (BoomMenuButton) actionBar.findViewById(R.id.action_bar_right_bmb);
        BoomMenuButton rightBmb = (BoomMenuButton) actionBar.findViewById(R.id.action_bar_left_bmb);

        /*leftBmb.setButtonEnum(ButtonEnum.TextOutsideCircle);
        leftBmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_9_1);
        leftBmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_9_1);
        for (int i = 0; i < leftBmb.getPiecePlaceEnum().pieceNumber(); i++)
            leftBmb.addBuilder(BuilderManager.getTextOutsideCircleButtonBuilderWithDifferentPieceColor());
*/
        rightBmb.setButtonEnum(ButtonEnum.Ham);
        rightBmb.setPiecePlaceEnum(PiecePlaceEnum.HAM_6);
        rightBmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_1);
        rightBmb.addBuilder(new HamButton.Builder().normalImageRes(R.drawable.notice_icon)
                .normalText("Notice Board")
                .subNormalText("Read about important events here.")
                .normalColor(Color.BLUE)
                .listener(new OnBMClickListener() {
                              @Override
                              public void onBoomButtonClick(int index) {
                                  //Toast.makeText(MainActivity.this, "Clicked First one", Toast.LENGTH_LONG).show();
                                  FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                  Fragment fragment = new NoticeFragment();
                                  fragmentTransaction.replace(R.id.frameLayoutMain, fragment, fragment.toString());
                                  fragmentTransaction.addToBackStack(fragment.toString());
                                  fragmentTransaction.commit();
                              }
                          }));

        rightBmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_2);
           rightBmb.addBuilder(new HamButton.Builder().normalImageRes(R.drawable.extracurricular)
                    .normalText("Extra Curricular Activities")
                    .subNormalText("Help give your child a structured environment.")
                    .normalColor(Color.BLUE)
                   .listener(new OnBMClickListener() {
                       @Override
                       public void onBoomButtonClick(int index) {
                           Toast.makeText(MainActivity.this, "Clicked second one", Toast.LENGTH_LONG).show();
                       }
                   }));
        rightBmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_3);
            rightBmb.addBuilder(new HamButton.Builder().normalImageRes(R.drawable.bus)
                    .normalText("School Bus")
                    .subNormalText("Get real-time info on where your child's bus is.")
                    .normalColor(Color.BLUE)
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            Toast.makeText(MainActivity.this, "Clicked 3rd one", Toast.LENGTH_LONG).show();
                        }
                    }));

        rightBmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_4);
        rightBmb.addBuilder(new HamButton.Builder().normalImageRes(R.drawable.reminder_icon)
                .normalText("Reminders")
                .subNormalText("Daily tasks you should be aware of.")
                .normalColor(Color.BLUE)
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        //Toast.makeText(MainActivity.this, "Clicked 4th one", Toast.LENGTH_LONG).show();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        Fragment fragment = new ReminderFragment();
                        fragmentTransaction.replace(R.id.frameLayoutMain, fragment, fragment.toString());
                        fragmentTransaction.addToBackStack(fragment.toString());
                        fragmentTransaction.commit();
                    }
                }));
        rightBmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_5);
        rightBmb.addBuilder(new HamButton.Builder().normalImageRes(R.drawable.calendar)
                .normalText("Calendar")
                .subNormalText("Plan your child's education.")
                .normalColor(Color.BLUE)
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        Toast.makeText(MainActivity.this, "Clicked 5th one", Toast.LENGTH_LONG).show();
                    }
                }));
        rightBmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_6);
        rightBmb.addBuilder(new HamButton.Builder().normalImageRes(R.drawable.schedule)
                .normalText("Time Table")
                .subNormalText("Check the time table.")
                .normalColor(Color.BLUE)
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        //Toast.makeText(MainActivity.this, "Clicked 6th one", Toast.LENGTH_LONG).show();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        Fragment fragment = new TimeTableFragment();
                        fragmentTransaction.replace(R.id.frameLayoutMain, fragment, fragment.toString());
                        fragmentTransaction.addToBackStack(fragment.toString());
                        fragmentTransaction.commit();
                    }
                }));
        init();

    }
    private void init() {
        for (int i = 0; i < IMAGES.length; i++)
            ImagesArray.add(IMAGES[i]);

        mPager = (ViewPager) findViewById(R.id.pager);


        mPager.setAdapter(new SlidingImage_Adapter(MainActivity.this, ImagesArray));


        CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES = IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {


            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }

        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {

            @Override
            public void run()
            {
                handler.post(Update);
            }

        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position)
            {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }

        });

    }

}






