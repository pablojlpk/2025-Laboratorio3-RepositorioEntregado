package com.programas.inmoprop.ui.inquilino;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import com.programas.inmoprop.MenuActivity;
import com.programas.inmoprop.R;
import com.programas.inmoprop.databinding.FragmentInquilinoBinding;
import com.programas.inmoprop.modelos.Inquilino;
import com.programas.inmoprop.ui.inmuebles.SlideshowViewModel;

import java.util.List;

public class InquilinoFragment extends Fragment {

    private FragmentInquilinoBinding binding;
    private InquilinoViewModel vm;
    private Context context;
    public static InquilinoFragment newInstance() {
        return new InquilinoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
      //  InquilinoViewModel inquilinoviewmodel =
        //        new ViewModelProvider(this).get(InquilinoViewModel.class);
        vm = new ViewModelProvider(this).get(InquilinoViewModel.class);
        binding = FragmentInquilinoBinding.inflate(inflater, container, false);
        context=getContext();
        View root = binding.getRoot();

        final TextView textView = binding.textInquilino;
        //vm.getmText().observe(getViewLifecycleOwner(), textView::setText);


     vm.getmText().observe(getViewLifecycleOwner(), new Observer<String>() {
         @Override
         public void onChanged(String s) {
             binding.textInquilino.setText(s);
         }
     });




     vm.getmListadoInquilinos().observe(getViewLifecycleOwner(), new Observer<List<Inquilino>>() {
         @Override
         public void onChanged(List<Inquilino> inquilinos) {
          InquilinoAdapter ia= new InquilinoAdapter(inquilinos,context);
             GridLayoutManager glm= new GridLayoutManager(context,1, GridLayoutManager.VERTICAL,false);
             binding.lista.setLayoutManager(glm);
             binding.lista.setAdapter(ia);
         }

     });

     //MenuActivity menuActivity = (MenuActivity) getActivity();
     vm.obtenerInquilinos( context );
return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}