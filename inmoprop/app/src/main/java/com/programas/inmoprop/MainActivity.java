package com.programas.inmoprop;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.programas.inmoprop.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CALL_PERMISSION = 1;
    private MainActivityViewModel vm;
    private ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
setContentView(binding.getRoot());
vm.activarSensor();
verificarPermisosParaLLamar();
binding.edTUsuario.setText("omarfuentes@gmail.com");
binding.edTClave.setText("123");
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
               // vm.login(usua,clav);
                vm.loginToken(usua,clav);
            }
        });

    }
    private void verificarPermisosParaLLamar() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    REQUEST_CALL_PERMISSION
            );
        }
    }

}