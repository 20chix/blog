package com.example.hadielmekawi.tictactoe;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View;
import android.view.ViewGroup;


public class MainActivityFragment extends Fragment{
      private AlertDialog mDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        View newButton = rootView.findViewById(R.id.new_button);
        View continueButton = rootView.findViewById(R.id.continue_button); newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GameActivity.class);
                getActivity().startActivity(intent);
            }
        });
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GameActivity.class); intent.putExtra(GameActivity.KEY_RESTORE, true); getActivity().startActivity(intent);
            } });

        //button here
        View aboutButton = rootView.findViewById(R.id.about_button);
        aboutButton.setOnClickListener(new View.OnClickListener(){
          @Override
            public void  onClick(View view){
              AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
              builder.setTitle(R.string.about_title);
              builder.setMessage(R.string.about_text);
              builder.setCancelable(false);
              builder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener(){



                  @Override
                          public void onClick(DialogInterface dialogInterface, int i){

                        //nohing

                  }
              });

              mDialog = builder.show();

          }




        });





        return rootView;


    }






}