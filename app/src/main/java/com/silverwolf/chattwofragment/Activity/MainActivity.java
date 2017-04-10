package com.silverwolf.chattwofragment.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.silverwolf.chattwofragment.Fragment.User1Fragment;
import com.silverwolf.chattwofragment.Fragment.User2Fragment;
import com.silverwolf.chattwofragment.R;

import java.util.ArrayList;

/**
 * Created by SilverWolf on 04/04/2016.
 */
public class MainActivity extends AppCompatActivity implements User1Fragment.OnSwitchListener,
        User2Fragment.OnSwitchListener {

    public static final String valueKey = "CHAT1", user1 = "User1: ", user2 = "User2: ";
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set fragment default in first view
        currentFragment = new User1Fragment();
        setTitle("User 1");
        showFragment(currentFragment, null);

    }

    //show fragment with tag, bundle
    private void showFragment(Fragment fragment, ArrayList<String> text) {
        if (text != null) {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList(valueKey, text);
            fragment.setArguments(bundle);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_content, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void sendTextTo1(ArrayList<String> text) {
        setTitle("User 1");
        showFragment(new User1Fragment(), text);
    }

    @Override
    public void sendTextTo2(ArrayList<String> text) {
        setTitle("User 2");
        showFragment(new User2Fragment(), text);
    }
}
