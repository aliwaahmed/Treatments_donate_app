package com.logapps.treatments_donate_app.Person;

import com.logapps.treatments_donate_app.Person.Accepted.Accepted;
import com.logapps.treatments_donate_app.Person.needs_data.Ineed;
import com.logapps.treatments_donate_app.Person.replace_data.Replacements_user;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class SerctionPagerAdapter extends FragmentPagerAdapter {

    public SerctionPagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }


    public Fragment getItem(int position){

        switch (position){
            case 0:
                Replacements_user replacement_user = new Replacements_user();
                return replacement_user;
            case 1:
                Ineed ineed = new Ineed();
                return ineed;
            case 2:
                Accepted accepted = new Accepted();
                return accepted ;
            case 3:
                Exchanges exchanges = new Exchanges();
                return exchanges ;
            default:
                return null ;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }



    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Replace";
            case 1:
                return "I need";
            case 2 :
                return "Accept";
          
            default:
                return null;

        }

    }
}
