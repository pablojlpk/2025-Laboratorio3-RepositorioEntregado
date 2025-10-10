package com.programas.inmoprop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.programas.inmoprop.databinding.ActivityMenuBinding;
import com.programas.inmoprop.modelos.Propietario;

public class MenuActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMenuBinding binding;
     private Propietario propietario;
    private int idprop;


    public int getIdprop() {
        return idprop;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String usuario=getIntent().getStringExtra("usuario");
        String mail=  getIntent().getStringExtra("mail");
        String idpropietario=  getIntent().getStringExtra("idpropietario");
        //idprop= getIntent().getIntExtra("idpropietario",0);
        propietario= (Propietario) getIntent().getSerializableExtra("propietario");
        idprop= Integer.parseInt(String.valueOf(propietario.getIdpropietario()));

///accedo para setear los datos en el header del menú

        NavigationView nv=findViewById(R.id.nav_view);
        View hv=nv.getHeaderView(0);
        TextView tv=hv.findViewById(R.id.textView);
        tv.setText("Usuario: "+usuario+"\n Mail: "+mail+"-"+idprop);
        ///

        Log.d("datosusuario", "onCreate: "+usuario+" "+mail);

        setSupportActionBar(binding.appBarMenu.toolbar);
        binding.appBarMenu.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .setAnchorView(R.id.fab).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_gallery,
                R.id.nav_slideshow,
                R.id.nav_contrato,
                R.id.nav_logout,
                R.id.nav_inquilino)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}