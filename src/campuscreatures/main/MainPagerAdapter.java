package campuscreatures.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 
public class MainPagerAdapter extends FragmentPagerAdapter {
 
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // map fragment activity
            return new MapFragment();
        case 1:
            // home fragment activity
            return new HomeFragment();
        case 2:
            // creature atlas fragment activity
            return new CreatureAtlasFragment();
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
 
}
