package com.example.soumayarebai.lab3_fragments_menus;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    Switch aSwitch;
    Button Btoggle;
    ActionMode mActionMode;
    private ActionMode.Callback mActionModeCallback=new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater=mode.getMenuInflater();
            inflater.inflate(R.menu.context_menu,menu);

            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.aciton_color: Btoggle.setBackgroundResource(R.color.colorPrimary); // mel fichoier colors
                    Btoggle.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
                    mode.finish();
                    return true;
                default: return false;

            }

        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode=null;


        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aSwitch = (Switch) findViewById(R.id.sw_date); // to initializa the switch
        Btoggle= (Button) findViewById(R.id.bt_toggle);
        Btoggle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(mActionMode !=null) return false;
                mActionMode= MainActivity.this.startActionMode(mActionModeCallback);
                v.setSelected(true);
                return true;
            }
        });
        insertFragment();

    }

        public void insertFragment(){
            Fragment fragment= new PickerFragment();
            Bundle bundle= new Bundle();
            bundle.putBoolean("dateOk",aSwitch.isChecked()); // bundle is like a sac
            fragment.setArguments(bundle);
            // this code yasna3 fragment i3abih beli nhebou alih w ohotou fi layout : dynamically
            FragmentManager fragmentManger=getFragmentManager(); // kol activity andha fragment manager mte3ha
            FragmentTransaction  transaction= getFragmentManager().beginTransaction(); // either all of them we have to commit to execute them , if we have two transaction the first should wait for the other
            transaction.replace(R.id.vert_layout,fragment);
            transaction.commit();

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id == R.id.menu_toggle){
            aSwitch.toggle();
            insertFragment();
        }
        return super.onOptionsItemSelected(item);
    }

    public void togglePicker(View view) {
        insertFragment();
    }
}
