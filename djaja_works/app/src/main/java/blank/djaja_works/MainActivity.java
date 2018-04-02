package blank.djaja_works;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	private static final String Tag ="Home Ativity";
    //private SectionPagerAdapter mSectionPagerAdapter;
    private ViewPager viewPager;
    public Toolbar toolbar;
    boolean doubleBackToExitPressedOnce = false;
    private int[] tabIcons = {
            R.drawable.ic_map_black_24dp,
            R.drawable.ic_monetization_on_black_24dp,
            R.drawable.ic_event_note_black_24dp,
            R.drawable.ic_more_horiz_black_24dp
    };
    //private SessionManager session;
    //private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
 
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
 
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

 
        /*tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);*/
        //setupTabIcons();
    }

    /*private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }*/

    private void setupViewPager(ViewPager viewPager) {
        /*ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), "ONE");
        adapter.addFragment(new TwoFragment(), "TWO");
        adapter.addFragment(new ThreeFragment(), "THREE");
        viewPager.setAdapter(adapter);*/
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
 
        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }
 
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }
 
        @Override
        public int getCount() {
            return mFragmentList.size();
        }
 
        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
 
        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }
}
