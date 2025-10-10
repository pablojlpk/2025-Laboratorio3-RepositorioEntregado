package com.programas.inmoprop.ui.perfil;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.programas.inmoprop.MenuActivity;
import com.programas.inmoprop.databinding.FragmentGalleryBinding;
import com.programas.inmoprop.modelos.Propietario;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private GalleryViewModel vm;
    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
*/
        vm = new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        context=getContext();
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        MenuActivity menuActivity = (MenuActivity) getActivity();


//asigno datos propietario
        binding.idUsuario.setText(menuActivity.getPropietario().getIdpropietario()+"");
        binding.etApellido.setText(menuActivity.getPropietario().getApellido());
        binding.etNombre.setText(menuActivity.getPropietario().getNombre());
        binding.etDni.setText(menuActivity.getPropietario().getDni()+"");
        binding.etMail.setText(menuActivity.getPropietario().getMail());
        binding.etClave.setText(menuActivity.getPropietario().getClave());


        vm.getText().observe(getViewLifecycleOwner(), textView::setText);
binding.btGuardar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Propietario p=new Propietario();

        p.setIdpropietario(menuActivity.getPropietario().getIdpropietario());
        p.setApellido(binding.etApellido.getText().toString());
        p.setNombre(binding.etNombre.getText().toString());
        p.setDni(Integer.parseInt(binding.etDni.getText().toString()));

        p.setMail(binding.etMail.getText().toString());
        p.setClave(binding.etClave.getText().toString());
        p.setBorrado(false);


        vm.actualizarPropietario(p);

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