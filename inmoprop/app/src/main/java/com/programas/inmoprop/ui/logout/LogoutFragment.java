package com.programas.inmoprop.ui.logout;

import static androidx.core.app.ActivityCompat.finishAffinity;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.programas.inmoprop.R;
import com.programas.inmoprop.databinding.FragmentLogoutBinding;

public class LogoutFragment extends Fragment {

    private FragmentLogoutBinding binding;
    private LogoutViewModel vm;

    public static LogoutFragment newInstance() {
        return new LogoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        LogoutViewModel logoutViewModel =
                new ViewModelProvider(this).get(LogoutViewModel.class);
        vm= new ViewModelProvider(this).get(LogoutViewModel.class);

        binding = FragmentLogoutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.tvMensaje;
        logoutViewModel.getmText().observe(getViewLifecycleOwner(), textView::setText);


    binding.btSalir.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requireActivity().finishAffinity();

            System.exit(0);


        }
    });


        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}