package com.programas.inmoprop;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.programas.inmoprop.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

private MainActivityViewModel vm;
    private ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        binding= ActivityMainBinding.inflate(getLayoutInflater());


        setContentView(binding.getRoot());

binding.edTUsuario.setText("luisprofessor@gmail.com");
binding.edTClave.setText("DEEKQW");
        vm.getmMensaje().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvMensaje.setText(s);
            }
        });

        binding.btIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usua=binding.edTUsuario.getText().toString();
                String clav=binding.edTClave.getText().toString();
                //vm.controlLogin(usua,clav);
                //vm.login(usua,clav);
                vm.loginToken(usua,clav);



            }
        });

    }

}