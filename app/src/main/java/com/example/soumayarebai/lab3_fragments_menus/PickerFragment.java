package com.example.soumayarebai.lab3_fragments_menus;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Soumaya Rebai on 28/04/2017.
 */


public class PickerFragment extends Fragment {
    boolean dateOk=true;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=null;
        if (dateOk) {
            view = inflater.inflate(R.layout.frag_date, container, false);
        }else{ view=inflater.inflate(R.layout.frag_time,container,false);
        }
        return view;
        //inflater un object yekhdem khedma prendre un fichier xml et le transformer en objet ! iraja3 objet men xml kima DOM example we have a btton iwali objet button
        // bundle objet contenir un objet eli béch yemchiw lil fragment
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        dateOk=args.getBoolean("dateOk");
    }
}
