package com.newclass.switchbutton;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.newclass.switchbutton.view.SwitchButton;


public class MainActivity extends ActionBarActivity {

    private SwitchButton sb_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sb_state = (SwitchButton) findViewById(R.id.sb_state);
        sb_state.setForegroundResource(R.mipmap.foreground);
        sb_state.setBackgroundResource(R.mipmap.background);
        sb_state.setState(true);
        sb_state.setOnStateChangeListener(new SwitchButton.OnStateChangeListener() {
            @Override
            public void currentState(boolean state) {

                System.out.println("当前状态是:" + state);
            }
        });
    }
}
