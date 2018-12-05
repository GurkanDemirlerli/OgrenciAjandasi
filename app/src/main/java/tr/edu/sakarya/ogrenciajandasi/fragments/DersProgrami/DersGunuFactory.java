package tr.edu.sakarya.ogrenciajandasi.fragments.DersProgrami;

import android.os.Bundle;

public class DersGunuFactory {
    public static DersGunuFragment newInstance(int gun) {
        DersGunuFragment fragment = new DersGunuFragment();
        Bundle bundle = new Bundle(2);
        bundle.putInt("Gun", gun);
        fragment.setArguments(bundle);
        return fragment ;
    }
}
