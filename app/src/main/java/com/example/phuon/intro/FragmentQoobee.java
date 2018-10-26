package com.example.phuon.intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

public class FragmentQoobee extends Fragment {
    private View view;
    ImageButton imageButtonSearch;
    EditText editTextSearch;
    String url = "http://www.google.com";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_booqee, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        imageButtonSearch = (ImageButton) view.findViewById(R.id.imageSearchGoogle);
        editTextSearch = (EditText) view.findViewById(R.id.editTextGoogleSearch);
        imageButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextSearch.getText() != null){
                    url = "https://www.google.com/search?q="+editTextSearch.getText().toString();
                }
                Intent intent = new Intent(getActivity(), WebViewSearch.class);
                intent.putExtra("keySearch", url);
                startActivity(intent);
            }
        });

        return view;
    }
}
