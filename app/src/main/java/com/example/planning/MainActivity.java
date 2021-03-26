package com.example.planning;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import java.util.Vector;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ViewGroup m_my_list;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_my_list = (ViewGroup)findViewById(R.id.list);
        LayoutInflater l = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Vector<String> strings = new Vector<String>();
        for(int i=0;i<100;++i)
        {
            strings.add("Item " + i);
        }
        for(String s : strings)
        {
            View item = l.inflate( R.layout.list_item, null);
            ((TextView)item.findViewById(R.id.item_text)).setText(s);
            item.findViewById(R.id.more_button).setOnClickListener(this);
            m_my_list.addView(item);
        }
        findViewById(R.id.detail).setOnClickListener(this);
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
    public void onClick(View v)
    {
        if (v.getId()==R.id.add_project)
        {
            View detailview = findViewById(R.id.detail);
            float width = findViewById(R.id.main_layout).getWidth();
            TranslateAnimation anim = new TranslateAnimation(width, 0.0f, 0.0f, 0.0f);
            anim.setDuration(300);
            anim.setFillAfter(true);
            detailview.bringToFront();
            detailview.startAnimation(anim);
            detailview.setVisibility(View.VISIBLE);
            detailview.setEnabled(true);
        }
        else if (v.getId()==R.id.detail)
        {
            View detailview = v;
            TranslateAnimation anim = new TranslateAnimation(0.0f, detailview.getWidth(), 0.0f, 0.0f);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {}
                @Override
                public void onAnimationEnd(Animation animation) {
                    findViewById(R.id.list_view).bringToFront();
                }
                @Override
                public void onAnimationRepeat(Animation animation) {}
            });
            anim.setDuration(300);
            anim.setFillAfter(true);
            detailview.startAnimation(anim);
            detailview.setEnabled(false);
        }
    }
}